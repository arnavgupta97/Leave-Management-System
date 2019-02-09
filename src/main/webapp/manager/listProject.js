//function listproject(){
//	var ProjectModel = Backbone.Model.extend({
//        defaults: {
//        	projectId: null,
//            projectManager: null,
//            projectName: ''
//        },
//        url:'http://localhost:8080/messenger/webapi/manager/getProjects'
//    });
//	var ProjectCollection = Backbone.Collection.extend({ 
//		model:ProjectModel, 
//		url:'http://localhost:8080/messenger/webapi/manager/getProjects'
//	});
//	
//	var Project = new ProjectCollection()
//	
//	Project.fetch({
//		success: function (collection, response, options) {
//	        //Will log JSON objects of all User objects
//	        console.log(collection.toJSON());
//	        //You can then fetch Model attributes this way
//	        for(i=0;i<collection.length;i++){
//	        console.log("Project ID: ", collection.get('projectid'));
//	        console.log("Manager ID: ", collection.get('projectmanagerid'));
//	        console.log("Project Name: ", collection.get('projectname'));
//	        }
//		}
//	});
//}