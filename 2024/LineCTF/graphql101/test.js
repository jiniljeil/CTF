const crypto = require('crypto');

const NUM_CHALLENGE = 40 ;
const STRENGTH_CHALLENGE = 999 ;

function intToString(v) {
    let s = v.toString();
    while (s.length !== STRENGTH_CHALLENGE.toString().length) s = '0' + s;
    return s;
}

for (let i = 0; i < NUM_CHALLENGE; ++i) 
  console.log(intToString(crypto.randomInt(0, STRENGTH_CHALLENGE))) 