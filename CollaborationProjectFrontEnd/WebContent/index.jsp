<html>
	<head>
		<title>LetsTalk</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-cookies.js"></script>
		<script src="js//MyAngular.js"></script>
  		<script src="user//UserController.js"></script>
  		<link rel="stylesheet" href="css/app.css"/>
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
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!blog" style="color:white">Blog</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!forum" style="color:white">Forum</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!job" style="color:white">Job</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!about" style="color:white">About Us</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!contact" style="color:white">Contact Us</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!login" style="color:white"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
					<li ng-hide="currentUser.role=='Role_User'||currentUser.role=='Role_Admin'"><a href="#!register" style="color:white"><span class="glyphicon glyphicon-user"></span>Register</a></li>
					<li ng-show="currentUser.role=='Role_User'" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white">Blog
        				<span class="caret"></span></a>
        				<ul class="dropdown-menu">
          					<li><a href="#">Add Blog</a></li>
          					<li><a href="#">View All Blogs</a></li>
          				</ul>
      				</li>
				</ul>
				<ul class="nav navbar-nav navbar-right" ng-hide="currentUser==undefined" style="padding-top:20px">
					<li style="color:white;padding-right:20px;font-size:20px">Welcome {{currentUser.firstName}} {{currentUser.lastName}}</li>
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