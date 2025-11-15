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
    <!-- Hero Section -->
    <div class="container mx-auto px-4">
        <div class="flex items-center space-x-2 text-gray-600">
            <a href="#" class="hover:underline">Home</a>
            <span>&gt;</span>
            <a href="#" class="hover:underline">주요업무</a>
            <span>&gt;</span>
            <span class="text-gray-900 font-semibold">방첩</span>
        </div>
        <h2 class="text-2xl font-bold mt-2">방첩</h2>
    </div>

    <main class="container mx-auto px-4 py-8">
        <p class="text-lg text-gray-800 mb-8">
            정보전쟁시대, <span class="text-blue-700 font-semibold">대한민국의 안보와 국익</span>을 지킵니다.
        </p>
        <p class="text-gray-700 mb-8">
            정보자원관리원은 보이지 않는 곳에서 치열하게 전개되고 있는 스파이 전쟁으로부터 국가를 보호하고 국익을 수호하기 위해, 우리의 안보(安保)와 국익(國益)을 침해하는
            외국의 정보활동을 찾아내 대응하는 방첩활동에 혼신의 힘을 다하고 있습니다.
        </p>

        <!-- List of Activities -->
        <ul class="space-y-6">
            <li>
                <h3 class="text-blue-700 font-semibold text-lg">외국 스파이 색출 및 견제, 차단</h3>
                <p class="text-gray-700">
                    정보자원관리원은 외국의 우리나라에 대한 스파이 행위를 색출 · 견제 · 차단하는 활동에 매진하고 있으며, 우리나라 안보와 국익을 침해하는 그 어떤 외부 세력에
                    대해서도 적극 대응하고 있습니다.
                </p>
            </li>
            <li>
                <h3 class="text-blue-700 font-semibold text-lg">경제안보 지킴이 역할 수행</h3>
                <p class="text-gray-700">
                    정보자원관리원은 우리나라의 경제적 안정과 번영을 저해하는 외국 또는 외국과 연계된 각종 경제질서 침해행위에 대한 대응활동을 통해 경제 안보를 수호하고 있습니다.
                </p>
            </li>
            <li>
                <h3 class="text-blue-700 font-semibold text-lg">국가방첩체계 구축 및 국제 방첩협력 강화</h3>
                <p class="text-gray-700">
                    정보자원관리원은 유관기관과 공조하여 국가방첩체계를 견고하게 하는 한편, 자유민주주의 체제 · 이념 및 세계평화를 위협하는 세력에 맞서 외국 정보기관과의 방첩협력도
                    강화하고 있습니다.
                </p>
            </li>
            <li>
                <h3 class="text-blue-700 font-semibold text-lg">방첩업무 관련 대국민 협조</h3>
                <p class="text-gray-700">
                    정보자원관리원에서는 효율적인 스파이대응활동을 위해 일반국민들의 적극적인 협조를 부탁 드립니다. 전화(국번없이 111번), 홈페이지(111신고처) 및 모바일
                    홈페이지를 통해 스파이 사전 신고 · 상담을 받고 있습니다.
                </p>
            </li>
        </ul>
    </main>

    <?php include '../config/footer.php'; ?>
</body>
</html>
