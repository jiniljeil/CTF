<?php 
require_once 'common.php'; 
if (!isset($_SESSION['status']) || !$_SESSION['status']) {
    location("login.php");
    exit();
}

global $dbcon;
$user=$_POST['user'];
$pass=$_POST['pass'];
$idx=$_SESSION['idx'];
// captcha 
// if (!($_POST['captcha_input'] === $_SESSION['captcha'])) {
//     location("/board/create.php");
//     exit;
// }

$row1=updateUser($dbcon,$user,$pass,$idx);

// 파일 있는지 확인 if
if(!empty($_FILES) && isset($_FILES['file']) && $_FILES['file']['error'] === UPLOAD_ERR_OK){
    $filename=filefunc();
    // 업로드 실패
    if($filename===null){
        die();
    }
    $row2=updateUserImg($dbcon,$idx,$filename);
}
$dbcon->close();

if($row1){
    $response = [
        'status' => true,
        'message' => 'Update'
    ];
    session_destroy();
}else{
    $response = [
        'status' => false,
        'message' => 'Fali'
    ];  
}
resJson($response);
exit();
?>