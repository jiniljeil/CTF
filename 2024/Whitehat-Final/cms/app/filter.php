<?php

if( is_array($_GET) ) {
    foreach($_GET as $k => $v) {
        if( is_array($_GET[$k]) ) {
            foreach($_GET[$k] as $k2 => $v2) {
                $_GET[$k][$k2] = addslashes($v2);
            }
        } else {
            $_GET[$k] = addslashes($v);
        }
    }
}
if( is_array($_POST) ) {
    foreach($_POST as $k => $v) {
        if( is_array($_POST[$k]) ) {
            foreach($_POST[$k] as $k2 => $v2) {
                $_POST[$k][$k2] = addslashes($v2);
            }
        } else {
            $_POST[$k] = addslashes($v);
        }
    }
}
if( is_array($_COOKIE) ) {
    foreach($_COOKIE as $k => $v) {
        if( is_array($_COOKIE[$k]) ) {
            foreach($_COOKIE[$k] as $k2 => $v2) {
                $_COOKIE[$k][$k2] = addslashes($v2);
            }
        } else {
            $_COOKIE[$k] = addslashes($v);
        }
    }
}
// if (is_array($_FILES)) {
    
//     foreach ($_FILES as $k => $v) {
//         // 파일 이름만 필터링
//         if(is_array($_FILES)){
//             foreach($_FILES[$k] as $k2 => $v2){
//                 // $_FILES[$k][$k2]=addslashes($v2);
//                 var_dump($v2);
//             }
//         }else{
//             // $_FILES[$k][$k2]=addslashes($v);
//             var_dump($v);
//         }
//     }
// }
// if (is_array($_FILES)) {
//     foreach ($_FILES as $k => $v) {
//         var_dump($k);
//         if (is_array($v)) {
//             foreach ($v as $k2 => $v2) {
//                 // 모든 필드에 대해 필터링
//                 // $_FILES[$k][$k2] = addslashes($v2);
                
//                 var_dump($v2);
//             }
//         }
//     }
// }

function xssfilter($data){ 
    if(empty($data)) 
        return $data; 
    if(is_array($data)){ 
        foreach($data as $key => $value){ 
            $data[$key] =xssfilter($value); 
        }
        return $data; 
    } 
    $data = str_replace(array('&amp;','&lt;','&gt;'), array('&amp;amp;','&amp;lt;','&amp;gt;'), $data); 
    $data = preg_replace('/(&#*\w+)[\x00-\x20]+;/', '$1;', $data); 
    $data = preg_replace('/(&#x*[0-9A-F]+);*/i', '$1;', $data); 
    if (function_exists("html_entity_decode")){
        $data = html_entity_decode($data); 
    }else{
        $trans_tbl = get_html_translation_table(HTML_ENTITIES);
        $trans_tbl = array_flip($trans_tbl);
        $data = strtr($data, $trans_tbl);
    }
    $data = preg_replace('#(<[^>]+?[\x00-\x20"\'])(?:on|xmlns)[^>]*+>#i', '$1>', $data);
    $data = preg_replace('#([a-z]*)[\x00-\x20]*=[\x00-\x20]*([`\'"]*)[\x00-\x20]*j[\x00-\x20]*a[\x00-\x20]*v[\x00-\x20]*a[\x00-\x20]*s[\x00-\x20]*c[\x00-\x20]*r[\x00-\x20]*i[\x00-\x20]*p[\x00-\x20]*t[\x00-\x20]*:#i', '$1=$2nojavascript...', $data); 
    $data = preg_replace('#([a-z]*)[\x00-\x20]*=([\'"]*)[\x00-\x20]*v[\x00-\x20]*b[\x00-\x20]*s[\x00-\x20]*c[\x00-\x20]*r[\x00-\x20]*i[\x00-\x20]*p[\x00-\x20]*t[\x00-\x20]*:#i', '$1=$2novbscript...', $data); 
    $data = preg_replace('#([a-z]*)[\x00-\x20]*=([\'"]*)[\x00-\x20]*-moz-binding[\x00-\x20]*:#', '$1=$2nomozbinding...', $data); 
    $data = preg_replace('#(<[^>]+?)style[\x00-\x20]*=[\x00-\x20]*[`\'"]*.*?expression[\x00-\x20]*\([^>]*+>#i', '$1>', $data); 
    $data = preg_replace('#(<[^>]+?)style[\x00-\x20]*=[\x00-\x20]*[`\'"]*.*?behaviour[\x00-\x20]*\([^>]*+>#i', '$1>', $data); 
    $data = preg_replace('#(<[^>]+?)style[\x00-\x20]*=[\x00-\x20]*[`\'"]*.*?s[\x00-\x20]*c[\x00-\x20]*r[\x00-\x20]*i[\x00-\x20]*p[\x00-\x20]*t[\x00-\x20]*:*[^>]*+>#i', '$1>', $data); 
    $data = preg_replace('#</*\w+:\w[^>]*+>#i', '', $data); 
    do{ 
        $old_data = $data; 
        $data = preg_replace('#</*(?:applet|b(?:ase|gsound|link)|embed|frame(?:set)?|i(?:frame|layer)|l(?:ayer|ink)|meta|object|s(?:cript|tyle)|title|xml)[^>]*+>#i', '', $data); 
    } 
    while ($old_data !== $data); 
    return $data; 
} 

function strandint($input){
    return preg_replace('/[^a-zA-Z0-9]/', '', $input);
}
function intandint($input) {
    return preg_replace('/[^0-9]/', '', $input);
}


if(isset($_POST['user'])){
    $_POST['user']=strandint($_POST['user']);
}
if(isset($_POST['pass'])){
    $_POST['pass']=strandint($_POST['pass']);
}
if(isset($_POST['idx'])){
    $_POST['idx']=intandint($_POST['idx']);
}
if(isset($_GET['user'])){
    $_GET['user']=strandint($_GET['user']);
}
if(isset($_GET['idx'])){
    $_GET['idx']=intandint($_GET['idx']);
}
if(isset($_GET['bname'])){
    $_GET['bname']=xssfilter($_GET['bname']);
}
if(isset($_POST['bname'])){
    $_POST['bname']=xssfilter($_POST['bname']);
}
if(isset($_POST['toname'])){
    $_POST['toname']=xssfilter($_POST['toname']);
}
if(isset($_GET['toname'])){
    $_GET['toname']=xssfilter($_GET['toname']);
}

?>