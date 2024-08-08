<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .form-header {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="form-container">
                    <h2 class="form-header text-center">Request your demo page</h2>
                    <form method="POST" action="save.php">
                        <div class="form-group mb-3">
                            <label class="mb-2" for="color">Theme color</label>
                            <select class="form-select" id="color" name="color">
                                <option value="dark">Default</option>
                                <option value="secondary">Light gray</option>
                                <option value="primary">Blue</option>
                                <option value="danger">Red</option>
                                <option value="warning">Yello</option>
                            </select>
                        </div>

                        <div class="form-group mb-3">
                            <label class="mb-2" for="name">Name</label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        
                        <div class="form-group mb-3">
                            <label class="mb-2" for="desc">Short description</label>
                            <textarea class="form-control" id="desc" name="desc" rows=5></textarea>
                        </div>

                        <div class="text-center mb-1">
                            <button type="submit" class="btn btn-primary btn-lg">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>