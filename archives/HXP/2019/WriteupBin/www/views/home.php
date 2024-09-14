<p>Welcome <?= $_SESSION['id'] ?></p>

<h2>Publish Writeup</h2>
<form id="publish-form" method="post" action="/add.php">
    <input type="hidden" name="c" value="<?= $_SESSION['c'] ?>">
    <textarea data-parsley-required data-parsley-minlength="140" data-parsley-pattern="[^<>]+" rows="25" cols="80" name="content">No captcha required for preview. Please, do not write just a link to original writeup here.</textarea>
    <br>
    <input type="submit">
</form>

<script nonce="<?=$nonce?>">
    $('#publish-form').parsley() // prevent hacking
</script>