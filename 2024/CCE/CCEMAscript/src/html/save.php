<?php

require "config.php";

function err($message = "no hack") {
    die("<script>alert('$message'); location.href='/';</script>");
}

function post($name, $default = "") {
    $value = empty($_POST[$name]) ? $default  : $_POST[$name];

    $value = "$value";
    if (strpos($value, '*') !== FALSE) {
        die(":)");
    }
    return $value;
}

$color = post("color", "dark");
$name = post("name");
$desc = post("desc");
$nonce = md5(random_bytes(32));

if(strlen($color) > 9 || strlen($name) > 16 || strlen($desc) > 300)
    err();

$file_id = md5(random_bytes(32).$name.$desc);
$file_name = md5($file_id);
$sensitive_file_name = md5(md5($salt_prefix.$file_id.$salt_suffix));

$nonce = bin2hex($nonce);
$color = bin2hex($color);
$name = bin2hex($name);
$desc = bin2hex($desc);

$save_contents = "$nonce|$color|$name|$desc";

file_put_contents("../data/$file_name", $save_contents);
file_put_contents("../sensitive/$sensitive_file_name", $save_contents);
?>
<script>
    alert("Successfully saved!");
    location.href = "view.php?file_id=<?=$file_id?>";
</script>