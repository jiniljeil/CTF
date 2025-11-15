<?php

namespace App\Services;

use Illuminate\Support\Facades\Validator;
use Illuminate\Database\QueryException;
use Exception;
use App\Models\User;

class UserService
{
    public function __construct()
    {
    }

    public function getAllUsers(User $currentUser): array
    {
        try {
            $users = User::select('name', 'email', 'birthday', 'phone_number', 'rrn', 'salary', 'position', 'department', 'created_at')->where('role', 1)->get();
            if (!$users) {
                return [];
            }

            $users = $users->toArray();
            foreach ($users as &$user) {
                if ($user['email'] !== $currentUser['email']) {
                    $user['email'] = preg_replace('/^(.{1})(.*)(@.*)$/', '$1****$3', $user['email']);
                    $user['phone_number'] = preg_replace('/-(\d{4})-/', '-****-', $user['phone_number']);
                    $user['rrn'] = "******-*******";
                    $user['salary'] = "************";
                }
            }
        } catch (QueryException $e) {
            return [];
        }
        return $users;
    }

    public function updateUser(User $currentUser, array $request): array
    {
        try {
            $validator = Validator::make($request, config('constants.VALIDATION.USER_UPDATE'));

            if ($validator->fails()) {
                return [];
            }

            $user = User::where('email', $currentUser->email)->first();
            if (!$user) {
                return [];
            }

            if ($user->role > config('constants.ROLE.USER')) {
                return [];
            }

            $user->update($request);
            $updatedUser = User::where('email', $currentUser->email)->first()->toArray();
        } catch (QueryException|Exception $e) {
            return [];
        }

        return $updatedUser;
    }
}
