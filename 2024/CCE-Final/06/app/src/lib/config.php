<?php

error_reporting(0);

ini_set('session.save_path', '/var/tmp/');
include(__DIR__.'/function.inc.php');
include(__DIR__.'/class.inc.php');
include(__DIR__.'/db.inc.php');
$dbcon = mysqli_connect("cce_mysql", "cce", "cce", "cce");
define('__SALT__', '1Na!!ji192.13931293(!!!!');

$config = [];

session_start();
?>