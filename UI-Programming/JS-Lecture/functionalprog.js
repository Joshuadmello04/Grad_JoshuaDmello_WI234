function abc(){
    console.log("From abc() function")
}

function xyz(){
    console.log("From xyz() function")
}

function demo1(op){
    op();
    console.log("___________________");
}

function demo2(){
    console.log("------------")
    console.log("From demo2() function")
    return xyz;
}
demo1(abc)
demo1(xyz)

var ref1 = demo2();
ref1();

demo2()() // this means demo2() will return the reference of xyz function and then we are calling that function using ()

demo2()()() // this will give error because demo2() will return the reference of xyz function and then we are calling that function using () and then we are trying to call the result of that function which is undefined because xyz function does not return anything.
//this is called function currying or function chaining.
