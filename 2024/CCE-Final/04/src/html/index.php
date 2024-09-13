<?php session_start(); ?>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보자원관리원 Webpage</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    <style>
        /* Add your custom styles here or in style.css */
        .image-container {
            position: relative;
            display: inline-block;
            width: 100%; /* Adjust width as needed */
        }
        .image-container img {
            width: 100%; /* Make sure the image takes full width of the container */
            display: block;
        }
        .image-text {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white; /* Text color */
            font-size: 2rem; /* Adjust font size */
            font-weight: 700; /* Font weight */
            text-align: center; /* Center text */
            padding: 10px;
            background-color: rgba(0, 0, 0, 0.5); /* Optional: Background color for text */
            border-radius: 8px; /* Optional: Rounded corners for text background */
        }
    </style>
</head>
<body>
    <!-- Header -->
    
    <?php include 'config/header.php'; ?>
    <!-- <img src="https://www.nis.go.kr/resources/img/renewal_images/light/bg_main.jpg" alt=""> -->
    <div class="image-container">
        <img src="https://github.com/user-attachments/assets/2b7d26a9-6c94-4eb6-8d8f-c1924bb891b8" alt="Main Background Image">
        <div class="image-text">정 보 자 원 관 리 원</div>
    </div>

    <?php include 'config/footer.php'; ?>
</body>
</html>
