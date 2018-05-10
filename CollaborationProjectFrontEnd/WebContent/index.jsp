<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>LetsTalk</title>
		
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="js/jquery.min.js"></script>
  		<script src="js/bootstrap.min.js"></script>
  		<script src="js/angular.min.js"></script>
		<script src="js/angular-route.js"></script>
		<script src="js/angular-cookies.js"></script>
		<script src="js/MyAngular.js"></script>
  		<script src="user/UserController.js"></script>
  		<script src="blog/BlogController.js"></script>
  		<script src="forum/ForumController.js"></script>
  		<script src="chat/ChatController.js"></script>
  		<script src="chat/ChatService.js"></script>
  		<script src="friend/FriendController.js"></script>
  		<script src="job/JobController.js"></script>
  		<link rel="stylesheet" href="css/app.css"/>
 		<script src="js/lodash.js"></script>
		<script src="js/stomp.js"></script>
		<script src="js/sockjs.js"></script>
		
	</head>
	<body ng-app="myApp">
	<nav class="navbar navbar-inverse" style="background-color:#34495E">
		<div class="container-fluid">
			<div class="navbar-header" style="height:12%">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span> 
      			</button>
				<a class="navbar-brand" href="#"><img src="images/icon.png" style="height:70px;width:100px"/></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
    			<ul class="nav navbar-nav" style="margin-top:20px;font-size:20px;font-weight:bold;">
					<li><a href="#/!" style="color:white">Home</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!listAllApprovedBlogs" style="color:white">Blog</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!forum" style="color:white">Forum</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!job" style="color:white">Job</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!about" style="color:white">About Us</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!contact" style="color:white">Contact Us</a></li>
					<!-- <li ng-show="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!uploadProfilePicture" style="color:white">Upload Profile Picture</a></li> -->
					
					
					
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'">
						<a href="#!login" style="color:white">
							<span class="glyphicon glyphicon-log-in"></span>Login
						</a>
					</li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'">
						<a href="#!register" style="color:white">
							<span class="glyphicon glyphicon-user"></span>Register
							</a>
					</li>
				
					<li ng-show="currentUser.role=='Role_User'" class="dropdown" style="padding-right:20px;padding-left:10px ">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Blog
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
          					<li><a href="#!addBlog">Add Blog</a></li>
          					<li><a href="#!viewAllBlogs">View All Blogs</a></li>
         				</ul>
      				</li>
      				
      				<li ng-show="currentUser.role=='Role_Admin'" class="dropdown" style="padding-right:20px;padding-left:10px ">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Blog
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
          					<li><a href="#!viewAllBlogs">View All Blogs</a></li>
          					<li><a href="#!viewPendingBlogs">View Pending Blogs</a></li>
         				</ul>
      				</li>
      				
      				
      				<li ng-show="currentUser.role=='Role_Admin'" class="dropdown" style="padding-right:20px;padding-left:10px ">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Forum
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
          					<li><a href="#!viewAllForums">View All Forums</a></li>
          					<li><a href="#!viewPendingForums">View Pending Forums</a></li>
         				</ul>
      				</li>
      				
      				<li ng-show="currentUser.role=='Role_Admin'" class="dropdown" style="padding-right:20px;padding-left:10px ">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Job
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
          					<li><a href="#!addJobForm">Add New Job</a></li>
          					<li><a href="#!viewAllJobs">View All Jobs</a></li>
          				</ul>
      				</li>
      				
      				
      				
      				<li ng-show="currentUser.role=='Role_User'" class="dropdown" style="padding-right:20px;padding-left:10px ">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Forum
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
          					<li><a href="#!addForum">Add Forum</a></li>
          					<li><a href="#!viewAllForums">View All Forums</a></li>
         				</ul>
         			</li>
         			
         				<li ng-show="currentUser.role=='Role_User'" class="dropdown" style="padding-right:20px;padding-left:10px ">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Job
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
          					<li><a href="#!viewAllJobs">View All Jobs</a></li>
          					<li><a href="#!viewAllAppliedJobs">View Applied Jobs</a></li>
          				</ul>
      				</li>
      				
         				<li ng-show="currentUser.role=='Role_User'" class="dropdown" style="padding-right:20px;padding-left:10px ">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Profile
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
          					<li><a href="#!viewProfile">View Profile</a></li>
          					<li><a href="#!updateProfile">Update Profile</a></li>
          					<li><a href="#!uploadProfilePicture">Upload Profile Picture</a></li>
         				</ul>
         			</li>
         		
      				<li ng-show="currentUser.role=='Role_User'" class="dropdown">
						<p class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;padding-top:10px">Friend
        				<span class="caret"></span></p>
        				<ul class="dropdown-menu">
        				
          					<li><a href="#!viewSuggestedFriends">View Suggested Friends</a></li>
          					<li><a href="#!showPendingRequests">Show Pending Requests</a></li>
          					<li><a href="#!showAllFriends">Show All Friends</a></li>
          				</ul>
      				</li>
      				
      					<li ng-show="currentUser.role=='Role_User'"><a href="#!startChat" style="color:white">Chat</a></li>
					
      				
				</ul>
				
			
				
				
				<ul class="nav navbar-nav navbar-right" ng-hide="currentUser==undefined" style="padding-top:20px">
					<li style="color:white;padding-right:20px;font-size:20px">Welcome {{currentUser.firstName}} {{currentUser.lastName}}</li>
					<li>
					
					
					<img id="img1"  
					src="http://localhost:4522/CollaborationProjectMiddleWare/viewProfilePicture/{{currentUser.loginName}}"
					onerror="if (this.src != 'images/profilepicture.png') this.src = 'images/profilepicture.png';"
					style="height:80px;width:100px" onshow="checkSRC"></li> 
								
					<li><form ng-controller="UserController"><input type="submit" value="LogOut" ng-click="logout()"></form></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<br/>
	<!-- Content -->
	<div ng-view></div>
	
	
	
	<br/><br/><br/><br/><br/><br/>
	<!-- Footer -->
	<footer>
		<div class="container-fluid" style="background-color:#34495E;height:40px;color:white;text-align:center;padding:10" >
			&copy; 2018 Copyright:LetsTalk.com	 
		</div>
	</footer>
	</body>
	
</html>