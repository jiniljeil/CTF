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
        if (!isset($_SESSION['uid'])) {
            die('<script>alert("로그인이 필요합니다.");location.href="/login"</script>');
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
            <span class="text-gray-900 font-semibold">간첩 신고</span>
        </div>
        <h2 class="text-2xl font-bold mt-2">간첩 신고</h2>
    </div>
    <!-- Hero Section -->
    <section class="max-w-7xl mx-auto px-6 py-12">
        <div class="flex flex-col md:flex-row items-center md:items-start space-y-6 md:space-y-0 md:space-x-12">
            <div class="w-full">
                <h3 class="text-2xl font-semibold mb-4">신고자 정보</h3>
                <?php
                $user_name = $_SESSION['uid'];
                $file_path = "/app/user/".$user_name."/profile.json";
                $user_data = json_decode(file_get_contents($file_path), true);
                echo "<p>이름 : ".$user_data['name']."</p>";
                echo "<p>아이디 : ".$user_data['uid']."</p>";
                echo "<p>이메일 : ".$user_data['email']."</p>";
                echo "<p>전화번호 : ".$user_data['pnum']."</p>";
            ?>

                <form action="/api/report_check.php" method="post" enctype="multipart/form-data">
                    <div class="mt-4">
                        <label for="name" class="block text sm font-medium text-gray-700">신고 대상자 이름</label>
                        <input type="text" id="name" name="name" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                    </div>
                    <div class="mt-4">
                        <label for="content" class="block text sm font-medium text-gray-700">신고 사유</label>
                        <textarea id="content" name="content" class="mt-1 block w-full p-6 border border-gray-300 rounded-md"></textarea>
                    </div>

                    <div class="mt-4">
                        <label for="evidence" class="block text sm font-medium text-gray-700">증거 첨부</label>
                        <input type="file" id="evidence" name="evidence" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                    </div>

                    <div class="mt-4">
                        <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-md">신고하기</button> 
                    </div>
                </form>
            </div>
    </section>

    <?php include 'config/footer.php'; ?>
</body>
</html>
