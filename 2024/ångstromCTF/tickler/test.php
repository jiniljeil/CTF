<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <p>
    </p>
    <script>
        const error = document.querySelector("p");
        const query = new URLSearchParams(window.location.search);
        if (query.has("error")) {
            error.innerHTML = query.get("error") ?? "";
        }
    </script>
</body>
</html>