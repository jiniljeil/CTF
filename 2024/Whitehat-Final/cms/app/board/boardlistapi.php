<?php require_once 'common.php'; 
global $dbcon;
$row1=selectBoardList($dbcon);
if(empty($row1)){
    echo "존재하지 않는 게시판 입니다";
    location("/");
    exit();
}
$dbcon->close();
resJson($row1);
?>
