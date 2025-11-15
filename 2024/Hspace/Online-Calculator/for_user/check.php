<?php
  $x = $_POST["x"];
  $y = $_POST["y"];
  $op = $_POST["op"];
  $message = $_POST["message"];
  $user_answer = $_POST["user_answer"];

  if(!$x || !$y || !$op || !$message || !$user_answer) {
    die("something wrong");
  }

  //validate values
  $x = (float)($x);
  $y = (float)($y);
  
  if(preg_match("/[^+\-*\/]/", $op)) {
    die("no hack");
  }
  
  $message = addslashes($message);
  $user_answer = (float)($user_answer);
  $code = "
    <?php
    \$real_answer = $x $op $y;
    if (\$real_answer == $user_answer) {
      echo '<script>alert(`$message`); location.href=`../index.php`;</script>';
    } else {
      echo '<script>alert(`wrong`); location.href=`../index.php`;</script>';
    }
  ";

  $fn = "calc/".sha1(random_bytes(16)).".php";
  file_put_contents($fn, $code);

  header("Location: $fn");
?>