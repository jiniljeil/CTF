<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('qnas', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('writer_id');
            $table->string('title');
            $table->string('password');
            $table->string('content');
            $table->string('file_name')->nullable();
            $table->string('real_name')->nullable();
            $table->string('file_path')->nullable();
            $table->integer('file_size')->nullable();
            $table->string('file_ext')->nullable();
            $table->boolean('is_read')->default(false);
            $table->timestamps();

            $table->foreign('writer_id')->references('id')->on('users')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('qna');
    }
};
