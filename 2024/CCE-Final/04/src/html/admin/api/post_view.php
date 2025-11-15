<?php
if (!isset($_SESSION['uid'])) {
    die('<script>alert("Login required");</script>');
}

if ($_SESSION['uid'] !== 'admin') {
    die('<script>alert("You are not admin");</script>');
}

include '../config/db.php';

$qeury = "SELECT * FROM posts";

# [DEBUG] SQL 
$result = $dbcon->query($qeury);


if ($result->num_rows > 0) {
    $rows = array();
    while ($row = $result->fetch_assoc()) {
        $rows[] = $row;
    }
    echo json_encode($rows);
} else {
    echo '';
}
?>s