<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <script src="https://cure53.de/purify.js"></script>
  </head>
  <body>
<script>
    window.onload = () => {
        const params = new URLSearchParams(location.search);
        injection = params.get("xss");
        if (injection)
        {
            injection = DOMPurify.sanitize(injection);
            document.body.innerHTML = injection.replace(/<style>.*<\/style>/gs, "");
        }
    };
</script>

</html>