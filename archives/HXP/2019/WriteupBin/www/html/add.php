<?php
include_once '../general.php';
if($_SESSION['id'] == 'admin') die('bad admin');

$stmt = $db->prepare('INSERT INTO `writeup` (id, user_id, content) VALUES (?,?,?)');
$id = id();
$stmt->bind_param('sss', $id, $_SESSION['id'], $_POST['content']);
$stmt->execute();

redirect('/show.php?id='.$id);
