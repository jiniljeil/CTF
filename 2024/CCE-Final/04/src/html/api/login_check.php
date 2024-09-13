<?php
    session_start();
    function createFolderIfNotExists($uid,$upw) {
        $baseDir = '/app/user';
        $newFolderPath = $baseDir . '/' . $uid;

        if (file_exists($newFolderPath)) {
            $secretFilePath = $newFolderPath . '/pw.json';
            if(file_exists($secretFilePath)) {
                $secretData = json_decode(file_get_contents($secretFilePath), true);
                if($secretData['upw'] === $upw) {
                    $_SESSION['uid'] = $uid;
                    echo '<script>location.href="/report"</script>';
                } else {
                    die("login failed");
                }
            } else {
                die("login failed");
            }
        } else {
            die("login failed");
        }
    }

    $uid = $_POST['uid'];
    $upw = $_POST['upw'];

    if(!$uid || !$upw) {
        die("login failed");
    }

    createFolderIfNotExists($uid, $upw);

?>