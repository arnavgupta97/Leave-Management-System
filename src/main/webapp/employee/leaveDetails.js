function leaveDetails() {

	var LeaveModel = Backbone.Model.extend({
		defaults : {
			id : null,
		},
		url : 'http://localhost:8080/messenger/webapi/employee'
	});

	var LeaveCollection = Backbone.Collection.extend({
		model : LeaveModel,
		url : 'http://localhost:8080/messenger/webapi/employee'
	});

	var leave = new LeaveCollection();

	leave.fetch().then(
			function() {
				var res = leave.findWhere({
					'id' : Number(sessionStorage.getItem("Id"))
				});
				// alert(JSON.stringify(res.get("leave")));
				var leaves = res.get("leave");
				alert("Leave ID: " + leaves.leaveId + "\n" + "Days Applied: "
						+ leaves.noOfLeavesApplied + "\n"
						+ "Reamining Leaves: " + leaves.noOfLeavesRemaining
						+ "\n" + "Start Date: " + leaves.startDate + "\n"
						+ "Leave Status: " + leaves.status);
			});
}