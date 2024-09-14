<?php
    if(!isset($title) || !isset($content) || !isset($password))
        die("<script>alert('Something error'); history.back(-1);</script>");
    
    if(strlen($password) < 8)
        die("<script>alert('Password is too short!'); history.back(-1);</script>");
    $title = addslashes($title);
    $content = addslashes($content);
    $password = md5($password);

    $conn = dbconn();

    if(isset($_FILES)) {
        $countfiles = count($_FILES['attachment']['name']);
        if(safe_upload($_FILES)) {
            for($i = 0; $i < $countfiles; $i++) {
                $filename = $_FILES['attachment']["name"][$i];
                $ext = pathinfo($filename, PATHINFO_EXTENSION);

                $filename = substr($filename, 0, strlen($filename) - strlen(".".$ext));
                if(preg_match("/ph.*|hta.*/", $ext) || getimagesize($_FILES["attachment"]["tmp_name"][$i]) === false )
                    die("<script>alert('Not allowed file format!'); history.back(-1);</script>");

                if ($_FILES["attachment"]["size"][$i] > 2 * 1024 * 1024) 
                    die("Sorry, the file is too large. Please upload a file under 2MB.");
                $attachment_info = prepare_upload_file($filename, $ext, $i);
                rename($_FILES["attachment"]["tmp_name"][$i], $attachment_info["path"]);
                @chmod($attachment_info["path"], 0777);
            }
            $conn->query("INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date)
                VALUES (NULL, \"support\", \"{$title}\", \"{$content}\", \"{$attachment_info["uuid"]}\", \"{$password}\", NOW());");
            $insert_id = mysqli_insert_id($conn);
        }
        else {
            die("<script>alert('Something wrong'); history.back(-1);</script>");
        }
    }
    else {
        $conn->query("INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date)
       VALUES (NULL, \"support\", \"{$title}\", \"{$content}\", NULL, \"{$password}\", NOW());");
       $insert_id = mysqli_insert_id($conn);
    }

?>


<script>alert('SUCCESS!!'); id = '<?=$insert_id;?>'; location.href='/?page=list&type=support';</script>