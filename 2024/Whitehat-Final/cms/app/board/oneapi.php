<?php require_once 'common.php'; 
$bname=$_GET['bname'];
$idx=$_GET['idx'];
$row1=selectBoardListOne($dbcon,$bname);
$res='';
if(empty($row1)){
    $res=[
        "status"=>false,
        "msg"=>"존재하지 않는 게시판 입니다!"
    ];
}
$row2=selectBoardOne($dbcon,$row1['bname'],$idx);
$dbcon->close();
if(empty($row2)){
    $res=[
        "status"=>false,
        "msg"=>"존재하지 않는 게시물 입니다!!"
    ];
}else{
    $res=$row2;
}
resJson($res);
exit;
?>
