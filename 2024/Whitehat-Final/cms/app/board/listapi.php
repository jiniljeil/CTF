<?php require_once 'common.php'; 
global $dbcon;
// 게시판 목록에 있는 것만 조회 할수 있게
$bname=$_GET['bname'];
$row1=selectBoardListOne($dbcon,$bname);
$res='';
if(empty($row1)){
    $res=[
        "status"=>false,
        "msg"=>"존재하지 않는 게시판 입니다!"
    ];
}
$row2=selectBoardAll($dbcon,$row1['bname']);
$dbcon->close();
if(empty($row2)){
    $res=[
        "status"=>false,
        "msg"=>"게시물이 없습니다!!"
    ];
}else{
    $res=$row2;
}
resJson($res);
exit();
?>
