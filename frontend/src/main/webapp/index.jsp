<html>
	<head>
		<title>LetsTalk</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
  		<script src="js/MyAngular.js"></script>
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
    			<ul class="nav navbar-nav navbar-right" style="margin-top:20px;font-size:20px;font-weight:bold;">
					<li><a href="#/!" style="color:white">Home</a></li>
					<li><a href="#!about" style="color:white">About Us</a></li>
					<li><a href="#!contact" style="color:white">Contact Us</a></li>
					<li><a href="#!login" style="color:white"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
					<li><a href="#!register" style="color:white"><span class="glyphicon glyphicon-user"></span>Register</a></li>
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