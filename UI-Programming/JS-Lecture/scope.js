function abc()
{
	x=10;
	var y = 20;
	let z = 30;
	console.log("Within block - x : "+x);
	console.log("Within block - y : "+y);
	console.log("Within block - z : "+z);
}
console.log("outside block, within function -x : " +x);
console.log("outside block, within function -y : " +y);
//console.log("outside block, within function -z : " +z);


abc();
console.log("Outside function : "+x);
//console.log("Outside function : "+y);
//console.log("Outside function : "+z);

// here its `const` by default so itll print for all 3 in case of x.

// `var` is function scope so itll print in 2 cases

// `let` is strictly block scope so only inside block