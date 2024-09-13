<?php

    echo pathinfo("a.php\x00.jpg", PATHINFO_EXTENSION)
?>