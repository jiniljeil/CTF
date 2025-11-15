<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Response;

use App\Services\AuthService;

class AuthController extends Controller
{
    public function __construct(AuthService $authService)
    {
        $this->authService = $authService;
    }

    public function getAuthUser(Request $request): JsonResponse
    {
        return response()->json(["data" => ["msg" => "Successfully", "data" => $request->user()]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function authenticate(Request $request): RedirectResponse
    {
        list($token, $ttl, $role) = $this->authService->login($request->only('email', 'password', 'role'));
        if (!$token || $role === null) {
            return redirect()->route('web-render-error', [
                'error' => 404,
                'message' => 'User information not found'
            ])->header('Content-Type', 'application/json; charset=UTF-8');
        }

        session(['jwt_session' => $token]);
        session(['email' => $request->email]);
        
        if ($role == 1){
            return redirect('/dashboard')
                ->withCookie(cookie('jwt_session', $token, $ttl / 60, '/', null, false, false));
        } elseif ($role == 2){
            return redirect('/admin/dashboard')
                    ->withCookie(cookie('jwt_session', $token, $ttl / 60, '/', null, false, false));
        } else {
            return redirect('/logout');
        }
    }

    public function register(Request $request): JsonResponse|Response
    {
        $result = $this->authService->create($request->all());
        if (!$result) {
            return response()->json(["data" => ["msg" => "Something wrong", "data" => ""]], 400)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }
        return response()->noContent(201);
    }

    public function logout(Request $request): JsonResponse
    {
        $result = $this->authService->logout($request->user());
        if (!$result) {
            return response()->json(["data" => ["msg" => "Something wrong", "data" => ""]])
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }
        return response()->json(["data" => ["msg" => "Successfully", "data" => ""]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }
}