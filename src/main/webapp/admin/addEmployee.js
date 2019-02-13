var EmployeeModel = Backbone.Model.extend({
	defaults : {
		id : null,
		name : "",
		password : "",
		userrole : "",
		contact : null,
		email : ""
	},
	url : '/messenger/webapi/admin/addEmployee'
});

var EmployeeView = Backbone.View
		.extend({
			template : _
					.template('<h2 id="header">ADD EMPLOYEE</h2><form><input name="id" placeholder="Enter Id" value="<%=id %>" required><br><input name="name" placeholder="Enter Name" value="<%=name %>" required><br><input name="password" placeholder="Enter Password" value="<%= password %>" required><br><input name="userrole" placeholder="Enter Userrole" value="<%= userrole %>" required><br><input name="contact" placeholder="Enter Contact" value="<%= contact %>" required><br><input name="email" placeholder="Email" value="<%= email %>" required><br><button>Save</button></form>'),
			events : {
				submit : 'save'
			},
			save : function(e) {
				e.preventDefault();
				var id = Number(this.$('input[name="id"]').val());
				var name = this.$('input[name="name"]').val();
				var password = this.$('input[name="password"]').val();
				var userrole = this.$('input[name="userrole"]').val();
				var contact = Number(this.$('input[name="contact"]').val());
				var email = this.$('input[name="email"]').val();
				this.model.set("id", id);
				this.model.set("name", name);
				this.model.set("password", password);
				this.model.set("userrole", userrole);
				this.model.set("contact", contact);
				this.model.set("email", email);
				this.model.save(null, {
					type : 'POST'
				});
				alert("Employee Has Been Added Successfully");
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