<?php
require "config.php";

$file_id = $_GET["file_id"];
$file_name = md5($file_id);

$file_content = @file_get_contents("../data/$file_name");

if(!$file_content) die();

list($nonce, $color, $name, $desc) = explode("|", $file_content);

$nonce = hex2bin($nonce);
$color = hex2bin($color);
$name = hex2bin($name);
$desc = hex2bin($desc);


if(strlen($color) > 9 || strlen($name) > 16 || strlen($desc) > 300)
    die();

$nonce_attr = "nonce=\"$nonce\"";
$fake_flag = str_repeat("*", strlen($flag));
?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Security-Policy" content="default-src 'none'; script-src 'nonce-<?=$nonce?>'">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/effect.css" rel="stylesheet"> 
    <script <?= $nonce_attr ?> id="safe_guard">
        let guard = 1337;
        // From SO 39963850
        setTimeout(function() {
            for (const key in window) {
                if(/^on/.test(key)) {
                    const eventType = key.substr(2);
                    window.addEventListener(eventType, (event) => {
                        event.stopImmediatePropagation();
                    });
                }
            }
            
            function safe_log() {
                let guard = 31337;
                eval("guard = guard + 1");
                
                if (guard == 31337) {
                    return;
                }

                eval("// Congratulations! This is a flag for you: <?= $fake_flag ?>");
            }
            
            safe_log();
        }, 2000);
        document.getElementById("safe_guard").remove();
    </script>
    <script <?= $nonce_attr ?>>
        console.log("Initalized: <?=$name?>'s demo page");
    </script>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6 content-area">
                <div class="bg-area">
                    <div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div><div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div><div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div><div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div><div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div><div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div><div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div><div class="base"><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div><div class="circ"></div></div>
                </div>
                <div class="form-container">
                    <h2 class="text-center text-<?= $color ?>">Demo Page</h2>
                    <p class="text-<?= $color ?>">
                        <?= $desc ?>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <script <?= $nonce_attr ?>>
        console.log("Finished :)");
    </script>
</body>
</html>