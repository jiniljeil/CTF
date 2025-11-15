<?php
class FileUploader {
    private $uploadOk;
    private $targetDir;
    private $allowedFileTypes = ['jpg', 'jpeg', 'png', 'gif'];

    public function __construct($targetDir = "/uploads/") {
        $this->uploadOk = 1;
        $this->targetDir = $_SERVER['DOCUMENT_ROOT'] . $targetDir;
    }

    public function upload($file) {
        $fileName = basename($file["name"]);

        // Prevent directory traversal
        if (strpos($fileName, '..') !== false) {
            $this->uploadOk = 0;
        }

        $targetFile = $this->targetDir . $fileName;
        $imageFileType = strtolower(pathinfo($targetFile, PATHINFO_EXTENSION));

        // Check if the file is an image
        if (isset($_POST["submit"])) {
            $check = getimagesize($file["tmp_name"]);
            if ($check === false) {
                $this->uploadOk = 0;
            }
        }

        // Check file size
        if ($file["size"] > 5000000) {
            $this->uploadOk = 0;
        }

        // Check allowed file types
        if (!in_array($imageFileType, $this->allowedFileTypes)) {
            $this->uploadOk = 0;
        }

        if ($this->uploadOk == 0) {
            return false; // Upload failed
        } else {
            // Generate a unique file name
            $newFileName = $this->targetDir . bin2hex(random_bytes(16)) . '.' . $imageFileType;
            if (move_uploaded_file($file["tmp_name"], $newFileName)) {
                return $newFileName; // File uploaded successfully
            } else {
                return false; // Error uploading file
            }
        }
    }
}

// Usage example
// $uploader = new FileUploader();
// $result = $uploader->upload($_FILES["file"]);
// if ($result) {
//     echo "File uploaded successfully: " . htmlspecialchars($result);
// } else {
//     echo "File upload failed.";
// }
?>
