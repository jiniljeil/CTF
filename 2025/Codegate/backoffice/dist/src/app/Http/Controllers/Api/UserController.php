<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\UserService;
use Illuminate\Http\JsonResponse;


class UserController extends Controller
{
    public function __construct(UserService $userService)
    {
        $this->userService = $userService;
    }

    public function getAllUsers(Request $request): JsonResponse
    {
        $result = $this->userService->getAllUsers($request->user());
        if (!$result) {
            return response()->json(["data" => ["msg" => "User information not founded", "data" => $result]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function update(Request $request): JsonResponse
    {
        $updateData = array_filter($request->only('name', 'birthday', 'phone_number', 'salary'), fn($value) => !is_null($value));
        $result = $this->userService->updateUser($request->user(), $updateData);
        if (!$result) {
            return response()->json(["data" => ["msg" => "Something wrong", "data" => ""]], 400)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }
}
