<?php

    function check($find, $split) {
        for($i=0; $i < count($split); $i++) {
            $pharse = $split[$i];
            if(strpos($pharse, $find) !== false)
                return true;
        }
        return false;
    }

    include_once("./util.php");
    $split = explode(" ", $cmd);
    
    if(check("Hello", $split)) {
        $cmd = ["type" => "greet"];  
    } else if(check("announcement", $split)) {
        $cmd = ["type" => "announcements"];
    } else if(check("support", $split)) {
        $cmd = ["type" => "support"];
    } else if(check("deploy", $split) || check("inquiry", $split)) {
        $cmd = ["type" => "deploy"];
    } else if(check("history", $split)) {
        $cmd = ["type" => "history"];
    } else if(check("news", $split)) {
        $cmd = ["type" => "news"];
    } else {
        echo "<h1>Wrong request</h1>";
    }

    if(!array_key_exists("type", $cmd))
        $cmd = ["type" => "undefined"];

    $conn = dbconn();
    $type = addslashes($cmd['type']);
    $result = $conn->query("SELECT content FROM CHATS where type=\"{$type}\"");

    $fetch = $result->fetch_array(MYSQLI_ASSOC);
    
    if(isset($fetch["content"])) {
        echo $fetch["content"];
    } else {
        echo "<h1>Error response</h1>";
    }
?>
