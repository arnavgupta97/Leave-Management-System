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
							.template('<form><input name="id" value="<%=id %>"><input name="name" value="<%=name %>"><input name="password" value="<%= password %>"><input name="userrole" value="<%= userrole %>"><input name="contact" value="<%= contact %>"><input name="email" value="<%= email %>"><button>Save</button></form>'),
					events : {
						submit : 'save'
					},
					save : function(e) {
						e.preventDefault();
						var id = Number(this.$('input[name="id"]').val());
						var name = this.$('input[name="name"]').val();
						var password = this.$('input[name="password"]').val();
						var userrole = this.$('input[name="userrole"]').val();
						var contact = Number(this.$('input[name="contact"]')
								.val());
						var email = this.$('input[name="email"]').val();
						this.model.save({
							id : id,
							name : name,
							password : password,
							userrole : userrole,
							contact : contact,
							email : email
						});
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