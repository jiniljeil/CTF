<?php
    error_reporting(0);


    function global_filter($input) {
        $pattern = '/[^a-zA-Z0-9\/\-]/';

        if (is_array($input)) {
            foreach ($input as $key => $value) {
                global_filter($value); 
            }
        } else {
            if (preg_match($pattern, $input)) {
                die("Invalid character");
            }
        }
    }

    function session_write($key, $value) {
        $_SESSION[$key] = $value;
        session_commit();
        session_start();
    }

    function isFirstLoginAttempt() {
        if (!isset($_SESSION["first_attempt"])) {
            session_write("first_attempt", true);
        } else {
            session_write("first_attempt", false);
        }
    }

    function print_error($sting) {
        die($sting);
    }

    function debug($arr) {
        var_dump(call_user_func($arr[0],$arr[1]));
    }

    global_filter($_REQUEST);
?>