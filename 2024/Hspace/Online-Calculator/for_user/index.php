<?php
  $x = random_int(10000, 99999);
  $y = random_int(10000, 99999);
  $op = array("+", "-", "/", "*")[random_int(0, 3)];
  $message = array(
    "Congratulations!",
    "You are good at math",
    "Wow"
  )[random_int(0, 2)];
?>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h3 class="text-center my-5">Online Calculation Judge</h3>
            <p>Answer following question</p>
            <code><?=$x?> <?=$op?> <?=$y?></code>
            <form method="POST" action="check.php">
                <div class="form-group">
                  <label for="user_answer">Your answer</label>
                  <input type="text" class="form-control" name="user_answer" placeholder="Answer">
                </div>
                <input type="hidden" name="x" value="<?=$x?>">
                <input type="hidden" name="y" value="<?=$y?>">
                <input type="hidden" name="op" value="<?=$op?>">
                <input type="hidden" name="message" value="<?=$message?>">
            </form>
        </div>
        <!-- If you solve the problem well, you can find some hidden flag file :p -->
    </body>
</html>