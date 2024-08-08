<?php

function connect() {
    $dbserver = "db";
    $dbconn = mysqli_connect($dbserver, "root", "veryveryhardpassword", "ccend");
    return $dbconn;
}

function query($sql) {
    // Special thanks to gnuboard :)
    $sql = trim($sql);
    $sql = preg_replace("#^select.*from.*[\s\(]+union[\s\)]+.*#i ", "select XXX", $sql);
    $sql = preg_replace("#^select.*from.*where.*`?information_schema`?.*#i", "select XXX", $sql);

    $conn = connect();
    return $conn->query($sql);
}