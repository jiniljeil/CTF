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
        <h2 class="text-2xl font-bold mt-2">공지사항</h2>
    </div>


        <!-- Search Form -->
    <div class="container mx-auto px-4 py-6 bg-white shadow-md">
        <form class="space-y-4">
            <div class="grid grid-cols-4 gap-4">
                <div>
                    <label for="title" class="block text-sm font-medium text-gray-700">제목/내용</label>
                    <input type="text" id="title" name="title" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                </div>
                <div>
                    <label for="registrar" class="block text-sm font-medium text-gray-700">등록일자</label>
                    <select id="registrar" name="registrar" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        <option value="">All</option>
                        <option value="today">Today</option>
                        <option value="week">This Week</option>
                        <option value="month">This Month</option>
                    </select>
                </div>
                <div>
                    <label for="author" class="block text-sm font-medium text-gray-700">등록자</label>
                    <select id="author" name="author" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        <option value="">All</option>
                        <option value="admin">Admin</option>
                        <option value="user">User</option>
                    </select>
                </div>
                <div>
                    <label for="date" class="block text-sm font-medium text-gray-700">날짜</label>
                    <select id="date" name="date" class="mt-1 block w-full p-2 border border-gray-300 rounded-md">
                        <option value="">All</option>
                        <option value="latest">Latest</option>
                        <option value="oldest">Oldest</option>
                    </select>
                </div>
            </div>
            <div class="flex justify-end">
                <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-md">검색</button>
            </div>
        </form>
    </div>

    <!-- Announcements Table -->
    <div class="container mx-auto px-4 py-6 bg-white shadow-md">
        <table class="w-full table-auto">
            <thead>
                <tr class="bg-gray-100 text-gray-700">
                    <th class="px-4 py-2">번호</th>
                    <th class="px-4 py-2">제목</th>
                    <th class="px-4 py-2">사용자</th>
                    <th class="px-4 py-2">등록일자</th>
                </tr>
            </thead>
            <tbody>
                <?php
                error_reporting(E_ALL&~E_NOTICE);
                $title = '';
                $register = '';
                $author = '';
                $date = '';
                if(isset($_GET['title'])) $title = $_GET['title'];
                if(isset($_GET['register'])) $register = $_GET['register'];
                if(isset($_GET['author'])) $author = $_GET['author'];
                if(isset($_GET['date'])) $date = $_GET['date'];
                
                if($title) {
                    $sql = "SELECT * FROM posts WHERE title LIKE CONCAT('%',?,'%') OR content LIKE CONCAT('%',?,'%') order by id DESC;";
                    $stmt = $dbcon->prepare($sql);
                    $stmt->bind_param("ss", $title, $title);
                    $stmt->execute();
                    $result = $stmt->get_result();
                } else if($register) {
                    if($register == 'today') {
                        $sql = "SELECT * FROM posts WHERE created_at >= CURDATE() order by id DESC;";
                    } else if($register == 'week') {
                        $sql = "SELECT * FROM posts WHERE YEARWEEK(created_at) = YEARWEEK(NOW()) order by id DESC;";
                    } else if($register == 'month') {
                        $sql = "SELECT * FROM posts WHERE MONTH(created_at) = MONTH(NOW()) order by id DESC;";
                    } else {
                        $sql = "SELECT * FROM posts order by id DESC;";
                    }
                    $result = $dbcon->query($sql);
                } else if($author) {
                    if($author == 'admin') {
                        $sql = "SELECT * FROM posts WHERE author_id = 'admin' order by id DESC;";
                    } else if($author == 'user') {
                        $sql = "SELECT * FROM posts WHERE author_id != 'admin' order by id DESC;";
                    } else {
                        $sql = "SELECT * FROM posts order by id DESC;";
                    }
                    $result = $dbcon->query($sql);
                } else if($date) {
                    if($date == 'latest') {
                        $sql = "SELECT * FROM posts order by id DESC;";
                    } else if($date == 'oldest') {
                        $sql = "SELECT * FROM posts order by id ASC;";
                    } else {
                        $sql = "SELECT * FROM posts order by id DESC;";
                    }
                    $result = $dbcon->query($sql);
                } else {
                    $sql = "SELECT * FROM posts order by id DESC;";
                    $result = $dbcon->query($sql);
                }

                if($result->num_rows > 0) {
                    while($row = $result->fetch_assoc()) {
                        echo "<tr class='border-t'>";
                        echo "<td class='px-4 py-2 text-center'>" . $row['id'] . "</td>";
                        echo "<td class='px-4 py-2'><a href='/CM/detail.php?id=".$row['id']."'>" . $row['title'] . "</a></td>";
                        echo "<td class='px-4 py-2 text-center'>" . $row['author_id'] . "</td>";
                        echo "<td class='px-4 py-2 text-center'>" . $row['created_at'] . "</td>";
                        echo "</tr>";
                    }
                }
                ?>
            </tbody>
        </table>

        <!-- Pagination -->
        <div class="flex justify-center mt-4">
            <button class="px-3 py-1 bg-blue-600 text-white rounded-md mx-1">1</button>
        </div>
    </div>


    <?php include '../config/footer.php'; ?>
</body>
</html>
