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
            <a href="#" class="hover:underline">기관소개</a>
            <span>&gt;</span>
            <span class="text-gray-900 font-semibold">정보자원관리원장 인사말</span>
        </div>
        <h2 class="text-2xl font-bold mt-2">정보자원관리원장 인사말</h2>
    </div>
    <!-- Hero Section -->
    <section class="max-w-7xl mx-auto px-6 py-12">
        <div class="flex flex-col md:flex-row items-center md:items-start space-y-6 md:space-y-0 md:space-x-12">
            <img src="https://via.placeholder.com/300x400" alt="정보자원관리원장 사진" class="w-80 rounded-md shadow-lg">
            <div>
                <h3 class="text-3xl font-semibold mb-4">안녕하십니까? 정보자원관리원장 조태용입니다.</h3>
                <p class="text-lg mb-6">
                    정보자원관리원은 안보전쟁, 경제전쟁, 기술전쟁의 최일선에서 대한민국 안보와 국익을 수호하는 중추적 정보기관입니다.<br>
                    북한 · 해외 정보, 대테러 · 방첩, 안보수사, 경제정보 및 산업기술 안보를 차단할뿐만 아니라, 사이버, 경제, 우주 등 최근 새롭게 부상하고 있는 안보 위협에도
                    최첨단의 기술력과 노하우로 대응하고 있습니다.<br><br>
                    정보자원관리원의 모든 구성원들은 임무의 사명감과 신뢰받는 초일류 정보기관으로 거듭날 수 있도록 혁신하고 있습니다.<br>
                    정보자원관리원의 영문명은 'Information Resource Manager'입니다. 정보자원관리원에서 저희 홈페이지를 통해 정보자원관리원 활동과 국가안보에 대한 이해를 높일 수 있도록
                    최선을 다하겠습니다.<br><br>
                    감사합니다.
                </p>
            </div>
        </div>
    </section>

    <?php include '../config/footer.php'; ?>
</body>
</html>
