var ProjectModel = Backbone.Model.extend({
	defaults : {
		projectId : null,
		projectManagerId : null,
		projectName : ""
	}
});
var ProjectCollection = Backbone.Collection.extend({
	model : ProjectModel,
	url : '/messenger/webapi/manager/getProjects'
});

var ViewAll = Backbone.View.extend({
	el : '#project-view',
	initialize : function() {
		this.listenTo(this.model, 'sync change', this.render);
		this.collection.fetch();
		this.render();
	},
	render : function() {
		this.collection.each(function(project) {
			var projectView = new ProjectView({
				model : project
			});
			this.$el.append(projectView.render().el);
		}, this);
		return this; // returning this for chaining..
	}
});
var ProjectView = Backbone.View.extend({
	// tagName : 'li',
	template : _.template($('#project-temp').html()),

	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this; // returning this from render method..
	}
});
var projectCollection = new ProjectCollection();
var viewAll = new ViewAll({
	collection : projectCollection
});
$(document.body).append(viewAll.render().el);