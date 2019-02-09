function updateDetails(){
	
    var EmployeeModel = Backbone.Model.extend({
        defaults: {
        	id:null,
            name: "",
            password: "",
            userrole: "",
            contact: null,
            email: ""
        },
        url:'http://localhost:8080/messenger/webapi/employee/update'
    });
   var employee = new EmployeeModel();
   
   employee.fetch().then(function(){
	   if(employee.get('id')==localStorage.getItem("Id")){
	       employee.set('name',document.getElementById("name").value);
	       employee.set('password',document.getElementById("password").value);
	       employee.set('contact',document.getElementById("contact"));
	       employee.set('email',document.getElementById("email"));
		   employee.save(); 
		   alert("Updated Successfully");
	   }else{
		   alert("Unable to Update");
	   }
   });
}
function logout(){
	 window.location.href ="http://localhost:8080/messenger/index.html";
	 localStorage.clear();
}