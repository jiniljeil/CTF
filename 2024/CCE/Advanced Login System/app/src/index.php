<?php

    session_start();
    require_once "lib.php";
    require_once "config/dbconn.php";

    if ($_SESSION["level"] > 900 && $_SESSION["username"] === "admin") {
        if ($_GET['mode'] === "debug_mode") {
            debug($_GET["debug"]);
        }
    } else if ($_SESSION["level"] == 1)  {
        session_write("level",2);
    } else {
        session_write("username", (isset($_REQUEST["username"]) && $_REQUEST["username"] !== "admin") ? $_REQUEST["username"] : "guest");
        session_write("level", 1);
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Session Info</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .session-info {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .session-info h1 {
            font-size: 24px;
            margin-bottom: 10px;
        }
        .session-info p {
            font-size: 18px;
            margin: 5px 0;
        }
        .session-info .username {
            color: #007BFF;
            font-weight: bold;
        }
        .session-info .level {
            color: #28A745;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="session-info">
        <h1>Session Information</h1>
        <p>Username: <span class="username"><?php echo htmlspecialchars($_SESSION["username"]); ?></span></p>
        <p>Level: <span class="level"><?php echo htmlspecialchars($_SESSION["level"]); ?></span></p>
    </div>
</body>
</html>