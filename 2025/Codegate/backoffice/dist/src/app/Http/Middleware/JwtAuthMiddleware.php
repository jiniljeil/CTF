<?php

namespace App\Http\Middleware;

use Closure;
use Exception;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;
use Tymon\JWTAuth\Facades\JWTAuth;
use App\Services\JwtTokenMgmtService;
use Tymon\JWTAuth\Exceptions\JWTException;
use Illuminate\Support\Facades\Auth;


class JwtAuthMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response) $next
     */
    public function handle(Request $request, Closure $next): Response
    {
        try {
            if (!$authenticated = JWTAuth::parseToken()->authenticate()) {
                return response()->json(["data" => ["msg" => "Unauthorized", "data" => ""]], 401);
            }

            $current_token = JWTAuth::getToken();

            if (JwtTokenMgmtService::isBlacklisted($current_token)) {
                return response()->json(["data" => ["msg" => "Unauthorized", "data" => ""]], 401);
            }
        } catch (JWTException $e) {
            return response()->json(["data" => ["msg" => "Unauthorized", "data" => ""]], 401);
        }
        return $next($request);
    }
}