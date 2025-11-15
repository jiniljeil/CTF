<?php
require_once 'common.php';
$response='';
if (!isset($_SESSION['status']) || !$_SESSION['status']) {
    location("login.php");
    exit();
}
global $dbcon;
$user=$_SESSION['idx'];
$response='';
$row=selectUserIdx($dbcon,$user);
$dbcon->close();
if(!$row){
    $response = [
        'status' => false,
        'msg' => '데이터가 없습니다.'
    ];
}else{
    $imgpath=''; 
    if(file_exists($row['filepath'])){ # TARGET  phar:///var/www/html/uploads/0c079ce53f4531a9322af267e41c3689.jpg
        $imgpath='/uploads/'.basename($row['filepath']);
    }else{
        $imgpath="/static/userimg.png";
    }
    $response=[
        'idx'=>$row['idx'],
        'role'=>$row['role'],
        'user'=>$row['user'],
        'filepath'=>$imgpath
    ];
}
resJson($response);
exit;
?>