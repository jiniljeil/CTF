<?php
$TPREFIX="board";
$LIST="list";
function selectBoardList($con){
    global $TPREFIX,$LIST;
    $sql="SELECT * FROM $TPREFIX$LIST";
    return $con->fetchAll($sql);
}
function selectBoardListOne($con,$bname){
    global $TPREFIX,$LIST;
    $sql="SELECT * FROM $TPREFIX$LIST WHERE bname='$bname'";
    return $con->fetchOne($sql);
}
function insertBoard($con,$bname,$title,$content,$owneridx){
    global $TPREFIX,$list;
    $sql = "INSERT INTO $TPREFIX$bname (title, content, owner) VALUES ('$title', '$content','$owneridx')";
    $ret=$con->query($sql);
    $sql="UPDATE $TPREFIX$LIST SET count =count +1 WHERE bname='$bname'";

    return $ret;
}
function selectBoardOne($con,$bname,$idx){
    global $TPREFIX;
    $sql = "SELECT * FROM $TPREFIX$bname WHERE idx='$idx'";
    return $con->fetchOne($sql);
}
function selectBoardAll($con,$bname){
    global $TPREFIX;
    $sql = "SELECT * FROM $TPREFIX$bname ORDER BY idx DESC ";
    return $con->fetchAll($sql);
}
function updateBoard($con,$bname,$owneridx,$idx,$title,$content){
    global $TPREFIX; # TARGET
    $sql = "UPDATE $TPREFIX$bname SET title='$title', content='$content' WHERE owner='$owneridx' AND idx='$idx'";
    return $con->query($sql);
}
function deleteBoard($con,$bname,$owneridx,$idx){
    global $TPREFIX,$list;
    $sql = "DELETE FROM $TPREFIX$bname WHERE owner='$owneridx' AND idx='$idx'";
    $ret=$con->query($sql);
    $sql="UPDATE $TPREFIX$LIST SET count =count -1 WHERE bname='$bname'";
    return $ret;
}
?>