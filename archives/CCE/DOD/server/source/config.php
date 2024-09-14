<?php
    ini_set( 'display_errors', '0' );
    define('__CONTROLLER__', "/var/www/html/controller/");
    define('__UPLOADS__', "/var/www/html/uploads/");
    
    define('__DBUSER__', "cce2023");
    define('__DBPASS__', "cce2023!@#!");
    define('__DBNAME__', "cce2023");
    define('__DBHOST__', "db");

    $board_types = [
        "announcements" => [
            "title" => "Announcements",
            "explain" => "Provides recent information of Department of Defense, competition, and more."
        ],
        "news" => [
            "title" => "News",
            "explain" => "Provides news of Department of Defense, competition information, and more."
        ],
        "faq" => [
            "title" => "FAQ",
            "explain" => "Provides information so that you can easily find out what the people are asking a lot about Department of Defense."
        ],
        "support" => [
            "title" => "More",
            "explain" => "Provides the ability to send inquiries."
        ]
    ];
?>