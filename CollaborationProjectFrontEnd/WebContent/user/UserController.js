myApp.controller("UserController",function($scope,$http,$location,$rootScope,$cookieStore){
	
	$scope.user={"loginName":"","firstName":"","lastName":"","password":"","email":"","role":"","status":""};
	$scope.user={loginName:'',firstName:'',lastName:'',password:'',email:'',role:'',onlineStatus:'',mobileNumber:''};
		
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

	$rootScope.logout=function(){
		console.log('LogOut function');
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path("logout");
	}	
});