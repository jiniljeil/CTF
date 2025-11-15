<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>WriteupBin 🚩🗑️</title>
    <meta name="description" content="WriteupBin - Leak flags with style!">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js" integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.8.2/parsley.min.js" integrity="sha256-75TL99xMMUTAZIeDi7hBQwrYdig+bWn7kfFuDSSuJsc=" crossorigin="anonymous"></script>
</head>
<body>
    <a href="/"><h1>WriteupBin 🚩🗑️</h1></a>

    <h2>Your Writeups</h2>
    <ul>
        <?php foreach($writeups as $w): ?>
            <li><a href="/show.php?id=<?= $w['id'] ?>">Writeup - <?= $w['id'] ?></a></li>
        <?php endforeach; ?>
    </ul>

    <hr>