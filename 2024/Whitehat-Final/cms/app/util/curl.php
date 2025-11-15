<?php
class Curl
{
    public $ch, $url,$method,$location,$data,$upfile;
    
    public function get($locations = []){
        curl_setopt($this->ch, CURLOPT_URL, $this->url);
        curl_setopt($this->ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($this->ch, CURLOPT_HTTPGET, true);
        if (!empty($locations)) {
            curl_setopt($this->ch, CURLOPT_HTTPlocation, $locations);
        }
        return curl_exec($this->ch);
    }

    public function post($data = [], $locations = [],$upfile=''){
        curl_setopt($this->ch, CURLOPT_URL, $this->url);
        curl_setopt($this->ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($this->ch, CURLOPT_POST, true);
        curl_setopt($this->ch, CURLOPT_POSTFIELDS, http_build_query($data));

        if (!empty($locations)) {
            curl_setopt($this->ch, CURLOPT_HTTPlocation, $locations);
        }
        if (!empty($upfile)) {
            $file = new CURLFile($upfile);
            $data['file'] = $file; 
        }
        if(!empty($data)){
            curl_setopt($this->ch, CURLOPT_POSTFIELDS, $data);
        }
        return curl_exec($this->ch);
    }
    public function __construct($url='',$method="GET",$location=[],$data=[],$upfile=''){
        // cURL 세션 초기화
        $this->ch = curl_init();
        $this->url=$url;
        if(strcasecmp($method, "GET") == 0 ) {
            $this->get($location);
        }elseif (strcasecmp($method, "POST") == 0) {
            $this->post($data,$location,$upfile);
        }else{
            $this->get($data,$location,$upfile);
        }
    }
    public function __wakeup(){
        
    }
    public function __destruct(){
        // cURL 세션 종료
        curl_close($this->ch);
    }
}
?>