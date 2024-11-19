<?php
require_once "common.php";
global $dbcon;

if ($_POST['user'] === '' && $_POST['pass']==='') {
    echo 'The parameter is an empty string.';
    location("/users/signup.php");
}
$user=$_POST['user'];
$pass=$_POST['pass'];

$response='';
$ret=selectUserOne($dbcon,$user);
if(!empty($ret)){
    resErr("이미 존재 하는 유저 입니다!");
}
$ret=insertUser($dbcon,$user,$pass);
$dbcon->close();
if($ret){
    $response = [
        'status' => true,
        'msg' => 'good'
    ];
    
}else{
    $response = [
        'status' => false,
        'msg' => '사용자 등록 실패'
    ];
}
resJson($response);
exit();
?>