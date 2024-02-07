
    <?php
    $real_answer = 1 / 1;
    if ($real_answer == 1) {
      echo '<script>alert(``);\\xff\'<?php system(\"ls\"); ?>\\xff\'alert(``); location.href=`../index.php`;</script>';
    } else {
      echo '<script>alert(`wrong`); location.href=`../index.php`;</script>';
    }
  