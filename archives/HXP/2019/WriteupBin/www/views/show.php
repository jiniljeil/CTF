<h2>Writeup - <?= $writeup['id'] ?></h2>

<h3>Liked by</h3>
<?= implode(', ', array_map(function($l) { return $l['user_id']; }, $likes)) ?> 

<form method="post" action="/like.php">
    <input type="hidden" name="c" value="<?= $_SESSION['c'] ?>">
    <input type="hidden" name="id" value="<?= $writeup['id'] ?>">
    <input id="like" type="submit" value="👍">
</form>

<hr>

<form method="post" action="/admin.php">
    <input type="hidden" name="c" value="<?= $_SESSION['c'] ?>">
    <input type="hidden" name="id" value="<?= $writeup['id'] ?>">
    <input type="submit" value="Show to Admin">
</form>

<hr>

<code><?= $writeup['content'] ?></code>