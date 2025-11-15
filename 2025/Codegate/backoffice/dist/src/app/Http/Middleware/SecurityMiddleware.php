<?php

namespace App\Http\Middleware;

use Closure;
use Exception;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;

class SecurityMiddleware
{
    private array $forbiddenPatterns = [];
    private string $SECURITY_MODULE = "";

    public function __construct()
    {
        $this->forbiddenPatterns = [];
        $this->SECURITY_MODULE = env('FILE_STORAGE', '/var/www/storage') . '/security_module.json';
    }

    private function loadSecurityPatterns(): void
    {
        if (!file_exists($this->SECURITY_MODULE)) {
            throw new Exception('file not found');
        }

        $security_module = file_get_contents($this->SECURITY_MODULE);
        $security_module = json_decode($security_module, true);

        if (is_array($security_module)) {
            foreach ($security_module as $category => $patterns) {
                foreach ($patterns as $pattern) {
                    $this->forbiddenPatterns[] = "#" . $pattern . "#i";
                }
            }
        }
    }

    /**
     * Handle an incoming request.
     *
     * @param \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response) $next
     */
    public function handle(Request $request, Closure $next): Response
    {
        try {
            $this->loadSecurityPatterns();
            $filteredData = $this->sanitize($request->all());
            $request->merge($filteredData);
        } catch (Exception $e) {
            return redirect('/dashboard');
        }

        return $next($request);
    }

    private function sanitize(array $data): array
    {
        foreach ($data as $key => $value) {
            if (is_string($value)) {
                $value = htmlspecialchars($value, ENT_QUOTES, 'UTF-8');

                foreach ($this->forbiddenPatterns as $pattern) {
                    $value = preg_replace($pattern, '', $value);
                }
            }

            if (is_array($value)) {
                $value = $this->sanitize($value);
            }
            $data[$key] = $value;
        }
        return $data;
    }
}