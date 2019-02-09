//function applyLeave(){
//	
//    var leaveModel = Backbone.Model.extend({
//        defaults: {
//          noofappliedleaves:null,
//          startdate:''
//        },
//        url:'http://localhost:8080/messenger/webapi/employee/applyLeave/' + localStorage.getItem("Id") + '/' + document.getElementById("days").value
//        +'/'+ document.getElementById("startdate").value
//    });
//   var leave = new leaveModel();
//   leave.fetch().then(function(){
//	   if( leave.get('status')=='Pending'){
//		  // alert("Leaves Already Applied");
//	   }
//	   else{
//		   leave.save();
//		   alert("Leaves Applied Successfully");
//	   }   
//   });
//}