<?php
session_start(['cookie_httponly' => true, 'cookie_samesite' => 'Strict']);
error_reporting(0);
ini_set('display_errors', 0);
ini_set('display_startup_errors', 0);

function id() {
    return bin2hex(random_bytes(8));
}

$nonce = base64_encode(id());

header('x-xss-protection: 1; mode=block');
header('X-Content-Type-Options: nosniff');
header('x-frame-options: DENY');
header('Referrer-Policy: no-referrer');
header("Feature-Policy: geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'none'; payment 'none'; usb 'none'; vr 'none'; encrypted-media 'none'");
header("Content-Security-Policy: default-src 'none'; script-src 'nonce-".$nonce."' https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.8.2/parsley.min.js; base-uri 'self'; form-action 'self'; frame-ancestors 'none'; require-sri-for script style;");

function redirect($url) {
    header("Location: ". $url);
    die();
}


if( ! isset($_SESSION['id'])) {
    $_SESSION = ['id' => id(), 'c' => id()];
}

$db = new mysqli('127.0.0.1', 'writeupbin', '__DB_PASSWORD__', 'writeupbin');
if ($db->connect_error) {
    die('db connection failed');
}
$db->set_charset('utf8mb4');


if( (strtoupper($_SERVER['REQUEST_METHOD']) == 'POST' || count($_POST))  && (! hash_equals($_SESSION['c'], $_POST['c']))) {
    die('csrf failed');
}

$stmt = $db->prepare('SELECT id FROM writeup where user_id = ? ORDER BY created_at');
$stmt->bind_param('s', $_SESSION['id']);
$stmt->execute();
$result = $stmt->get_result();
$writeups = mysqli_fetch_all($result, MYSQLI_ASSOC);