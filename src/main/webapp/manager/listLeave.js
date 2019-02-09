//function listleave(){
//	var LeaveModel = Backbone.Model.extend({
//        defaults: {
//            noOfLeavesApplied: null,
//            noOfLeavesRemaining: null,
//            startDate: "",
//            status: "",
//        },
//        url:'http://localhost:8080/messenger/webapi/manager/getLeaves'
//    });
//	var LeaveCollection = Backbone.Collection.extend({ 
//		model:LeaveModel, 
//		url:'http://localhost:8080/messenger/webapi/manager/getLeaves'
//	});
//	
//	var leave = new LeaveCollection()
//	
//	leave.fetch({
//		success: function (collection, response, options) {
//	        //Will log JSON objects of all User objects
//	        console.log(collection.toJSON());
//	        //You can then fetch Model attributes this way
////	        for(i=0;i<collection.length;i++){
////	        console.log("ID: ", collection.get('leaveid'));
////	        console.log("Applied Leaves: ", collection.get('noofLeavesapplied'));
////	        console.log("Remaining Leaves: ", collection.get('noofleavesremaining'));
////	        console.log("Start Date: ",collection.get('startdate'));
////	        console.log("Status: ", collection.get('status'));
////	        }
//		}
//	});
//}