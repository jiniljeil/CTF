<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Services\AdminService;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class AdminController extends Controller
{
    public function __construct(AdminService $adminService)
    {
        $this->adminService = $adminService;
    }

    public function getAllUsers(Request $request): JsonResponse
    {
        $result = $this->adminService->getAllUsers($request->user());
        if (!$result) {
            return response()->json(["data" => ["msg" => "User information not founded", "data" => $result]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function update(Request $request): JsonResponse
    {
        $result = $this->adminService->updateAdmin($request->user(), $request->all());
        if (!$result) {
            return response()->json(["data" => ["msg" => "Something wrong", "data" => ""]], 400)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }
}
