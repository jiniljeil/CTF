<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Web\EtcController;
use App\Http\Controllers\Web\PageController;
use App\Http\Controllers\Web\AccountPageController;
use App\Http\Controllers\Api\AuthController;


Route::redirect('/', '/dashboard');
Route::get('/health', [EtcController::class, 'check'])->name('web-render-health');

Route::middleware(['web', 'web.jwt.session', 'web.role.user'])->group(function () {
    Route::get('/dashboard', [PageController::class, 'dashboard'])->name('web-render-dashboard');
    Route::get('/organization', [PageController::class, 'organization'])->name('web-render-organization');
    Route::get('/mypage', [PageController::class, 'mypage'])->name('web-render-mypage');
    Route::get('/mail', [PageController::class, 'mail'])->name('web-render-mail-list');
    Route::get('/qna', [PageController::class, 'qna'])->name('web-render-qna-list');
});

Route::prefix('/admin')->middleware(['web', 'web.jwt.session', 'web.role.admin'])->group(function () {
    Route::redirect('', '/admin/dashboard');
    Route::get('/organization', [PageController::class, 'organization'])->name('web-admin-render-organization');
    Route::get('/mypage', [PageController::class, 'mypage'])->name('web-admin-render-mypage');
    Route::get('/mail', [PageController::class, 'mail'])->name('web-admin-render-mail-list');
    Route::get('/qna', [PageController::class, 'qna'])->name('web-admin-render-qna-list');
    Route::get('/dashboard', [PageController::class, 'dashboard'])->name('web-admin-render-dashboard');
});


Route::middleware(['web'])->group(function () {
    Route::get('/error', [EtcController::class, 'error'])->name('web-render-error');
});

Route::middleware(['web'])->group(function () {
    Route::get('/logout', [AccountPageController::class, 'logout'])->name('web-logout');
});

Route::middleware(['web', 'web.jwt.session.dupl'])->group(function () {
    Route::get('/login', [AccountPageController::class, 'login'])->name('web-render-login');
    Route::post('/login', [AuthController::class, 'authenticate'])->name('web-proc-login');
    Route::get('/register', [AccountPageController::class, 'register'])->name('web-render-register');
});