async function getName(int id){
    var empName = await getEmployee(id);
    console.log("Employee name is "+empName);
}
//getName depends on getEmployee..it wont return till getemployee completes
//so getName must await getEmployee
async function getEmployee(int id){
    //this function connects to db to fetch emp details
    if(id=101)
    {
        return "Joshua";
    }
}

abc()
getName(101)
xyz()