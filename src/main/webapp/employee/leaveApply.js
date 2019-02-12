var LeaveModel = Backbone.Model.extend({
	defaults : {
		id : null,
		noOfLeavesApplied : null,
		startDate : ""
	},
	url : '/messenger/webapi/employee/applyLeave'
});

var LeaveView = Backbone.View
		.extend({
			template : _
					.template('<h2 id="header">APPLY FOR LEAVES</h2><form><input name="noOfLeavesApplied" placeholder="Number of Days" value="<%= noOfLeavesApplied %>" required><br><input name="startDate" placeholder="Start Date" value="<%= startDate %>" required><br><button>Save</button></form>'),
			events : {
				submit : 'save'
			},
			save : function(e) {
				e.preventDefault();
				var id = Number(sessionStorage.getItem("Id"));
				var noOfLeavesApplied = +this.$(
						'input[name="noOfLeavesApplied"]').val();
				var startDate = this.$('input[name="startDate"]').val();
				this.model.save({
					"id" : id,
					"leave" : {
						"noOfLeavesApplied" : noOfLeavesApplied,
						"startDate" : startDate
					}
				});
				alert("Leaves Have Been Applied Successfully");
				this.model.toJSON();
			},
			render : function() {
				this.$el.html(this.template(this.model.attributes));
				return this;
			}
		});
var leaveModel = new LeaveModel();
var leaveView = new LeaveView({
	model : leaveModel
});

$(document.body).html(leaveView.render().el);