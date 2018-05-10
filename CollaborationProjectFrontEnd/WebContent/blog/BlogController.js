myApp.controller("BlogController",function($scope,$http,$location,$rootScope,$cookieStore,$window)
{
	
	$scope.blog={blogName:'',blogContent:'',createDate:'',likes:0,loginName:'',status:''}
	$scope.comment={commentText:'',loginname:'',blogId:'',commentDate:''}
	$scope.blogdata;
	$scope.message;
	$scope.viewmessage;
	$scope.allBlogs;
	$scope.allBlogComments;
	$scope.addBlog=function()
	{
		console.log("Enter into insertBlog Method");
		
		$http.post("http://localhost:4522/CollaborationProjectMiddleWare/addBlog",$scope.blog)
		.then(fetchAllBlogs(),function(response)
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
	
$scope.listAllApprovedBlogs=function(){
	console.log('list all approved blogs called');
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/listAllApprovedBlogs")
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

	
	
	$scope.fetchPendingBlogs=function(){
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/listPendingBlogs")
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
		.then(fetchAllBlogs(),function(response){
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
						$window.location.reload();
						$location.path("viewAllBlogs");
				});
	};
	
	
	$scope.incrementLikes=function(bid){
		console.log("Incrementing Likes : "+bid);
		$http({
		    url : 'http://localhost:4522/CollaborationProjectMiddleWare/incrementBlogLikes/'+bid,
		    method : 'GET',
		   transformResponse: [
		        function (data) { 
		            console.log("Likes Incremented");
		            alert("Likes Incremented");
		        	$window.location.reload();
		        	$location.path('listAllApprovedBlogs');
		        }
		    ]
		});
	};
	
	$scope.approveBlog=function(bid){
		console.log('Approve Blog '+bid);
		$http({
		    url : 'http://localhost:4522/CollaborationProjectMiddleWare/approveBlog/'+bid,
		    method : 'GET',
		   transformResponse: [
		        function (data) { 
		            alert("Blog Approved Succesfully..");
		        	$window.location.reload();
		        }
		    ]
		});
	};
	
	$scope.rejectBlog=function(bid){
		console.log('Reject Blog '+bid);
		$http({
		    url : 'http://localhost:4522/CollaborationProjectMiddleWare/rejectBlog/'+bid,
		    method : 'GET',
		   transformResponse: [
		        function (data) { 
		            alert("Blog Rejected Succesfully..");
		        	$window.location.reload();
		        }
		    ]
		});
	};
	
	$scope.ViewBlog=function(bid){
		console.log("In ViewBlog  : "+bid);
		
		
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/getBlog/"+bid)
		.then(function(response)
     	{
			console.log('fetching blog');
			$rootScope.currentBlog=response.data;
			$cookieStore.put('blogDetails',response.data);
			$location.path("ViewBlog");
			
	     },
	     function(error){
	    	 console.log('Error Found');
	     }
	     );
		
};
	
	$scope.addCommentForm=function(bid){
		console.log("Display Comment Form for Blog "+bid);
		$rootScope.blogId=bid;
		$location.path('AddCommentForm');
	};
	
	
	$scope.addComment=function(bid){
		console.log('Adding comment for blog '+bid);
		console.log('Comment Text =  '+$scope.comment.commentText);
		$scope.comment.blogId=bid;
		console.log('Blog Id = '+$scope.comment.blogId);
	
		
		$http.post("http://localhost:4522/CollaborationProjectMiddleWare/addBlogComment",$scope.comment)
		.then(fetchAllBlogs(),function(response)
     	{
			alert("Comment Added Succesfully...");
			
	     },
	     function(error){
	    	 alert("Username is not correct.. Please enter correct username or register yourself");
	     });
		
	};
	
	$scope.viewAllBlogComments=function(bid){
		console.log('ViewAllBlogsComments : '+bid);
			$http.get("http://localhost:4522/CollaborationProjectMiddleWare/listAllBlogComments/"+bid)
			.then(function(response)
					{
							console.log('Fetch All Comments');
							$scope.allBlogComments=response.data;
							$rootScope.currentBlogComments=response.data;
							$location.path("ViewAllComments");
					},
					function(error){
						console.log("No Blog comments found...");
						$scope.allBlogComments=[];
						$scope.viewmessage="No Blog Comments Found!!!"
					});
		};

});