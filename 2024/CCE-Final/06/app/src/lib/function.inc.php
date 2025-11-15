<?php

function gap_time($start_date, $end_date) {
	
	$start_time = strtotime($start_date);
	$end_time = strtotime($end_date);

	$diff = $end_time - $start_time;

	$hours = floor($diff/3600);

	$diff = $diff-($hours*3600);

	$min = floor($diff/60);

	$sec = $diff - ($min*60);

	return sprintf("%02d:%02d:%02d", $hours, $min, $sec); 

}

function startsWith($haystack, $needle) {
    return $needle === "" || strrpos($haystack, $needle, -strlen($haystack)) !== false;
}

function generate_uuid()
{
    $b = random_bytes(16);
    $b[6] = chr(ord($b[6]) & 0x0f | 0x40);
    $b[8] = chr(ord($b[8]) & 0x3f | 0x80);
    return vsprintf('%s%s-%s-%s-%s-%s%s%s', str_split(bin2hex($b), 4));
}

function alert($msg, $location) {

    $msg = htmlspecialchars($msg, ENT_QUOTES);
    $location = htmlspecialchars($location, ENT_QUOTES);

    if($location === 'back'){
        exit('<script>alert("' . $msg. '"); history.go(-1);</script>');
    }

    exit('<script>alert("' . $msg . '"); location.href="' . $location . '";</script>');
}

function valid_str($str, $type) {
    switch (trim($type)) {
        case 'memberid': 
            if(strlen($str) < 4 || strlen($str) > 10 || preg_match('/[^a-z0-9_]/', $str)) return false;
            break;
        case 'username':
            if(strlen($str) < 4 || strlen($str) > 20 || preg_match('/[^a-z0-9_]/', $str)) return false;
            break;
        default:
            return false;
            break;
    }
    return true;
}

function is_login() {
    if($_SESSION['uuid']) return true;
    return false;
}

function is_admin() {
    if($_SESSION['is_admin']) return true;
    return false;
}

function clean_html($str) {
    return htmlspecialchars($str, ENT_QUOTES);
}

function clean_sql($str) {
    return addslashes($str);
}

function now() {
    return date("Y-m-d H:i:s");
}

?>