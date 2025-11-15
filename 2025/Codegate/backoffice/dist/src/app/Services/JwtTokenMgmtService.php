<?php

namespace App\Services;

use Illuminate\Support\Facades\Redis;

class JwtTokenMgmtService
{
    protected const ACCESSLIST_KEY = 'jwt:access:';
    protected const BLACKLIST_KEY = 'jwt:blacklist:';

    public static function addToAccessList(string $suffixKey, int $ttl, string $token): void
    {
        Redis::setex(self::ACCESSLIST_KEY . $suffixKey, $ttl, $token);
    }

    public static function isAccessToken(string $suffixKey): bool
    {
        return Redis::exists(self::ACCESSLIST_KEY . $suffixKey);
    }

    public static function getAccessToken(string $suffixKey): ?string
    {
        return Redis::get(self::ACCESSLIST_KEY . $suffixKey);
    }

    public static function delAccessToken(string $suffixKey): void
    {
        Redis::del(self::ACCESSLIST_KEY . $suffixKey);
    }

    public static function addToBlacklist(string $token, int $ttl): void
    {
        Redis::setex(self::BLACKLIST_KEY . $token, $ttl, true);
    }

    public static function isBlacklisted(string $token): bool
    {
        return Redis::exists(self::BLACKLIST_KEY . $token) > 0;
    }
}
