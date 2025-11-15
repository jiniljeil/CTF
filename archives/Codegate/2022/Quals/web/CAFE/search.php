<?php 
  if(isset($_GET['text'])){
    $text = $_GET['text'];
  }    
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<section>
    <div class="py-3">
        <div class="container">       
          <p>Search result: <?= $text ?> </p>
          <table class="table table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Content</th>
                <th scope="col">Writer</th>
                <th scope="col">Views</th>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
    </div>
</section>
</body>
</html>