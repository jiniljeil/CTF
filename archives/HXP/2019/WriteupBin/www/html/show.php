<?php
include_once '../general.php';

$stmt = $db->prepare('SELECT id, content FROM `writeup` WHERE `id` = ?');
$stmt->bind_param('s', $_GET['id']);
$stmt->execute();
$writeup = mysqli_fetch_all($stmt->get_result(), MYSQLI_ASSOC)[0];


$stmt = $db->prepare('SELECT user_id FROM `like` WHERE `writeup_id` = ?');
$stmt->bind_param('s', $_GET['id']);
$stmt->execute();
$result = $stmt->get_result();
$likes = mysqli_fetch_all($result, MYSQLI_ASSOC);

include('../views/header.php');
include('../views/show.php');
