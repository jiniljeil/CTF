<?php 
    include 'config.php';
    $allowedIP = "127.0.0.1"; // 허용할 IP 주소나 IP 대역
    $clientIP = $_SERVER['REMOTE_ADDR']; // 클라이언트의 IP 주소 가져오기
    
    if ($clientIP !== $allowedIP) {
        // 차단할 IP 주소와 클라이언트의 IP 주소가 일치하지 않을 경우, 접근 거부
        header("HTTP/1.1 403 Forbidden");
        die("only localhost");
    }

    echo $flag;
?>
