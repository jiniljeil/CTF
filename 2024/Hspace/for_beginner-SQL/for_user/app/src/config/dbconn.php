<?php
    $server = 'sqli_mysql';
    $username = 'hspace';
    $password = 'hspace1234!';
    $db = 'hspace';
    $flag = file_get_contents("/flag.txt");

    $mysqli = mysqli_connect($server, $username, $password, $db);
    if($mysqli->connect_error){
        die("connect_error: " . $conn->connect_error);
    }
?>