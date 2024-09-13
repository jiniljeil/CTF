<?php
    session_start();
    if(!isset($_SESSION['uid'])) {
        header('Location: /login.php');
    }

    include '../config/db.php';

    function generateRandomString($length = 10) {
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $charactersLength = strlen($characters);
        $randomString = '';
    
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[random_int(0, $charactersLength - 1)];
        }
    
        return $randomString;
    }

    $name = $_POST['name'];
    $content = $_POST['content'];
    $author = $_SESSION['uid'];

    if(!$name || !$content) {
        die("신고자 및 신고 대상자를 모두 입력해주세요.");
    }

    $maxFileSize = 3 * 1024 * 1024; // 5MB

    $evidence = $_FILES['evidence']; # php\x00
    
    $random_name = generateRandomString();
    if($evidence['size'] > 0) {
        if($evidence['size'] > $maxFileSize) {
            die("파일 크기는 3MB 이하여야 합니다.");
        }
        // file upload
        if($evidence['error'] === 0) {
            $evidencePath = '/app/report/' . $name . '_' . $random_name.'_'.$evidence['name']; 
            move_uploaded_file($evidence['tmp_name'], $evidencePath);
        }

        $ext = pathinfo($evidencePath, PATHINFO_EXTENSION);
        $stmt = $dbcon->prepare("INSERT INTO report (name, content, evidence, author_id) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $name, $content, $evidencePath, $author);
        $stmt->execute();
    
        if($ext !== 'zip'){
            die("zip 파일만 업로드 가능합니다.");
        }
    
        echo "<script>alert('신고가 완료되었습니다. 신고번호 : ".$random_name."');history.go(-1)</script>";
    } else {
        $stmt = $dbcon->prepare("INSERT INTO report (name, content, author_id) VALUES (?, ?, ?)");
        $stmt->bind_param("sss", $name, $content, $author);
        $stmt->execute();
        echo "<script>alert('신고가 완료되었습니다. 신고번호 : ".$random_name."');history.go(-1)</script>";
    }
?>