<?php
require_once "../lib/config.php";

if(!is_login() || !is_admin()) header("Location: ./login.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_FILES['file'])) {
    $file = $_FILES['file'];

    if ($file['error'] !== UPLOAD_ERR_OK) {
        alert("업로드 에러!", "./index.php");
    }

    $ext = strtolower(pathinfo($file['name'], PATHINFO_EXTENSION));
    if ($ext !== 'xlsx') {
        alert("허용되지 않은 확장자입니다.", "./index.php");
    }

    // 임시 파일에서 /tmp 디렉토리로 이동
    $destination = "/tmp/" . generate_uuid() . ".xlsx";

    // 파일 이동
    if (move_uploaded_file($file['tmp_name'], $destination)) {
        if ($_GET['contentType'] === "json") {
            header("Content-Type: application/json");
            echo json_encode(Array(
                "path" => $destination,
                "size" => $file["size"],
                "type" => $file["type"],
                "original_name" => $file["name"]
            ));
        } else {
            var_dump(Array(
                "path" => $destination,
                "size" => $file["size"],
                "type" => $file["type"],
                "original_name" => $file["name"]
            ));
            alert("업로드 완료!!", "./index.php");
        }
    } else {
        alert("업로드 에러!", "./index.php");
    }
}
?>