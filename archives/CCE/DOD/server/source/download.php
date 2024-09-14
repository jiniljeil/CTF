<?php
    include("./util.php");
    $id = addslashes($attachment);
    $conn = dbconn();
    $result = $conn->query("SELECT filename, filepath FROM ATTACHMENTS WHERE attachmentid=\"{$id}\"");
    $fetch = $result->fetch_array(MYSQLI_ASSOC);

    if (isset($fetch["filepath"])) {
        /* 
        $filepath = $fetch["filepath"]; // UUID is filename
        header('Content-Description: File Transfer');
        header('Content-Type: application/octet-stream');
        header('Content-Disposition: attachment; filename='.basename($fetch["filename"]));
        header('Content-Transfer-Encoding: binary');
        header('Expires: 0');
        header('Cache-Control: must-revalidate, post-check=0, pre-check=0');
        header('Pragma: public');
        ob_clean();
        flush();
        readfile($filepath);
        exit;
*/
        echo "<script>alert('Under Construction'); history.back(-1);</script>";
    } else {
        echo "<script>alert('Something error'); history.back(-1);</script>";
    }
?>
