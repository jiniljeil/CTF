<?php

if(!defined("__DB_INDEX__")) die;
if($_SERVER["REMOTE_ADDR"] !== "127.0.0.1") die("Sorry, not yet opened");

include "db.php";

if ($action === "db_version") {
    $result = query("select @@version");
    $row = $result->fetch_array();

    die("$row[0]");
}

if ($action === "db_show_events") {
    $result = query("select name from events");
    
    $res = array();
    while(($row = $result->fetch_assoc())) {
        $res[] = $row['name'];
    }

    die(json_encode($res));
}

if ($action === "db_show_event_detail") {
    $event_name = $_GET["db_arg_event_name"];

    if(preg_match("/(as|,|where|limit)/i", $event_name))
        die("no hack");

    // sql 
    $event_name = addslashes($event_name);
    $result = @query("select * from events where name = '{$event_name}'");

    if(!$result)
        die("no hack");

    $row = $result->fetch_assoc();
    if($row["writer"] !== sha1($_GET["db_arg_writer"]))
        die("no hack");

    if($row["pw"] !== sha1($_GET["db_arg_password"]))
        die("no hack");

    die($row["body"]);
}