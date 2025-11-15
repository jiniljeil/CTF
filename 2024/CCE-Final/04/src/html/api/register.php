<?php
    function createFolderIfNotExists($folderName, $userData, $secretData) {
        # folderName : ../ 
        $baseDir = '/app/user';
        $newFolderPath = $baseDir . '/' . $folderName;

        if (!file_exists($newFolderPath)) {
            if (mkdir($newFolderPath, 0777, true)) {
                echo "register success";
                $profilePath = $newFolderPath . '/profile.json';
                $jsonData = json_encode($userData, JSON_PRETTY_PRINT);
                $secretData = json_encode($secretData, JSON_PRETTY_PRINT);
                if (file_put_contents($profilePath, $jsonData) && file_put_contents($newFolderPath . '/pw.json', $secretData)) {
                    echo " and profile saved";
                    
                } else {
                    echo " but failed to save profile";
                }
            } else {
                die("register failed");
            }
        } else {
            echo "register failed";
        }
    }

    $name = $_POST['name'];
    $email = $_POST['email'];
    $pnum = $_POST['pnum'];
    $uid = $_POST['uid'];
    $upw = $_POST['upw'];
    $cupw = $_POST['cupw'];

    if(!$name || !$email || !$pnum || !$uid || !$upw || !$cupw) {
        die("register failed");
    }
    if($name === 'admin') {
        die("register failed");
    }
    if($upw !== $cupw) {
        die("register failed");
    }
    $userData = [
        'name' => $name,
        'email' => $email,
        'pnum' => $pnum,
        'uid' => $uid
    ];

    $secretData = [
        'upw' => $upw
    ];
    createFolderIfNotExists($uid, $userData, $secretData);
?>