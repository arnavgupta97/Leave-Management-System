function approve(){
	
	   var LeaveModel = Backbone.Model.extend({
	        url:'/messenger/webapi/manager/approve'
	    });
	   var leave =  new LeaveModel();
	   leave.save();
}

function logout(){
	 window.location.href ="/messenger/index.html";
	 sessionStorage.clear();
} 
