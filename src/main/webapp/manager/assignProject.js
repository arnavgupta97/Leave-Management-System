//function assignProject(){
//	
//    var ProjectModel = Backbone.Model.extend({
//        defaults: {
//            projectid: null,
//            projectmanagerid:null,
//            projectname: "",
//        },
//        url:'http://localhost:8080/messenger/webapi/manager/assignProject/' + document.getElementById("projectname").value + '/' + document.getElementById("managerid").value
//        +'/'+ document.getElementById("empid").value
//    });
//    var project = new ProjectModel();
//    project.fetch().then(function(){
//    project.create();
//    });
//    alert("Project has been assigned successfully");
//}