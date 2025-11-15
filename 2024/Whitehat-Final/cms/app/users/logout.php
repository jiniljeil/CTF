<?php
require_once 'common.php';
session_destroy();
location("login.php");
exit();
?>