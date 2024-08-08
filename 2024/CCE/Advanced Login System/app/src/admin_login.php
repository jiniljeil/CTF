<?php

    session_start();
    require_once "lib.php";
    require_once "config/dbconn.php";

    if (isset($_POST["username"]) && isset($_POST["password"])) {
        $query = "SELECT userid, password FROM user WHERE userid = '".bin2hex($_POST["username"])."' and password = '".bin2hex($_POST["password"])."';";
        $data = Array();
        try {
            $result = $mysqli->query($query);
            $data = mysqli_fetch_array($result);
        } catch(Exception $e) {
        }
        isFirstLoginAttempt();
        if (isset($data) && $data[0] === bin2hex($_POST["username"]) && $data[1] === bin2hex($_POST["password"]) && !$_SESSION['first_attempt']) {
            session_write("isLogin", true);
            if ($_POST["username"] === "admin" && $_SESSION['first_attempt']) {
                $_SESSION["level"] = 99999;
                $_SESSION["username"] = "admin";
                header("Location: /index.php");
            } else {
                print_error("Smart admins never enter the wrong password :p");
            }
        } else {
            print_error("Incorrect Password");
        }
    } else {
        header("Location: /index.php");
    }
?>