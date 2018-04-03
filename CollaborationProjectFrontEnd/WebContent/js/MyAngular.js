/*var myApp=angular.module("myApp",['ngRoute']);
*/
var myApp=angular.module("myApp",['ngRoute',,'ngCookies']);

myApp.config(function($routeProvider)
{
	$routeProvider
	.when("/",{
		templateUrl:"home.html"
	})
	.when("/contact",{
		templateUrl:"contactus.html"
	})
	.when("/login",{
		templateUrl:"user/Login.html"
	})
	.when("/register",{
		templateUrl:"user/Register.html"
	})
	.when("/blog",{
		templateUrl:"Blog.html"
	})
	.when("/forum",{
		templateUrl:"Forum.html",
	})
	.when("/job",{
		templateUrl:"Job.html"
	})
	.when("/UserHome",{
		templateUrl:"user/UserHome.html"
	})
	.when("/logout",{
		templateUrl:"user/LogOut.html"
	})
	.when("/about",{
		templateUrl:"aboutus.html"
	})
	.when("/addBlog",{
		templateUrl:"blog/AddBlog.html"
	})
	.when("/displayProfile",{
		templateUrl:"user/UploadProfilePicture.html"
	})
	.when("/uploadProfilePicture",{
		templateUrl:"user/UploadProfilePicture.html"
	})

});


myApp.run(function($rootScope,$cookieStore)
		{
			console.log('I am in run function');
			console.log($rootScope.currentUser);
			
				if($rootScope.currentUser==undefined)
				{
				$rootScope.currentUser=$cookieStore.get('userDetails');
				}
				else
				{
				console.log($rootScope.currentUser.userName);
				console.log($rootScope.currentUser.role);
				}
		});
