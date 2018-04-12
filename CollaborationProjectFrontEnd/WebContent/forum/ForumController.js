myApp.controller("ForumController",function($scope,$http,$location,$rootScope,$cookieStore,$window)
{
	
	$scope.forum={forumName:'',forumContent:'',createDate:'',loginName:'',status:''}
	$scope.allForums
	$scope.message;
	
	$scope.addForum=function()
	{
		console.log("Enter into add Forum Method");
		
		$http.post("http://localhost:4522/CollaborationProjectMiddleWare/addForum",$scope.forum)
		.then(fetchAllForums(),function(response)
     	{
			console.log("Forum Added Succesfully..");
			$scope.forum={forumName:'',forumContent:'',createDate:'',loginName:'',status:''}
			$scope.message='Forum Added Succesfully';
	     });			
	};
	
	function fetchAllForums()
	{
		console.log('Fetching All forums');
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/listForums")
		.then(function(response)
				{
						console.log('Forum List ');
						
						$scope.allForums=response.data;
						console.log('fetched all forums');
						
				},
				function(error){
					console.log("No Forums found...");
					$scope.allForums=[];
				});
	};
	
	$scope.fetchAllForumsAgain=function(){
		
		console.log('Fetching All Forums Again');
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/listForums")
		.then(function(response)
				{
						console.log('Forum List 2');
						$scope.allForums=response.data;
				},
				function(error){
					console.log("No Forums found...");
					$scope.allForums=[];
					$scope.viewmessage="No Forums Found!!!"
				});
	};
		
	 $scope.UpdateForum=function(fid){
		console.log("In Forum Update Function : "+fid);
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/getForum/"+fid)
		.then(function(response) {
				console.log("Forum fetched " +response.data);
				$scope.forum=response.data;
				$rootScope.currentForum=response.data;
				$cookieStore.put('forumDetails',response.data);
				$location.path("updateForumForm");
		});
};
	

	$scope.editForum=function(){
		
		console.log("Editing Forum : "+$rootScope.currentForum);
		
		$http.post("http://localhost:4522/CollaborationProjectMiddleWare/updateForum",$rootScope.currentForum)
		.then(fetchAllForums(),function(response){
				console.log("In then of Edit Forum");
				alert("Forum Updated Succesfully...");
				$location.path("viewAllForums");
		});
		
	}

	$scope.DeleteForum=function(forumid){
		
		console.log("Deleting Forum..."+forumid);
		$http.get("http://localhost:4522/CollaborationProjectMiddleWare/deleteForum/"+forumid)
		.then(fetchAllForums(),function(response)
				{
						alert("Forum Deleted Succesfully...");
						$window.location.reload();
						$location.path("viewAllForums");
						
				});
	};

});