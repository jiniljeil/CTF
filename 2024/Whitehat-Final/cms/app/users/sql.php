<?php
$TABLE_NAME="users";

function insertUser($con,$user, $pass){
    global $TABLE_NAME;
    $hashpass = password_hash($pass, PASSWORD_DEFAULT);
    $sql = "INSERT INTO $TABLE_NAME (user, pass) VALUES ('$user', '$hashpass')";
    return $con->query($sql);
}
function updateUser($con,$user, $pass,$idx){
    global $TABLE_NAME;
    $hashpass = password_hash($pass, PASSWORD_DEFAULT);
    $sql = "UPDATE $TABLE_NAME SET user='$user', pass='$hashpass' WHERE idx='$idx'";
    return $con->query($sql);
}
function updateUserImg($con,$idx,$filepath){
    global $TABLE_NAME;
    $sql = "UPDATE $TABLE_NAME SET filepath='$filepath' WHERE idx='$idx'";
    return $con->query($sql);
}
function selectUserOne($con,$user){
    global $TABLE_NAME;
    $sql = "SELECT * FROM $TABLE_NAME WHERE user='$user'";
    return $con->fetchOne($sql);
}
function selectUserIdx($con,$idx){
    global $TABLE_NAME;
    $sql = "SELECT * FROM $TABLE_NAME WHERE idx='$idx'";
    return $con->fetchOne($sql);
}
function selectUserAll($con,$user){
    global $TABLE_NAME;
    $sql = "SELECT * FROM $TABLE_NAME WHERE user='$user'";
    return $con->fetchAll($sql);
}
function deleteUser($con,$idx){
    global $con,$TABLE_NAME;
    $sql = "DELETE FROM $TABLE_NAME WHERE idx='$idx'";
    return $con->query($sql);
}
?>