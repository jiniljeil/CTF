<?php
session_start();

if (!isset($_SESSION['uid'])) {
    die('<script>alert("Login required"); location.href="/login.php"</script>');
}

if ($_SESSION['uid'] !== 'admin') {
    die('<script>alert("Admin required"); location.href="/index.php"</script>');
}

$USER_PATH = '/app/user/';
$files = scandir($USER_PATH);
$folders = array_diff($files, array('.', '..'));

foreach ($folders as $folder) {
    echo $folder . '<br>';
}

?>
