function delete_employee() {
	var EmployeeModel = Backbone.Model.extend({
		defaults : {
			id : null,
			name : "",
			password : "",
			userrole : "",
			contact : null,
			email : ""
		},
		url : '/messenger/webapi/admin'
	});

	var EmployeeCollection = Backbone.Collection.extend({
		model : EmployeeModel,
		url : '/messenger/webapi/admin'
	});

	var employeeCollection = new EmployeeCollection();

	employeeCollection.fetch().then(function() {

		var id = Number(document.getElementById("myid").value);
		var res = employeeCollection.findWhere({
			'id' : id
		});
		console.log(res);
		if (res.get('id') == id) {
			res.destroy({
				data : JSON.stringify({
					'id' : id
				}),
				contentType : "application/json"
			});
			alert("Employee Deleted Successfully");
		} else {
			alert("Employee Not Found");
		}
	});

}