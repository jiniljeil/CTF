<?php

require_once "../lib/config.php";

if(!is_login() || !is_admin()) header("Location: ./login.php");


$path = $_GET["path"];

$file = new FileDownloader($path);
$file->download();
?>