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
    <?php include '../config/db.php'; ?>
    <!-- Hero Section -->
     <br>
    <div class="container mx-auto px-4">
        <div class="flex items-center space-x-2 text-gray-600">
            <a href="#" class="hover:underline">Home</a>
            <span>&gt;</span>
            <a href="#" class="hover:underline">소식·정보</a>
            <span>&gt;</span>
            <span class="text-gray-900 font-semibold">공지사항</span>
        </div>

        <?php
            if(!isset($_GET['id'])) {
                die('잘못된 접근입니다.');
            }

            $id = $_GET['id'];
            $sql = "SELECT * FROM posts WHERE id = ?";
            $stmt = $dbcon->prepare($sql);
            $stmt->bind_param('i', $id);
            $stmt->execute();
            $result = $stmt->get_result();
            $post = $result->fetch_assoc();
            echo "<h2 class='text-2xl font-bold mt-2'>{$post['title']}</h2>";
            echo '</div>';
            echo '<main class="container mx-auto px-4 py-8">';
            echo '<p class="text-gray-700 mb-8">';
            echo $post['content'];
            echo '</p>';
            echo '</main>';
        ?>
    </div>

    <?php include '../config/footer.php'; ?>
</body>
</html>
