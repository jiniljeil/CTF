<?php
    $page = $_GET['page'];
    if(isset($page)){
        include("./data/".$page);
    } else {
        header("Location: /?page=1");
    }
?>