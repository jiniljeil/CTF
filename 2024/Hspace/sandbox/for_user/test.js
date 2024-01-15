// const vm2 = require('vm2')
const fs = require('fs') 

console.log(fs.readFileSync(new URL("file:///etc/passwd")))
// let sbx = {
//     readFile: (path) => {
//         if(!(new String(path).toString()).includes('flag'))
//             return fs.readFileSync(path,{encoding: "utf-8"})
//         return null
//     },
//     sum: (args) => args.reduce((a,b)=>a+b),
// }

// let vm = new vm2.VM({
//     timeout: 20,
//     sandbox: sbx,
//     fixAsync: true,
//     eval: false
// })

// let result = ":(";
// result = new String(vm.run(`sum([2]);`)); 
// console.log(result); 
