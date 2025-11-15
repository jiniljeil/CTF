<?php

namespace App\Providers;

use App\Http\Middleware\AdminRoleMiddleware;
use App\Http\Middleware\JwtToSessionMiddleware;
use App\Http\Middleware\JwtAuthMiddleware;
use App\Http\Middleware\SessionDupliCheckMiddleware;
use App\Http\Middleware\UserRoleMiddleware;
use App\Http\Middleware\SecurityMiddleware;
use App\Http\Middleware\WebAdminRoleMiddleware;
use App\Http\Middleware\WebUserRoleMiddleware;
use Illuminate\Support\Facades\App;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        //
//        $this->app->singleton(JwtTokenMgmtService::class, function ($app) {
//            return new JwtTokenMgmtService();
//        });
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        //
        App::bind('api.security', function () {
            return new SecurityMiddleware();
        });
        App::bind('api.jwt.verify', function () {
            return new JwtAuthMiddleware();
        });
        App::bind('api.role.user', function () {
            return new UserRoleMiddleware();
        });
        App::bind('api.role.admin', function () {
            return new AdminRoleMiddleware();
        });
        App::bind('web.jwt.session', function () {
            return new JwtToSessionMiddleware();
        });
        App::bind('web.role.user', function () {
            return new WebUserRoleMiddleware();
        });
        App::bind('web.role.admin', function () {
            return new WebAdminRoleMiddleware();
        });
        App::bind('web.jwt.session.dupl', function () {
            return new SessionDupliCheckMiddleware();
        });
    }


}
