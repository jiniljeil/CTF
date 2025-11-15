<?php
function filefunc(){
    $uploadOk = 1;
    $target_dir =$_SERVER['DOCUMENT_ROOT']."/uploads/";
    $file_name = basename($_FILES["file"]["name"]);

    if (strpos($file_name, '..') !== false) {
        $uploadOk = 0;
    }
    $target_file = $target_dir . $file_name;
    
    $imageFileType = strtolower(pathinfo($target_file, PATHINFO_EXTENSION));
    if(isset($_POST["submit"])) {
        $check = getimagesize($_FILES["file"]["tmp_name"]);
        if($check !== false) {
            $uploadOk = 1;
        } else {
            $uploadOk = 0;
        }
    }
    if ($_FILES["file"]["size"] > 5000000) {
        $uploadOk = 0;
    }
    $allowedFileTypes = ['jpg', 'jpeg', 'png', 'gif'];
    if (!in_array($imageFileType, $allowedFileTypes)) {
        $uploadOk = 0;
    }
    // if (mime_content_type($tmpFile) !== "image/gif" || $fileExtension !== "gif") {
    //     echo "Sorry, only JPG, JPEG, PNG, GIF files are allowed.";
    //     $uploadOK = 0;
    // }
    
    if ($uploadOk == 0) {
        // echo "Sorry, your file was not uploaded.";
        
    } else {
        $newFileName = $target_dir . bin2hex(random_bytes(16)) . '.' . $imageFileType;
        if (move_uploaded_file($_FILES["file"]["tmp_name"], $newFileName)) {
            // echo "The file ". htmlspecialchars( basename( $_FILES["file"]["name"])). " has been uploaded.";
        } else {
            // echo "Sorry, there was an error uploading your file.";
            
        }
    }
    return $newFileName;
}
?>