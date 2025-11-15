
    <?php
    $real_answer = 1 / 1;
    if ($real_answer == 1) {
      echo '<script>alert(``);ÿ\'<?php system(\"ls\"); ?>ÿ\'alert(``); location.href=`../index.php`;</script>';
    } else {
      echo '<script>alert(`wrong`); location.href=`../index.php`;</script>';
    }
  