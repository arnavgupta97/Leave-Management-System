var EmployeeModel = Backbone.Model.extend({
	defaults : {
		id : null,
		name : "",
		password : "",
		userrole : "",
		contact : null,
		email : ""
	},
	url : '/messenger/webapi/employee/update'
});

var EmployeeView = Backbone.View
		.extend({
			template : _
					.template('<nav class="navbar navbar-inverse"><div class="container-fluid"><div class="navbar-header"><a class="navbar-brand">Employee</a></div><ul class="nav navbar-nav"><li><a href="EmployeePortal.html">Home</a></li><li class="active"><a href="">Update Details</a></li><li><a href="leaveapply.html">Apply Leave</a></li><li><a href="leaveDetails.html">Leave Details</a></li><li><a href="projectDetails.html">Project Details</a></li></ul><button type ="button" class = "btn btn-default" onclick="logout()">Log Out</button></div></nav><div class="col-md-4"></div><div class="col-md-4"><h2 id="header">UPDATE YOUR DETAILS</h2><form><input type="text" name="name" class="form-control" placeholder="Name" value="<%=name %>" required><br><input type="password" name="password" class="form-control" placeholder="Password" value="<%= password %>" required><br><input type="number" name="contact" class="form-control" placeholder="Contact" value="<%= contact %>" required><br><input type="email" name="email" class="form-control" placeholder="Email" value="<%= email %>" required><br><button class="btn btn-success">Save</button></form></div>'),
			events : {
				submit : 'save'
			},
			save : function(e) {
				e.preventDefault();
				var name = this.$('input[name="name"]').val();
				var password = this.$('input[name="password"]').val();
				var contact = Number(this.$('input[name="contact"]')
						.val());
				var email = this.$('input[name="email"]').val();
				this.model.save({
					id : Number(sessionStorage.getItem("Id")),
					name : name,
					password : password,
					contact : contact,
					email : email
				});
				this.model.toJSON();
			},
			render : function() {
				this.$el.html(this.template(this.model.attributes));
				return this;
			}
		});
var employeeModel = new EmployeeModel();
var employeeView = new EmployeeView({
	model : employeeModel
});

$(document.body).html(employeeView.render().el);