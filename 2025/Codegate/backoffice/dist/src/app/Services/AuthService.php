<?php

namespace App\Services;

use Illuminate\Http\Request;
use Tymon\JWTAuth\Facades\JWTAuth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Database\QueryException;
use Exception;
use App\Models\User;
use Illuminate\Support\Facades\Auth;


class AuthService
{
    public function __construct()
    {
        //
    }

    public function login(array $credential): array
    {
        try {
            $validator = Validator::make($credential, config('constants.VALIDATION.LOGIN'));

            if ($validator->fails()) {
                return [null, null, null];
            }

            if (!$token = JWTAuth::attempt($credential)) {
                return [null, null, null];
            }

            $role = User::where('email', $credential['email'])->first()->toArray()['role'];

            $previousToken = JwtTokenMgmtService::getAccessToken($credential['email']);

            if ($previousToken || $token === $previousToken) {
                JwtTokenMgmtService::addToBlacklist($previousToken, JwtAuth::factory()->getTTL() * config('constants.JWT.EXP'));
            }

            $ttl = 1800;
            JwtTokenMgmtService::addToAccessList($credential['email'], $ttl, $token);

        } catch (QueryException|Exception $e) {
            return [null, null, null];
        }
        return [$token, $ttl, $role];
    }

    public function logout(User $currentUser): bool
    {
        try {
            $token = JWTAuth::getToken();

            JwtTokenMgmtService::delAccessToken($currentUser->email);
            $ttl = auth('api')->factory()->getTTL() * config('constants.JWT.EXP');
            JwtTokenMgmtService::addToBlacklist($token, $ttl);

            Auth::guard('api')->logout();
        } catch (Exception $e) {
            return false;
        }
        return true;
    }

    public function create(array $request): bool
    {
        try {
            $validator = Validator::make($request, config('constants.VALIDATION.CREDENTIAL'));

            if ($validator->fails()) {
                return false;
            }

            User::create([
                'name' => $request['name'],
                'email' => $request['email'],
                'password' => Hash::make($request['password']),
                'birthday' => $request['birthday'],
                'rrn' => $request['rrn'],
                'phone_number' => $request['phone_number'],
            ]);
        } catch (QueryException|Exception $e) {
            return false;
        }

        return true;
    }
}