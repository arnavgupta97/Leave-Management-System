function approve(){
	   var LeaveModel = Backbone.Model.extend({
	        defaults: {
	            noOfLeavesApplied: null,
	            noOfLeavesRemaining: null,
	            startDate: "",
	            status: "",
	        },
	        url:'http://localhost:8080/messenger/webapi/manager'
	    });
	   var leave = new LeaveModel();
	   
	   leave.fetch().then(function(){
	       leave.save();
	       alert("Required Leaves Has Been Monitored Successfully");
	   });
}

//function reject(){
//	   var LeaveModel = Backbone.Model.extend({
//	        defaults: {
//	            noOfLeavesApplied: null,
//	            noOfLeavesRemaining: null,
//	            startDate: "",
//	            status: "",
//	        },
//	        url:'http://localhost:8080/messenger/webapi/manager/rejectLeave'
//	    });
//	   var leave = new LeaveModel();
//	   leave.fetch().then(function(){
//	           leave.save();	           
//	   });
//	   alert("Required Leaves Has Been Rejected Successfully");
//}
function logout(){
	 window.location.href ="http://localhost:8080/messenger/index.html";
	 sessionStorage.clear();
} 
