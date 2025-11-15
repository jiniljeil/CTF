<?php

namespace App\Http\Controllers\Web;

use App\Http\Controllers\Controller;
use Exception;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Response;
use Illuminate\Http\Request;

class EtcController extends Controller
{
    /**
     * Health check
     *
     * @return JsonResponse
     */
    public function check(): JsonResponse
    {
        return response()->json([
            'status' => 'OK'
        ]);
    }

    public function error(Request $request): Response
    {
        $error = $request->query('error', '404');
        $message = $request->query('message', 'Not Found');
    
        return response()->view('error', [
            'title' => config('constants.NAME'),
            'error' => $error,
            'message' => $message
        ]);    
    }
}