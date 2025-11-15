<?php
    require_once("./config.php");
    session_start();
    
    if (!isset($_SESSION['dir'])) {
        $_SESSION['dir'] = random_bytes(4);
    }

    $SANDBOX = getcwd() . "/uploads/" . md5("supers@f3salt!!!!@#$" . $_SESSION['dir']);
    if (!file_exists($SANDBOX)) {
        mkdir($SANDBOX);
    }

    echo "Here is your current directory : " . $SANDBOX . "<br>";

    if (is_uploaded_file($_FILES['file']['tmp_name'])) {
        $filename = basename($_FILES['file']['name']);
        if (move_uploaded_file( $_FILES['file']['tmp_name'], "$SANDBOX/" . $filename)) {
            echo "<script>alert('File upload success!');</script>";
        }
    }
    if (isset($_GET['path'])) {
        if (file_exists($_GET['path'])) {
            echo "file exists<br><code>";
            if ($_SESSION['admin'] == 1 && $_GET['passcode'] === SECRET_CODE) {
                include($_GET['path']);
            }
            echo "</code>";
        } else {
            echo "file doesn't exist";
        }
    }
    if (isset($filename)) {
        unlink("$SANDBOX/" . $filename);
    }
?>

<form enctype='multipart/form-data' action='index.php' method='post'>
	<input type='file' name='file'>
	<input type="submit" value="upload"></p>
</form>