const ws = require('ws');

const socket = new ws('wss://human-benchmark-5f86954c880a.instancer.sekai.team/game/typing?userId=76883f96-b3b5-4222-9e35-bddf37480b83');

socket.on('open', () => {
    console.log('Connected to the server');
    socket.send(JSON.stringify({
        type: 'start',
    }));
});

var arr = [];
var d = Date.now();
socket.on('message', (message) => {
    const data = JSON.parse(message);

    if (data.type === 'session') {
        arr = data.words;
        let n = 0;
        for (const i of arr) {
            setTimeout(() => {
                socket.send(JSON.stringify({
                    type: 'word',
                    word: i,
                    wordIndex: n++,
                    timestamp: d,
                }));
                console.log(`sended word ${i}${Date.now()}`);
            }, 10);
            d += 100;
        }
        setTimeout(() => {
            socket.send(JSON.stringify({
                type: 'end'
            }));
            console.log(`sended word ${Date.now()}`);
        }, 10);
    } else {
        console.log(data);
    }
});

socket.on('close', () => {
    console.log('Disconnected from the server');
});

socket.on('error', (error) => {
    console.error(error);
});