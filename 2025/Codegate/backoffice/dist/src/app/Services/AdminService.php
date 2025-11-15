<?php

namespace App\Services;

use App\Models\User;
use Illuminate\Database\QueryException;
use Illuminate\Support\Facades\Validator;

class AdminService
{
    public function __construct()
    {

    }

    public function getAllUsers(User $currentUser): array
    {
        try {
            $users = User::select('name', 'email', 'birthday', 'phone_number', 'rrn', 'salary', 'position', 'department', 'created_at')->get();
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

    public function updateAdmin(User $currentUser, array $request): array
    {
        return [];
    }
}
