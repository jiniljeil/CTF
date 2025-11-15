<?php


return [
    'NAME' => 'NOVITION Backoffice',

    'ROLE' => [
        'USER' => 1,
        'ADMIN' => 2,
    ],

    "JWT" => [
        "EXP" => 30,
    ],

    "VALIDATION" => [
        "LOGIN" => [
            'email' => 'required|string|email|min:15|max:255|regex:/^[a-zA-Z0-9]+@novition\.org$/',
            'password' => 'required|string|min:8',
            'role' => 'required|regex:/^[0-9]{1}$/'
        ],
        "CREDENTIAL" => [
            'name' => 'required|string|max:100|regex:/^[a-zA-Z ]{2,}$/',
            'email' => 'required|string|email|min:15|max:255|regex:/^[a-zA-Z0-9]+@novition\.org$/|unique:users',
            'password' => 'required|string|min:8',
            'birthday' => 'required|string|min:8|max:8|regex:/^\d{8}$/',
            'rrn' => 'required|string|max:14|regex:/^\d{6}-\d{7}$/|unique:users',
            'phone_number' => 'required|string|max:13|regex:/^\d{3}-\d{4}-\d{4}$/|unique:users',
        ],
        "USER_UPDATE" => [
            'name' => 'string|max:100|regex:/^[a-zA-Z ]{2,}$/',
            'birthday' => 'string|min:8|max:8|regex:/^\d{8}$/',
            'phone_number' => 'string|max:13|regex:/^\d{3}-\d{4}-\d{4}$/|unique:users',
            'salary' => 'string|min:1|max:8|regex:/^\d{1,8}$/',
        ],
        "MAIL" => [
            'receiver_email' => 'required|string|email|min:15|max:255|regex:/^[a-zA-Z0-9]+@novition\.org$/',
            'subject' => 'required|string|min:5|max:254',
            'body' => 'required|string',
        ],
        "QNA" => [
            'title' => 'required|string|min:5|max:254',
            'password' => 'required|string|min:8',
            'content' => 'required|string',
        ],
        "TEMPLATE_0" => [
            'name' => 'required|string|max:25',
            'team' => 'required|string|max:30',
            'salary' => 'required|string|regex:/^[0-9]+$/',
        ],
        "TEMPLATE_1" => [
            'name' => 'required|string|max:25',
            'request_details' => 'required|string|max:100',
            'sender_name' => 'required|string|max:25',
            'sender_position' => 'required|string|max:25',
        ],
        "TEMPLATE_2" => [
            'name' => 'required|string|max:25',
            'response_details' => 'required|string|max:100',
            'sender_name' => 'required|string|max:25',
            'sender_position' => 'required|string|max:25',
        ],
    ],

    "FILE" => [
        "MIME" => ['image/jpeg', 'image/png', 'application/zip', 'application/pdf', 'text/plain'],
        "EXTS" => ['jpeg', 'jpg', 'png', 'zip', 'pdf', 'txt'],
        "SIZE" => 5 * 1024 * 1024,
        "PREFIX" => "qna_file_",
    ],
];