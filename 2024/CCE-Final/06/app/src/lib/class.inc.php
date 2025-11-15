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

class Logger {
    private $logFile;

    public function __construct($filePath) {
        $this->logFile = $filePath;
    }

    public function log($message) {
        $timeStamp = date('Y-m-d H:i:s');
        file_put_contents($this->logFile . ".log", "$timeStamp - $message\n", FILE_APPEND);
    }
}

class NetworkInfo {
    public function getServerIP() {
        return $_SERVER['SERVER_ADDR'];
    }

    public function getNetworkInterfaces() {
        return net_get_interfaces();
    }
}

class ResourceMonitor {
    public function _getServerLoadLinuxData() {
        if (is_readable("/proc/stat")) {
            $stats = @file_get_contents("/proc/stat");

            if ($stats !== false) {
                $stats = preg_replace("/[[:blank:]]+/", " ", $stats);
                $stats = str_replace(array("\r\n", "\n\r", "\r"), "\n", $stats);
                $stats = explode("\n", $stats);
                foreach ($stats as $statLine)
                {
                    $statLineData = explode(" ", trim($statLine));
                    if
                    (
                        (count($statLineData) >= 5) &&
                        ($statLineData[0] == "cpu")
                    )
                    {
                        return array(
                            $statLineData[1],
                            $statLineData[2],
                            $statLineData[3],
                            $statLineData[4],
                        );
                    }
                }
            }
        }
        return null;
    }

    public function getServerLoad()
    {
        $load = null;

        if (stristr(PHP_OS, "win")) {
            $cmd = "wmic cpu get loadpercentage /all";
            @exec($cmd, $output);

            if ($output) {
                foreach ($output as $line) {
                    if ($line && preg_match("/^[0-9]+\$/", $line)) {
                        $load = $line;
                        break;
                    }
                }
            }
        } else {
            if (is_readable("/proc/stat")) {
                $statData1 = _getServerLoadLinuxData();
                sleep(1);
                $statData2 = _getServerLoadLinuxData();

                if ((!is_null($statData1)) && (!is_null($statData2))) {
                    $statData2[0] -= $statData1[0];
                    $statData2[1] -= $statData1[1];
                    $statData2[2] -= $statData1[2];
                    $statData2[3] -= $statData1[3];

                    $cpuTime = $statData2[0] + $statData2[1] + $statData2[2] + $statData2[3];
                    $load = 100 - ($statData2[3] * 100 / $cpuTime);
                }
            }
        }
        return $load;
    }
}

class AuthManager {
    public function hashPassword($password) {
        return password_hash($password, PASSWORD_DEFAULT);
    }

    public function verifyPassword($password, $hash) {
        return password_verify($password, $hash);
    }

    public function generateAuthToken() {
        return bin2hex(random_bytes(16));
    }
}

class JobManager{
    public $callback = null;
    public $allowCallbackList = ["FileDownloader::", "PackageManager::", "Logger::", "NetworkInfo::", "ResourceMonitor::", "AuthManager::"];
    public $arg = [];
    private $jobs = [];
    public function __construct($job, $callback, $arg) {
        $this->add_Job($job);
        $this->callback = $callback;
        $this->arg = $arg;
    }

    public function add_Job($job) {
        if(is_string($job) && !empty($job)) {
            $this->jobs[] = $job;
        } else {
            throw new InvalidArgumentException("Invalid job provided");
        }
    }

    public function flush() {
        $this->callback = null;
        $this->arg = null;
    }

    public function __destruct() {
        foreach ($this->allowCallbackList as $ck) {
            if(startsWith($this->callback, $ck)) {
                call_user_func_array($this->callback, $this->arg);
            }
        }
    }
}


?>