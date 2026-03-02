// function abc(){
//     console.log("Hello World");
// }

// setTimeout(abc,2000);

// setTimeout(function(){
//     console.log("Hello World");
// },2000);


// function abc() {
//     console.log("HI");
// }

// function xyz() {
//     console.log("HELLO");
// }

// function stop() {
//     clearInterval(ref2);
// }

// var x = 10
// console.log(typeof(x))

// setTimeout(stop, 10000);

// var ref1 = setInterval(abc, 1000);
// var ref2 = setInterval(xyz, 1100);

var num = "12345abc" 
console.log(num+5);
//output = 12345abc5

var num1 = "12345" 
console.log(num1+5);
//output = 123455

var n1 = new Number(123432);
console.log(n1);
var b1 = new Boolean('false');
console.log(b1); // true

//scenarios where boolean is false
console.log(new Boolean());
console.log(new Boolean(false));
console.log(new Boolean(0));
console.log(new Boolean(""));
console.log(new Boolean(null));
console.log(new Boolean(undefined));
 
console.log(new Boolean(-1)) //true