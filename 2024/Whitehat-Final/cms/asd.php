<?php
    $a = "\nasd'f\r\n!@#"; 
    echo preg_replace('/[^a-zA-Z0-9]/', '', $a) . "\n";
    // echo addslashes($a);
?>