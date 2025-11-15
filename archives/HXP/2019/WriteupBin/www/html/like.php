<?php
include_once '../general.php';

$stmt = $db->prepare('INSERT INTO `like` (user_id, writeup_id) VALUES (?,?)');
$stmt->bind_param('ss', $_SESSION['id'], $_POST['id']);
$stmt->execute();

redirect('/show.php?id='. $_POST['id']);
