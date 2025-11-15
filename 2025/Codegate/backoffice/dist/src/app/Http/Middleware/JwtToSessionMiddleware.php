<?php

namespace App\Http\Middleware;

use Closure;
use Exception;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;
use Tymon\JWTAuth\Facades\JWTAuth;

class JwtToSessionMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response) $next
     */
    public function handle(Request $request, Closure $next): RedirectResponse|Response
    {
        try {
            $jwt_session = session('jwt_session');
            if (!$jwt_session) {
                throw new Exception('Invalid token');
            }

            $user = JWTAuth::setToken($jwt_session)->authenticate();
            if (!$user) throw new Exception('Not founded user information');
            $request->attributes->add(['user' => $user]);

        } catch (Exception $e) {
            return redirect('/logout');
        }

        return $next($request);
    }
}
