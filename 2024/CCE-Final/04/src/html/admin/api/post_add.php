<?php
session_start();

include '../config/db.php';

if(!isset($_SESSION['uid'])) {
    die('<script>alert("Login required");</script>');
}

if($_SESSION['uid'] !== 'admin') {
    die('<script>alert("You are not admin");</script>');
}

if(!isset($_POST['title']) || !isset($_POST['content'])) {
    die('<script>alert("Invalid request");</script>');
}

$title = $_POST['title'];
$content = $_POST['content'];
$author_id = $_SESSION['uid'];

if(strlen($title) > 254) {
    die('<script>alert("Invalid request");</script>');
}


$stmt = $dbcon->prepare("INSERT INTO posts (title, content, author_id) VALUES (?, ?, ?)");
$stmt->bind_param("sss", $title, $content, $author_id);
$stmt->execute();

?>