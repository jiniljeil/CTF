<?php
error_reporting(E_ALL&~E_WARNING);  
error_reporting(E_ALL&~E_NOTICE);  
$dbcon = new mysqli('db', 'root', 'root', 'DoIRM');
if(!$dbcon) {
    die('DB Connect Error');
}

if($dbcon->connect_error){
    $dbcon = new mysqli('127.0.0.1', 'root', 'root', 'DoIRM');
    if(!$dbcon) {
        die('DB Connect Error');
    }
}

?>