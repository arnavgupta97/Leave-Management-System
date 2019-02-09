function cancelLeaves() {
	var LeaveModel = Backbone.Model.extend({
		defaults : {
			id : null
		},
		url : 'http://localhost:8080/messenger/webapi/employee'
	});

	var LeaveCollection = Backbone.Collection.extend({
		model : LeaveModel,
		url : 'http://localhost:8080/messenger/webapi/employee'
	});

	var leave = new LeaveCollection();
	var leaveModel = new LeaveModel();

	leave.fetch().then(function() {

		var res = leave.findWhere({
			'id' : Number(sessionStorage.getItem("Id"))
		});
		var leaves = res.get("leave");
      //  alert(leaves.status);
		if (leaves.status == "Pending") {

			leaveModel.set({id : Number(sessionStorage.getItem("Id"))});
			leaveModel.save();
			alert("Leaves Successfully Cancelled");

		} else {
			alert("Unable to Cancel");
		}

	});
}