<?php

    $flag = file_get_contents("/flag");

    function is_safe_url($url) {
        // Allow only http(s)
        if(!preg_match('/^https?:\/\/.*$/', $url)){
            return false;
        }

        $host = parse_url($url, PHP_URL_HOST);
        if(!host) {
            return false;
        }

        $ip = gethostbyname($host);
        $ip = ip2long($ip);
        if($ip === false){
            return false;
        }
        
        $is_inner_ipaddress = ip2long('127.0.0.0') >> 24 == $ip >> 24 or 
            ip2long('10.0.0.0') >> 24 == $ip >> 24 or 
            ip2long('172.16.0.0') >> 20 == $ip >> 20 or 
            ip2long('192.168.0.0') >> 16 == $ip >> 16 ;
        if($is_inner_ipaddress){
            return false;
        }
        return true;
        
    }

    extract($_GET);
?>
