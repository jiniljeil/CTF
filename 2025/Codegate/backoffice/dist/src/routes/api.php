<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Api\AuthController;
use App\Http\Controllers\Api\UserController;
use App\Http\Controllers\Api\MailController;
use App\Http\Controllers\Api\AdminController;
use App\Http\Controllers\Api\QnaController;

// Authorization endpoint
Route::prefix('v1')->middleware(['throttle:api', 'api.security'])->group(function () {
    Route::post('/register', [AuthController::class, 'register'])->name('api-register');

    Route::middleware(['api.jwt.verify', 'api.role.user'])->group(function () {
        Route::patch('/user-update', [UserController::class, 'update'])->name('api-user-update');
    });
    Route::middleware(['api.jwt.verify', 'api.role.admin'])->group(function () {
        Route::patch('/admin-update', [AdminController::class, 'update'])->name('api-admin-update');
    });
    Route::middleware(['api.jwt.verify'])->group(function () {
        Route::post('/logout', [AuthController::class, 'logout'])->name('api-logout');
    });
});


Route::prefix('v1/user')->middleware(['throttle:api', 'api.security', 'api.jwt.verify', 'api.role.user'])->group(function () {
    // Get User(s) information
    Route::get('', [AuthController::class, 'getAuthUser'])->name('api-get-auth-user');
    Route::get('/list', [UserController::class, 'getAllUsers'])->name('api-get-all-user');

    // Mail
    Route::get('/mail/inbox', [MailController::class, 'getMyInbox'])->name('api-get-all-mail');
    Route::post('/mail/sent', [MailController::class, 'sent'])->name('api-set-mail');
    Route::get('/mail/read/{mailId}', [MailController::class, 'read'])
        ->where('mailId', '[0-9]+')
        ->name('api-get-mail');
    Route::get('/mail/template/{templateId}', [MailController::class, 'getMailTemplate'])
        ->where('templateId', '[0-9]+')
        ->name('api-get-mail-template');

    // QnA board
    Route::get('/qna', [QnaController::class, 'getQnaList'])->name('api-get-all-qna');
    Route::post('/qna', [QnaController::class, 'create'])->name('api-set-qna');
    Route::get('/qna/{qnaId}', [QnaController::class, 'getQna'])
        ->where('qnaId', '[0-9]+')
        ->name('api-get-qna');
    Route::post('/qna/file', [QnaController::class, 'getFile'])
        ->name('api-get-qna-file');
});

Route::prefix('v1/admin')->middleware(['throttle:api', 'api.security', 'api.jwt.verify', 'api.role.admin'])->group(function () {
    Route::get('', [AuthController::class, 'getAuthUser'])->name('api-get-auth-user');
    Route::get('/list', [AdminController::class, 'getAllUsers'])->name('api-get-all-user');

    // Mail
    Route::get('/mail/inbox', [MailController::class, 'getMyInbox'])->name('api-admin-get-all-mail');
    Route::post('/mail/sent', [MailController::class, 'sent'])->name('api-admin-set-mail');
    Route::get('/mail/read/{mailId}', [MailController::class, 'read'])
        ->where('mailId', '[0-9]+')
        ->name('api-admin-get-mail');
    Route::post('/mail/template', [MailController::class, 'createMailTemplate'])->name('api-admin-set-mail-template');
    Route::get('/mail/template/{templateId}', [MailController::class, 'getMailTemplate'])
        ->where('templateId', '[0-9]+')
        ->name('api-admin-get-mail-template');

    Route::get('/qna', [QnaController::class, 'getQnaList'])->name('api-admin-get-all-qna');
    Route::get('/qna/{qnaId}', [QnaController::class, 'getQna'])
        ->where('qnaId', '[0-9]+')
        ->name('api-admin-get-qna');
    Route::post('/qna/file', [QnaController::class, 'getFile'])
        ->name('api-admin-get-qna-file');
});