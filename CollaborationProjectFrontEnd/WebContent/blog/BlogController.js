
myApp.controller("BlogController",function($scope,$http,$location,$rootScope,$cookieStore){
	
	$scope.blog={blogId:'',blogName:'',blogContent:'',createDate:'',loginName:'',status:'',likes:''};
		
		$scope.addBlog=function(){
			console.log("Add Blog function");
			
			$http.post("http://localhost:4522/CollaborationProjectMiddleWare/addBlog",$scope.blog)
			.then(function(response){
				console.log('statusText'+response.statusText);
				
			});
		
			}
		
		
});