<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;

class WebUserRoleMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param \Closure(\Illuminate\Http\Request): (\Symfony\C omponent\HttpFoundation\Response) $next
     */
    public function handle(Request $request, Closure $next): Response
    {
        if ($request->user()['role'] !== config('constants.ROLE.USER')) {
            return redirect()->route('web-render-error', [
                'error' => 403,
                'message' => 'Permission denied'
            ]);
        }

        return $next($request);
    }
}