<?php
session_start();

$captcha_code = '';
$characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
$characters_length = strlen($characters);
for ($i = 0; $i < 6; $i++) {
    $captcha_code .= $characters[rand(0, $characters_length - 1)];
}

// 세션에 캡챠 코드 저장
$_SESSION['captcha'] = $captcha_code;

// 캡챠 이미지 생성
$width = 120;
$height = 40;
$image = imagecreate($width, $height);

// 배경색 설정 (흰색)
$background_color = imagecolorallocate($image, 255, 255, 255);

// 텍스트 색상 설정 (검은색)
$text_color = imagecolorallocate($image, 0, 0, 0);

// 폰트 크기와 위치 설정
$font_size = 3;  // 기본 폰트 크기
$x = 5;
$y = 5;

// 이미지에 텍스트 그리기 (기본 폰트 사용)
imagestring($image, $font_size, $x, $y, $captcha_code, $text_color);

// 이미지 출력
header('Content-type: image/png');
imagepng($image);
imagedestroy($image);
?>