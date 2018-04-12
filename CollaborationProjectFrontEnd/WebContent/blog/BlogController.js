myApp.controller("BlogController",function($scope,$http,$location,$rootScope,$cookieStore)
{
	
	$scope.blog={blogName:'',blogContent:'',createDate:'',likes:0,loginName:'',status:''}
	
	$scope.blogdata;
	$scope.message;
	$scope.viewmessage;
	$scope.allBlogs;
	
	$scope.addBlog=function()
	{
		console.log("Enter into insertBlog Method");
		
		$http.post("http://localhost:4522/CollaborationProjectMiddleWare/addBlog",$scope.blog)
		.then(function(response)
     	{
			$scope.message='Blog Added Succesfully';
			$scope.blog={blogName:'',blogContent:'',createDate:'',likes:0,loginName:'',status:''}
			$location.path("addBlog");
	     });			
	};
	
	$scope.fetchAllBlogsAgain=function(){
		
		console.log('Fetching All Blogs');
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/listBlogs")
		.then(function(response)
				{
						console.log('Second');
						$scope.allBlogs=response.data;
				},
				function(error){
					console.log("No Blogs found...");
					$scope.allBlogs=[];
					$scope.viewmessage="No Blogs Found!!!"
				});
	};
	
	function fetchAllBlogs()
	{
		
		console.log('Fetching All Blogs');
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/listBlogs")
		.then(function(response)
				{
						console.log('Second');
						$scope.allBlogs=response.data;
				},
				function(error){
					console.log("No Blogs found...");
					$scope.allBlogs=[];
					$scope.viewmessage="No Blogs Found!!!"
				});
	};
	
	
	$scope.UpdateBlog=function(bid){
		console.log("In Blog Update Function : "+bid);
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/getBlog/"+bid)
		.then(function(response) {
				console.log("Blog fetched " +response.data);
				$scope.blog=response.data;
				$rootScope.currentBlog=response.data;
				$cookieStore.put('blogDetails',response.data);
				$location.path("updateBlogForm");
		});
};
	

	$scope.editBlog=function(){
		
		console.log("Editing Blog : "+$rootScope.currentBlog);
		
		$http.post("http://localhost:4522/CollaborationProjectMiddleWare/updateBlog",$rootScope.currentBlog)
		.then(function(response){
				console.log("In then of Edit Blog");
				alert("Blog Updated Succesfully...");
				$location.path("viewAllBlogs");
		});
		
	}

	$scope.DeleteBlog=function(bid){
		
		console.log("Deleting Blog..."+bid);
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/deleteBlog/"+bid)
		.then(fetchAllBlogs(),function(response)
				{
						alert("Blog Deleted Succesfully...");
						$location.path("viewAllBlogs");
				});
	};
	
});