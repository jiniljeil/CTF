<?php

namespace App\Http\Middleware;

use Closure;
use Exception;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;
use Tymon\JWTAuth\Facades\JWTAuth;

class SessionDupliCheckMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param  \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response)  $next
     */
    public function handle(Request $request, Closure $next): RedirectResponse|Response|string
    {
        try {
            $jwt_session = session('jwt_session');
            if ($jwt_session) {
                $user = JWTAuth::setToken($jwt_session)->authenticate();

                if ($user) {
                    throw new Exception('Already login');
                }
            }
        } catch (Exception $e) {
            return redirect('/dashboard');
        }

        return $next($request);
    }
}
