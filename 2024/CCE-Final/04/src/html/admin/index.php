<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin page</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .section-content {
            display: none; /* Initially hide sections */
            margin-top: 10px;
        }
    </style>
</head>
<body class="bg-gray-100 text-gray-900">

    <?php
        if(!isset($_SESSION['uid'])) {
            die('<script>alert("Login required");location.href="/login"</script>');
        }
        if($_SESSION['uid'] !== 'admin') {
            die('<script>alert("You are not admin");</script>');
        }

        include '../config/db.php';
    ?>

    <div class="max-w-7xl mx-auto py-10 px-4 sm:px-6 lg:px-8">
        <h1 class="text-3xl font-bold text-gray-800 mb-6">Welcome Admin</h1>
        <div class="bg-white shadow-md rounded-lg p-6">
            <h2 class="text-2xl font-semibold mb-4">Admin Dashboard</h2>
            
            <div class="mb-4">
                <h2 id="users-toggle" class="text-lg font-semibold text-blue-600 cursor-pointer">Users</h2>
                <div id="users-content" class="section-content mt-4">
                    <?php
                        $USER_PATH = '/app/user/';
                        $files = scandir($USER_PATH);
                        $folders = array_diff($files, array('.', '..'));

                        foreach ($folders as $folder) {
                            echo '<div class="border border-gray-300 bg-gray-50 p-4 rounded-md mb-4">';
                            echo '<h3 class="text-xl font-medium"> User: ' . $folder . '</h3>';
                            echo '</div>';
                        }
                    ?>
                </div>
            </div>
            <hr class="my-4">

            <div class="mb-4">
                <h2 id="posts-toggle" class="text-lg font-semibold text-blue-600 cursor-pointer">Posts</h2>
                <div id="posts-content" class="section-content mt-4">
                    <?php
                        $qeury = "SELECT * FROM posts ORDER BY id DESC";
                        $result = $dbcon->query($qeury);

                        if ($result->num_rows > 0) {
                            $rows = array();
                            while ($row = $result->fetch_assoc()) {
                                $rows[] = $row;
                            }
                            foreach ($rows as $row) {
                                echo '<div class="border border-gray-300 bg-gray-50 p-4 rounded-md mb-4">';
                                echo '<h3 class="text-xl font-medium">Title: ' . $row['title'] . '</h3>';
                                echo '<p>Content: ' . $row['content'] . '</p>';
                                echo '<p>Author: ' . $row['author_id'] . '</p>';
                                echo '<p>Created at: ' . $row['created_at'] . '</p>';
                                echo '</div>';
                            }
                        } else {
                            echo '<p class="text-gray-500">No posts found.</p>';
                        }
                    ?>
                </div>
            </div>
            <hr class="my-4">

            <div class="mb-4">
                <h2 id="post-add-toggle" class="text-lg font-semibold text-blue-600 cursor-pointer">Add Post</h2>
                <div id="post-add-content" class="section-content mt-4">
                    <form action="/admin/index.php" method="post">
                        <div class="mb-4">
                            <label for="title" class="block text-sm font-medium text-gray-700">Title</label>
                            <input type="text" id="title" name="title" placeholder="Enter title" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                        </div>
                        <div class="mb-4">
                            <label for="content" class="block text-sm font-medium text-gray-700">Content</label>
                            <textarea id="content" name="content" placeholder="Enter content" rows="5" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"></textarea>
                        </div>
                        <button type="submit" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">Add Post</button>
                    </form>
                </div>
            </div>
            <hr class="my-4">

            <div class="mb-4">
                <h2 id="post-search-toggle" class="text-lg font-semibold text-blue-600 cursor-pointer">Search Posts</h2>
                <div>
                    <div id="post-search-content" class="section-content mt-4">
                        <form action="/admin/index.php" method="post">
                            <div class="mb-4">
                                <label for="search" class="block text-sm font-medium text-gray-700">Search</label>
                                <input type="text" id="search" name="search" placeholder="Enter search keyword" class="mt-1 block w-1/3 px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                            </div>
                            <button type="submit" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">Search</button>
                        </form>
                    </div>
                        <?php
                            if (isset($_POST['search'])) {
                                $keyword = $_POST['search'];
                                
                                # [DEBUG] SQL 
                                $query = "SELECT * FROM posts WHERE title like '%$keyword%' OR content like '%$keyword%'";
                                $result = $dbcon->query($query);

                                if ($result->num_rows > 0) {
                                    $rows = array();
                                    while ($row = $result->fetch_assoc()) {
                                        $rows[] = $row;
                                    }
                                    echo '<div class="mt-4 p-4 bg-white rounded-lg shadow-md">';
                                    echo '<p class="font-semibold">Results:</p>';
                                    echo '<pre class="text-sm text-gray-800">' . json_encode($rows, JSON_PRETTY_PRINT) . '</pre>';
                                    echo '</div>';
                                } else {
                                    echo '<p class="text-gray-500 mt-4">No results found.</p>';
                                }
                            }
                        ?>
                </div>
            </div>
        </div>
    </div>

    <?php
        if(isset($_POST['title']) && isset($_POST['content'])) {
            $title = $_POST['title'];
            $content = $_POST['content'];
            $author_id = $_SESSION['uid'];

            if(strlen($title) > 254) {
                die('<script>alert("Invalid request");</script>');
            }

            $stmt = $dbcon->prepare("INSERT INTO posts (title, content, author_id) VALUES (?, ?, ?)");
            $stmt->bind_param("sss", $title, $content, $author_id);
            $stmt->execute();
            echo '<script>alert("Post added");location.href="/admin/"</script>';
        }
    ?>

    <script>
        document.getElementById('users-toggle').addEventListener('click', function() {
            var content = document.getElementById('users-content');
            content.style.display = content.style.display === 'none' || content.style.display === '' ? 'block' : 'none';
        });

        document.getElementById('posts-toggle').addEventListener('click', function() {
            var content = document.getElementById('posts-content');
            content.style.display = content.style.display === 'none' || content.style.display === '' ? 'block' : 'none';
        });

        document.getElementById('post-add-toggle').addEventListener('click', function() {
            var content = document.getElementById('post-add-content');
            content.style.display = content.style.display === 'none' || content.style.display === '' ? 'block' : 'none';
        });

        document.getElementById('post-search-toggle').addEventListener('click', function() {
            var content = document.getElementById('post-search-content');
            content.style.display = content.style.display === 'none' || content.style.display === '' ? 'block' : 'none';
        });
    </script>
</body>
</html>
