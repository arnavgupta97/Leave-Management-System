//function Login(){
//	 var EmployeeModel = Backbone.Model.extend({
//	        defaults: {
//	            id: null,
//	            name: "",
//	            password: "",
//	            userrole: "",
//	            contact: null,
//	            email: ""
//	        },
//	        url:'http://localhost:8080/messenger/webapi/admin'
//	    });
//	   var employee = new EmployeeModel();
//	   employee.toJSON();
//	   employee.fetch().then(function(){
//		   alert("Logged in");
//	          if(employee.get('id')==document.getElementById("id").value  && employee.get('password')==document.getElementById("password").value ){
//	        		  
//	        	if(employee.get('userrole')=="administrator"){
//	        	  
//	        	  window.location.href ="http://localhost:8080/messenger/admin/adminPortal.html"
//	        	  localStorage.setItem("Id",document.getElementById("id").value);
//	        	  
//	          }else if(employee.get('userrole')=="manager"){
//	        	  
//	        	  window.location.href ="http://localhost:8080/messenger/manager/ManagerPortal.html"
//	        	  localStorage.setItem("Id",document.getElementById("id").value);
//	        	  
//	          }else if (employee.get('userrole')=="employee"){
//	        	  
//	        	  window.location.href ="http://localhost:8080/messenger/employee/EmployeePortal.html"
//	        	  localStorage.setItem("Id",document.getElementById("id").value);
//	          }
//	          }
//		   else{
//			   alert("Invalid Details");
//		   }
//	   });
//} 