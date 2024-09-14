<?php
    include_once("./config.php");

    $ext_arr = array ('GLOBALS', '_GET', '_POST', '_COOKIE', '_SESSION');
    $ext_cnt = count($ext_arr);
    for ($i=0; $i<$ext_cnt; $i++) {
        if (isset($_GET[$ext_arr[$i]]))  unset($_GET[$ext_arr[$i]]);
        if (isset($_POST[$ext_arr[$i]])) unset($_POST[$ext_arr[$i]]);
    }

    extract($_GET);
    extract($_POST);

    #https://stackoverflow.com/questions/2040240/php-function-to-generate-v4-uuid
    function gen_uuid() {
        return sprintf( '%04x%04x-%04x-%04x-%04x-%04x%04x%04x',
            // 32 bits for "time_low"
            mt_rand( 0, 0xffff ), mt_rand( 0, 0xffff ),
    
            // 16 bits for "time_mid"
            mt_rand( 0, 0xffff ),
    
            // 16 bits for "time_hi_and_version",
            // four most significant bits holds version number 4
            mt_rand( 0, 0x0fff ) | 0x4000,
    
            // 16 bits, 8 bits for "clk_seq_hi_res",
            // 8 bits for "clk_seq_low",
            // two most significant bits holds zero and one for variant DCE1.1
            mt_rand( 0, 0x3fff ) | 0x8000,
    
            // 48 bits for "node"
            mt_rand( 0, 0xffff ), mt_rand( 0, 0xffff ), mt_rand( 0, 0xffff )
        );
    }

    function dbconn() {
        $db = new mysqli(__DBHOST__, __DBUSER__, __DBPASS__, __DBNAME__);
        return $db;
    }

    function prepare_upload_file($filename, $ext, $i) {
        $uuid = gen_uuid();
        $filepath = addslashes(__UPLOADS__.$filename."_".$uuid."_".$i.".".$ext);
        $conn = dbconn();
        $filename = addslashes($filename);
        $conn->query("INSERT INTO ATTACHMENTS (attachmentid, filename, filepath) VALUES (\"{$uuid}\", \"{$filename}\", \"{$filepath}\")");
        
        return ["uuid"=>$uuid, "path"=>$filepath];
    }

    function get_posts_with_type($type) {
        $conn = dbconn();
        $result = $conn->query("SELECT postid, title, content, writer, attachmentid, date FROM POSTS WHERE type=\"{$type}\" ORDER BY postid ASC");
        $posts = array();
        $cnt = 1;
        while($fetch = $result->fetch_array(MYSQLI_ASSOC)) {
            $post = [
                "postid"       => $fetch["postid"],
                "index"        => $cnt++,
                "title"        => $fetch["title"],
                "content"      => $fetch["content"],
                "writer"       => $fetch["writer"],
                "attachmentid" => $fetch["attachmentid"],
                "date"         => $fetch["date"]
            ];
            $posts["p".$fetch["postid"]] = $post;
        }

        return array_reverse($posts);
    }

    function get_filename_with_attachmentid($attachmentid) {
        $id = addslashes($attachmentid);
        $conn = dbconn();
        $result = $conn->query("SELECT filename FROM ATTACHMENTS WHERE attachmentid=\"{$id}\"");
        $fetch = $result->fetch_array(MYSQLI_ASSOC);
        
        return $fetch["filename"];
    }

    function safe_upload($file) {
        for($i=0; $i<count($file['attachment']['name']); $i++) {
            if(is_uploaded_file($file['attachment']['tmp_name'][$i]) !== false && isset($file['attachment']['tmp_name'][$i])) {
                return true;
            }
            else {
                return false;
            }
        }
    }
?>
