<?php 
    include 'config.php';
    if(isset($_GET['url']) && !is_safe_url($url)){
        die('url is not safe');
    } 
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Proxy Server</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 30px;
        }
        #submit-button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <h1>Proxy Server</h1>
                <form method="get" action="">
                    <div class="form-group">
                        <label for="url-input">Enter URL:</label>
                        <input type="text" class="form-control" id="url-input" name="url" placeholder="Enter URL here" pattern="^(https?://)(?!localhost)[\w-]+(\.[\w-]+)+([\w.,@?^=%&:/~+#-]*[\w@?^=%&/~+#-])?$" required>
                    </div>
                    <button type="submit" class="btn btn-primary" id="submit-button">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <?php
    // 프록시 서버 설정
    $proxy_port = 80;

    // 클라이언트 요청 받기
    if (isset($_GET['url'])) {

        $url_parts = parse_url($url);
        $proxy = $url_parts['scheme'] . '://' . $url_parts['host'];
        if (isset($url_parts['port'])) {
            $proxy .= ':' . $url_parts['port'];
        }
        $request = isset($url_parts['path']) ? $url_parts['path'] : '/';
        if (isset($url_parts['query'])) {
            $request .= '?' . $url_parts['query'];
        }

        // 서버로 요청 보내기
        $ch = curl_init();
        $url = $proxy . $request;
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_PORT, $proxy_port);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($ch);
        curl_close($ch);

        // 서버 응답 클라이언트에게 반환
        echo '<div class="container"><div class="row"><div class="col-md-6 col-md-offset-3"><h2>Server Response</h2><pre>' . htmlspecialchars($response) . '</pre></div></div></div>';
    }
    ?>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
