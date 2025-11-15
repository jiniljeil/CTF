<?php session_start(); ?>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보자원관리원 Webpage</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <!-- Header -->
    <?php include 'config/header.php'; ?>
    <?php
        if (isset($_SESSION['uid'])) {
            echo "<script>alert('이미 로그인되어 있습니다.');</script>";
            echo "<script>location.href = '/report.php';</script>";
        }
    ?>
    <br>
    <div class="container mx-auto px-4">
        <div class="flex items-center space-x-2 text-gray-600">
            <a href="#" class="hover:underline">Home</a>
            <span>&gt;</span>
            <a href="#" class="hover:underline">참여·민원</a>
            <span>&gt;</span>
            <span class="text-gray-900 font-semibold">간첩신고</span>
            <span>&gt;</span>
            <span class="text-gray-900 font-semibold">회원가입 및 로그인</span>
        </div>
        <h2 class="text-2xl font-bold mt-2">회원가입 및 로그인</h2>
    </div>
    <!-- Hero Section -->
    <section class="max-w-7xl mx-auto px-6 py-12">
        <div class="flex flex-col md:flex-row items-center md:items-start space-y-6 md:space-y-0 md:space-x-12">
            <div class="w-full">
                <h3 class="text-3xl font-semibold mb-4">로그인</h3>
                <form action="/api/login_check.php" method="post">
                    <div class="">
                        <div>
                            <label for="uid" class="block text-sm font-medium text-gray-700">아이디</label>
                            <input type="text" id="uid" name="uid" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                        <br>
                        <div>
                            <label for="upw" class="block text-sm font-medium text-gray-700">비밀번호</label>
                            <input type="password" id="upw" name="upw" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                        <br>
                    </div>
                    <br>
                    <div class="flex justify-end">
                        <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-md">로그인</button>
                    </div>
                </form>
            </div>
        
            <div class="w-full">
                <h3 class="text-3xl font-semibold mb-4">회원가입</h3>
                <form action="/api/register.php" method="post">
                    <div class="">
                        <div>
                            <label for="name" class="block text-sm font-medium text-gray-700">이름</label>
                            <input type="text" id="name" name="name" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                        <br>
                        <div>
                            <label for="email" class="block text-sm font-medium text-gray-700">이메일</label>
                            <input type="text" id="email" name="email" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                        <br>
                        <div>
                            <label for="pnum" class="block text-sm font-medium text-gray-700">전화번호</label>
                            <input type="text" id="pnum" name="pnum" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                        <br>
                        <div>
                            <label for="uid" class="block text-sm font-medium text-gray-700">아이디</label>
                            <input type="text" id="uid" name="uid" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                        <br>
                        <div>
                            <label for="upw" class="block text-sm font-medium text-gray-700">비밀번호</label>
                            <input type="password" id="upw" name="upw" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                        <br>
                        <div>
                            <label for="cupw" class="block text-sm font-medium text-gray-700">비밀번호 확인</label>
                            <input type="password" id="cupw" name="cupw" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        </div>
                    </div>
                    <br>
                    <div class="flex justify-end">
                        <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-md">회원가입</button>
                    </div>

                </form>
            </div>
        </div>
    </section>

    <?php include 'config/footer.php'; ?>
</body>
</html>
