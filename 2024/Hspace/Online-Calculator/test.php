<?php
  $x = "'<?php system('ls');?>" * 10000000; $y = 10e99999999999 ;
  // $op = "/*"; 
  echo (float)($x);
  /* $op = ["<?php system('ls'); ?>","/","c"]; */
  // echo hex2bin('ff'); 
  // if(preg_match("/[^+\-*\/]/", $op)) {
  //   die("no hack");
  // }
  // $real_answer = "$x $op $y";
  // echo $real_answer;
?>