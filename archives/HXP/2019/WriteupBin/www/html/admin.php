<?php
include_once '../general.php';
if($_SESSION['id'] == 'admin') die('bad admin');

$fp = stream_socket_client('tcp://127.0.0.1:1024', $errno, $errstr, 2);
if (!$fp) {
    die('admin seems to be too busy');
} else {
    fwrite($fp, $_POST['id']."\n");
    fclose($fp);
}

redirect('/');
