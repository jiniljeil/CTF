<?php 
require_once 'common.php'; 
if (!isset($_SESSION['status']) || !$_SESSION['status']) {
    location("login.php");
    exit();
}

global $dbcon ;
$owneridx=$_SESSION['idx'];
$role=$_SESSION['role'];
$idx=$_POST['idx'];
$bname=$_POST['bname'];
$toname=$_POST['toname'];
// captcha 
// if (!($_POST['captcha_input'] === $_SESSION['captcha'])) {
//     location("/board/create.php");
//     exit;
// } 
// echo("test");
// die;
$orgtb=selectBoardListOne($dbcon,$bname);

if(empty($orgtb)){
    $res=[
        "status"=>false,
        "msg"=>"존재 하지 않는 게시판 입니다!"
        // "location"=>"/board/list.php?bname={$row1['bname']}"
    ];
    resJson($res);
    exit();
}

if($role<$orgtb['role']){
    $res=[
        "status"=>false,
        "msg"=>"게시물을 작성할 권한이 없습니다!!"
        // "location"=>"/board/list.php?bname={$row1['bname']}"
    ];
    resJson($res);
    exit();
}

$orgboard=selectBoardOne($dbcon,$orgtb['bname'],$idx);
$title=isset($_POST['title']) ? $_POST['title']: $orgboard['title'];
$content=isset($_POST['content']) ? $_POST['content']: $orgboard['content'];

$tb=$orgtb['bname'];
if(($orgtb['bname'] === $toname)){
    $row2=updateBoard($dbcon,$orgtb['bname'],$owneridx,$idx,$title,$content);
    $row2=$idx;
}else{
    $totb=selectBoardListOne($dbcon,$toname);
    $row2=insertBoard($dbcon,$totb['bname'],$title,$content,$owneridx);
    $row3=deleteBoard($dbcon,$orgtb['bname'],$owneridx,$idx);
    if($row2){ 
        $idx=$row2;
        $tb=$totb['bname'];
    }
}
if($row2){
    $res=["status"=>true,"idx"=>$idx,"bname"=>$tb];
}
$dbcon->close();
resJson($res);
exit();
?>