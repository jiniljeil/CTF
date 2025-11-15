<?php

namespace App\Http\Controllers\Web;

use Exception;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

class PageController extends Controller
{
    public function dashboard(Request $request): Response
    {
        return response()->view('dashboard', [
            'title' => config('constants.NAME'),
            'user_info' => $request->user(),
            'jwt_token' => session('jwt_session'),
            'role' => $request->user()['role']
        ]);
    }

    public function organization(Request $request): Response
    {
        return response()->view('organization', [
            'title' => config('constants.NAME'),
            'user_info' => $request->user(),
            'jwt_token' => session('jwt_session'),
            'role' => $request->user()['role']
        ]);
    }

    public function mypage(Request $request): Response
    {
        return response()->view('mypage', [
            'title' => config('constants.NAME'),
            'user_info' => $request->user(),
            'jwt_token' => session('jwt_session'),
            'role' => $request->user()['role']
        ]);
    }


    public function mail(Request $request): Response
    {
        return response()->view('mail', [
            'title' => config('constants.NAME'),
            'user_info' => $request->user(),
            'jwt_token' => session('jwt_session'),
            'role' => $request->user()['role']
        ]);
    }

    public function qna(Request $request): Response
    {
        return response()->view('qna', [
            'title' => config('constants.NAME'),
            'user_info' => $request->user(),
            'jwt_token' => session('jwt_session'),
            'role' => $request->user()['role']
        ]);
    }
}
