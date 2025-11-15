<?php 
require_once 'common.php'; 

if (!isset($_SESSION['status']) || !$_SESSION['status']) {
    location("/users/login.php");
    exit();
}
global $dbcon;
$owneridx=$_SESSION['idx'];
$role=$_SESSION['role'];
$title=$_POST['title'];
$content=$_POST['content'];
$bname=$_POST['bname'];
$res='';
// captcha 
// if (!($_POST['captcha_input'] === $_SESSION['captcha'])) {
//     location("/board/create.php");
//     exit;
// } 

$row1=selectBoardListOne($dbcon,$bname);

if(empty($row1)){
    $res=[
        "status"=>false,
        "msg"=>"존재 하지 않는 게시판 입니다!"
        // "location"=>"/board/list.php?bname={$row1['bname']}"
    ];
    resJson($res);
    exit();
}

if($role<$row1['role']){
    $res=[
        "status"=>false,
        "msg"=>"게시물을 작성할 권한이 없습니다!!"
        // "location"=>"/board/list.php?bname={$row1['bname']}"
    ];
    resJson($res);
    exit();
}
$row2=insertBoard($dbcon,$row1['bname'],$title,$content,$owneridx);
$dbcon->close();

if($row2){
    $res=[
        "status"=>true,
        "idx"=>$row2
    ];    
}else{
    $res=[
        "status"=>false,
        "msg"=>"게시물 생성 실패"
    ];
}
resJson($res);
exit();
?>