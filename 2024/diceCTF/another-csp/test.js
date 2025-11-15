import { randomInt } from 'crypto';
console.log(randomInt(2 ** 24).toString(16).padStart(6, '0'));