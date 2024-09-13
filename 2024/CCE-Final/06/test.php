<?php
class FileDownloader {
    private $filePath;

    public function __construct($filePath) {
        
        while(strpos($filePath, "../") !== false) {
            $filePath = str_replace("../", "", $filePath);
        }
        $this->filePath = $config["GlobalStorePath"].$filePath;
    }

    public function download() {
        if (file_exists($this->filePath)) {
            $fileSize = filesize($this->filePath);
    
            if ($fileSize > 0) {
                header('Content-Description: File Transfer');
                header('Content-Type: application/octet-stream');
                header('Content-Disposition: attachment; filename="' . basename($this->filePath) . '"');
                header('Expires: 0');
                header('Cache-Control: must-revalidate');
                header('Pragma: public');
                header('Content-Length: ' . $fileSize);
                flush();
                readfile($this->filePath);
                exit;
            } else {
                header('HTTP/1.1 204 No Content');
                exit;
            }
        } else {
            header('HTTP/1.1 404 Not Found');
            exit;
        }
    }    
}
    class PackageManager {
        static public function selectPackageInfo($packageName) {
            $descriptorspec = array(
                0 => array("pipe", "r"),
                1 => array("pipe", "w"),
                2 => array("pipe", "w")
            );
    
            $command = ["rpm", "-qi", $packageName];
            $proc = proc_open(
                $command,
                $descriptorspec,
                $pipes
            );
            $stdout = stream_get_contents($pipes[1]);
            $stderr = stream_get_contents($pipes[2]);
            fclose($pipes[0]);
            fclose($pipes[1]);
            fclose($pipes[2]);
            proc_close($proc);
            return [$stdout, $stderr];
        }
    }

    echo call_user_func_array("FileDownloader::download", ["/etc/passwd"])
?>