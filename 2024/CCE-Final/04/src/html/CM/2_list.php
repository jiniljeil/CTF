<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보자원관리원 Webpage</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../style.css">
</head>
<body>
    <!-- Header -->
    <?php include '../config/header.php'; ?>
    <br>
    <div class="container mx-auto px-4">
        <div class="flex items-center space-x-2 text-gray-600">
            <a href="#" class="hover:underline">Home</a>
            <span>&gt;</span>
            <a href="#" class="hover:underline">참여·민원</a>
            <span>&gt;</span>
            <span class="text-gray-900 font-semibold">간첩신고</span>
        </div>
        <h2 class="text-2xl font-bold mt-2">간첩신고</h2>
    </div>
    <!-- Hero Section -->
    <section class="bg-white py-16 text-center">
        <h1 class="text-3xl font-bold text-blue-700 mb-4">111 신고</h1>
        <p class="text-base mb-8">
            北 간첩 및 연계세력, 국제범죄, 테러, 산업·방산스파이, 외국스파이, 사이버공격, 직원비리 등 국가안보와 관련된 범죄신고를 <strong>국번없이 111(무료)</strong> 과 온라인으로 제보받고 있습니다.
        </p>

        <!-- Stepper Section -->
        <div class="flex justify-center space-x-12 mb-12">
            <div class="text-center">
                <div class="w-28 h-28 bg-blue-700 text-white rounded-full flex justify-center items-center text-2xl font-bold mb-4">
                    STEP 01
                </div>
                <p class="text-lg font-medium">회원가입 및 로그인</p>
            </div>
            <div class="text-center">
                <div class="w-28 h-28 bg-blue-700 text-white rounded-full flex justify-center items-center text-2xl font-bold mb-4">
                    STEP 02
                </div>
                <p class="text-lg font-medium">신고하기</p>
            </div>
        </div>

        <p class="text-sm mb-4">신고된 내용은 보안이 유지되며, 개인정보 보호법에 의해 보호됩니다.</p>
        <p class="text-sm mb-8">악의적 사유로 허위신고 시 법적 처벌을 받을 수 있음을 유의하시고, 반드시 회원가입 및 로그인 후 신고를 진행해 주시기 바랍니다.</p>

        <!-- CTA Button -->
        <a href="/login"><button class="bg-blue-700 hover:bg-blue-800 text-white py-3 px-8 rounded-lg text-lg">회원가입 및 로그인</button></a>
    </section>

    <?php include '../config/footer.php'; ?>
</body>
</html>
