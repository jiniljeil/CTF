<?php
session_start();
require_once "config/dbconn.php";

$userid = $_GET['userid'];
$password = $_GET['password'];

// admin' or if(substr(password,1,1)=='a',sleep(3),false)#
if(isset($userid) && isset($password)) {
    $query = "SELECT userid, password FROM user WHERE userid = '${userid}' and password = '".md5($password)."'";
    try {
        $result = $mysqli->query($query);
        $data = mysqli_fetch_array($result);
        if(isset($data) && $data[0] == "admin" && $data[1] === md5($password)){
            die($flag);
	    } else {
		    die("Wrong...");
	    }
    } catch(Exception $e) {
    }
} else {
    show_source(__FILE__);
}
?>