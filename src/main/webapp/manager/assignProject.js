var ProjectModel = Backbone.Model
				.extend({
					defaults : {
						projectmanagerid : null,
						projectname : "",
						id : ""
					},
					url : '/messenger/webapi/manager/assignProject'
				});

		var ProjectView = Backbone.View
				.extend({
					template : _
							.template('<h2 id="header" >ASSIGN PROJECT</h2><form><input name="projectmanagerid" placeholder="Manager ID" value="<%= projectmanagerid %>"><input name="projectname" placeholder="Project Name" value="<%= projectname %>"><input name="id" placeholder="Employee ID" value="<%= id %>"><button>Save</button></form>'),
					events : {
						submit : 'save'
					},
					save : function(e) {
						e.preventDefault();
						var projectmanagerid = Number(this.$(
								'input[name="projectmanagerid"]').val());
						var projectname = String(this.$(
								'input[name="projectname"]').val());
						var id = Number(this.$('input[name="id"]').val());
						this.model.save({
							"id": id,
							"project": [{
								"projectName": projectname,
								"projectManagerId": projectmanagerid
							}]
						}, { type: 'POST' });
						this.model.toJSON();
					},
					render : function() {
						this.$el.html(this.template(this.model.attributes));
						return this;
					}
				});
		var projectModel = new ProjectModel();
		var projectView = new ProjectView({
			model : projectModel
		});

		$(document.body).html(projectView.render().el);