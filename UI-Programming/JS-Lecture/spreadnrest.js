function add(a,b,c){
	console.log("result : " + (a+b+c));
}

function add(a,b){
    console.log("result : " + (a+b));
}

function add(...a)
{
	var res = 0;
	for(let i=0;i<a.length;i++)
		{
			res += a[i];
		}
		console.log("Result : " +res);
}

add(10,20,30,40,50);
add(10,20);
add(10,20);
add(10,10,30);


console.log("-------------")

var emp = ["raju",25,3000,"tester"];

function display(...emp)
{
    console.log(emp);
}