<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('name');  // your name - e.g. honggildong
            $table->string('email')->unique();  // your email - e.g. hong@novition.com (unique id + @novition2025.com)
            $table->string('password');  // your password - e.g. veryhardpassword
            $table->string('birthday');  // your birthday - e.g. 19990101 (yyyymmdd), 8 length
            $table->string('rrn')->unique();  // korean rrn - e.g. 990101-1234567, 14 length
            $table->string('phone_number')->unique();  // korean phone number - e.g. 010-1234-1234, 13 length
            $table->string('salary')->default('0');  // your salary - default 0
            $table->string('position')->default('intern'); // your position - default intern
            $table->string('department')->default('IT DevTeam'); // your department - default IT DevTeam
            $table->boolean('welcome_mail')->default(0); // your department - default IT DevTeam
            $table->boolean('role')->default(config('constants.ROLE.USER'));  // your default role - default 1 (general user) or 2 - (super admin)
            $table->timestamps();
        });

        DB::table('users')->insert([
            [
                "name" => 'James Park',
                "email" => 'administrator@novition.org',
                "password" => '$2y$12$A9eY12Z7uv6xz1Fy0lRdreaXFbnxa2CHCVWP49Trgwgw5meF7A2xi',
                // password => redactredactredact
                "birthday" => '20250329',
                "rrn" => '250329-12345678',
                "phone_number" => '010-1234-1234',
                "salary" => '9999999',
                "position" => 'CEO',
                "department" => 'CEO',
                "role" => config('constants.ROLE.ADMIN'),
                "welcome_mail" => 1,
                "created_at" => date('Y-m-d H:i:s', time())
            ]
        ]);
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};