<?php
function resJson($response){
    header('Content-Type: application/json');
    if (!$response) {
        $response = [
            'status' => false,
            'msg' => '데이터가 없습니다.'
        ];
    }
    echo json_encode($response);
    }
function resMsg($msg){
    header('Content-Type: application/json');
    $status=true;
    if (!$msg) {
      $msg="데이터가 없습니다.";
      $status=false;
    }
    $response = [
        'status' => $status,
        'msg' => $msg
    ];
    echo json_encode($response);
    }
function resErr($msg){
    header('Content-Type: application/json');
    if (!$msg) {
        $msg="데이터가 없습니다.";
    }
    $response = [
        'status' => false,
        'msg' => $msg
    ];
    echo json_encode($response);
    }
?>