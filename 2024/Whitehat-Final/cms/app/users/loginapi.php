<?php
require_once 'common.php';
global $dbcon;
if (empty($_POST['user']) || empty($_POST['pass'])) {
    echo 'The parameter is an empty string.';
    location("/users/login.php");
    exit(); 
}

$user = $_POST['user'];
$pass = $_POST['pass'];

$row = selectUserOne($dbcon, $user);
$dbcon->close();

if (!$row) {
    location("/users/login.php");
    exit();
}

$res='';
if (password_verify($pass, $row['pass'])) {
    session_regenerate_id(true);
    $_SESSION['status'] = true;
    $_SESSION['idx'] = $row['idx'];
    $_SESSION['user'] = $row['user'];
    $_SESSION['role'] = $row['role'];
    $_SESSION['dbname'] = $row['dbname'];
    $res=[
        'status'=>true,
        'msg'=>'로그인 성공'
    ];
} else {
    $res=[
        'status'=>false,
        'msg'=>'로그인 실패'
    ];
}
resJson($res);
exit(); 
?>