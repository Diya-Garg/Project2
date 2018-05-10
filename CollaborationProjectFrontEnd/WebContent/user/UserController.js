myApp.controller("UserController",function($scope,$http,$location,$rootScope,$cookieStore){
	
	$scope.user={loginName:'',firstName:'',lastName:'',password:'',email:'',role:'',onlineStatus:'',mobileNumber:''};
		
	
		$scope.fetchUserDetails=function(){
			console.log("Inside fetch user Details function "+$rootScope.currentUser.loginName);
			$http.get("http://localhost:4522/CollaborationProjectMiddleWare/getUser/"+$rootScope.currentUser.loginName)
			.then(function(response){
				console.log("fetched User");
				
				$scope.user=response.data;
				$rootScope.currentUser=response.data;
				$cookieStore.put('userDetails',response.data);
			});
			
		};
		
		$scope.updateProfilePicture=function(){
			console.log("Update Profile Picture");
			
		};
	
		$scope.updateProfile=function(){
			console.log("Update Profile function");
			$http.post("http://localhost:4522/CollaborationProjectMiddleWare/updateUser",$scope.user)
			.then(listOfUsers(),function(response){
				console.log("User Profile Updated..");
				$scope.user=response.data;
				alert("Profile Updated!!!");
				$location.path("viewProfile");
			});
		};
		
		
		$scope.Register=function()
		{
			console.log("Enter into Register Method");
			$http.post('http://localhost:4522/CollaborationProjectMiddleWare/register',$scope.user)
			.then(listOfUsers(),function(response)
	     	{
				console.log('Status Text:'+response.statusText);
				$scope.user={loginName:'',firstName:'',lastName:'',password:'',email:'',role:'',onlineStatus:'',mobileNumber:''};
				$window.location.reload();

				alert("User Registered sucessfully");

				$location.path("register");
				
		 });
		};
		
		function listOfUsers(){
			console.log('In listOfUsers function');
			$http.get("http://localhost:4522/CollaborationProjectMiddleWare/getListOfUsers")
			.then(function(response)
					{
							console.log('List of users'+response.data);
					},
					function(error){
						console.log("No users found...");
					});
		};
		
		$rootScope.login=function(){
			console.log("Logging function");
			
			$http.post("http://localhost:4522/CollaborationProjectMiddleWare/login",$scope.user)
			.then(function(response){
				console.log(response.status);
				$scope.user=response.data;
				$rootScope.currentUser=response.data;
				$cookieStore.put('userDetails',response.data);
				console.log($rootScope.currentUser.role);
				
				if($rootScope.currentUser.role=='Role_Admin'){
					console.log("Admin");
				}
				if($rootScope.currentUser.role=='Role_User'){
					console.log("User");
				}
				$location.path("UserHome");
			});
		};
		
	/*	$scope.Register=function()
		{
			console.log("Enter into Register Method");
			$http.post('http://localhost:4522/CollaborationProjectMiddleWare/register',$scope.user)
			.then(listOfUsers(),function(response)
	     	{
				console.log('Status Text:'+response.statusText);
				alert("User Registered sucessfully");
			
		 });
		};*/
		
		
		
		
		
		$rootScope.displayDetails=function(){
			
			console.log("Display Details function called : ");
		};

	$rootScope.logout=function(){
		console.log('LogOut function');
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path("logout");
	}	
});