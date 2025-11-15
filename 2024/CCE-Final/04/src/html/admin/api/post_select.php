<?php
session_start();

if (!isset($_SESSION['uid'])) {
    die('<script>alert("Login required");</script>');
}

if ($_SESSION['uid'] !== 'admin') {
    die('<script>alert("You are not admin");</script>');
}

include '../config/db.php';

if (!isset($_POST['select'])) {
    die('<script>alert("Invalid request");</script>');
}

$keyword = $_POST['select'];

# [DEBUG] SQL 
$query = "SELECT * FROM posts WHERE title like '%$keyword%' OR content like '%$keyword%'";
$result = $dbcon->query($query);

if ($result->num_rows > 0) {
    $rows = array();
    while ($row = $result->fetch_assoc()) {
        $rows[] = $row;
    }
    echo json_encode($rows);
} else {
    die('<script>alert("Invalid request");</script>');
}

?>