<?php

namespace App\Http\Controllers\Web;

use App\Http\Controllers\Controller;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Auth;
use Exception;

class AccountPageController extends Controller
{
    public function login(): Response
    {
        return response()->view('login', [
            'title' => config('constants.NAME'),
            'csrf_token' => csrf_token(),
        ]);
    }

    public function register(): Response
    {
        return response()->view('register', [
            'title' => config('constants.NAME'),
        ]);
    }

    public function logout(Request $request): RedirectResponse
    {
        try {
            session()->forget('jwt_session');
            session()->forget('email');
            session()->forget('role');
            Auth::logout();
            session()->flush();
            session()->regenerateToken();
        } catch (Exception $e) {
            return redirect('/login');
        }

        return redirect('/login');
    }
}
