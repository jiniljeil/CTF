<?php
    $server = 'cce_mysql';
    $username = 'cce';
    $password = 'cce';
    $db = 'cce';

    $mysqli = mysqli_connect($server, $username, $password, $db);
    if($mysqli->connect_error){
        die("connect_error: " . $conn->connect_error);
    }
?>