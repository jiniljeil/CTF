<stylesheet version="1.0" xmlns="http://www.w3.org/1999/XSL/Transform">
    <template match="/">
        <script xmlns="http://www.w3.org/1999/xhtml">
            prompt(location.href);
            top.location = `http://example.com/`;
        </script>
    </template>
</stylesheet>