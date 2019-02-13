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
					.template('<h2 id="header">UPDATE YOUR DETAILS</h2><form><input name="name" placeholder="Name" value="<%=name %>" required><br><input name="password" placeholder="Password" value="<%= password %>" required><br><input name="contact" placeholder="Contact" value="<%= contact %>" required><br><input name="email" placeholder="Email" value="<%= email %>" required><br><button>Save</button></form>'),
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