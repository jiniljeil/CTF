<?php
include_once '../general.php';

if (!isset($_SERVER['PHP_AUTH_USER'])) {
    header('WWW-Authenticate: Basic realm="admin.py"');
    header('HTTP/1.0 401 Unauthorized');
    exit();
}

if ($_SERVER['PHP_AUTH_USER'] === 'admin' && password_verify($_SERVER['PHP_AUTH_PW'], '__ADMIN_HASH__')) {
    $_SESSION['id'] = 'admin';

    redirect('/show.php?id='. $_GET['id']);
}
