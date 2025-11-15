<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Support\Facades\Redis;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Redis::command('FLUSHALL');
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        //
    }
};
