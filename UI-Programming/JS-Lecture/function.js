function add(a,b)
{
	console.log("Addition: "+(a+b));
}
var sum = add(10,20);

var mul = function(a,b){
	console.log("Multiply : " +(a*b));	
}

var div =(a,b) => {
    console.log("Division : "+(a/b));
}

//the only dynamic function as logic is passes in argument and is converted to function
var sub = new Function("a","b", "console.log('Subtract : '+(a-b))" );

mul(5,10);
sub(20,10);

console.log(typeof add);// -> function
console.log(typeof mul);// -> function
console.log(typeof div);// -> function
console.log(typeof sub);// -> function