<?php

include_once "common.php";

if ($action === "phpinfo") {
    phpinfo();
}

if ($action === "read") {
    if(preg_match("/:\/\//", $arg)) {
        die("no hack");
    }
    $content = @file_get_contents("$arg");
    echo $content;
    die;
}

if ($action === "write") {
    $file_name = md5($arg.random_bytes(32));
    $cont = @file_get_contents($arg);
    file_put_contents("data/{$file_name}", $cont);
    die($file_name);
}

if ($action === "include") {
    if ( strpos($arg, "/") || strpos($arg, "\\"))
        die("no hack");

    // safe include
    $cont = @file_get_contents($arg);
    if (!$cont)
        die("no hack");
    
    if ( strpos($cont, "<") !== FALSE )
        die("no hack");
    
    require_once($arg);
}

if ($action === "ping") {
    list($url, $path) = @explode("|", $arg);

    if (strncmp($url, "http://", 7) !== 0)
        die("only http support");
    
    $host = substr($url, 7);
    if ($host != "cce.cstec.kr" && !preg_match("/\.cce\.cstec\.kr$/", $host)) 
        die("no hack");
    if (preg_match("/[^A-Za-z0-9-._%?=&\/]/", $path))
        die("no hack");

    // Double check
    $real_url = "http://{$host}/{$path}";
    $parse_url = parse_url($real_url); 
    
    if ($parse_url["host"] !== "cce.cstec.kr" && !preg_match("/\.cce\.cstec\.kr$/", $parse_url["host"])) 
        die("no hack2");
    if (preg_match("/[^A-Za-z0-9-._%?=&\/]/", $parse_url["path"]))
        die("no hack2");
    
    // send ping
    $fp = fsockopen($host, 80);
    if(!$fp)
        die("error");

    $body = "GET /{$path} HTTP/1.1\r\n";
    $body .= "Host: internal-ping.cce.local\r\n";
    $body .= "Connection: Close \r\n\r\n";

    fwrite($fp, $body);
    while (!feof($fp)) {
        echo str_replace("\n", "<br>", htmlentities(fgets($fp, 2048)));
    }
    fclose($fp);
}

if (!strncmp($action, "db_", 3)) {
    define("__DB_INDEX__", 1);
    include "db_action.php";
    die;
}
?>