var app = angular.module("onboardApp", []);

app.controller('myCtrl', function($scope, $http){
	$http.get('http://onboarding.stgconsulting.com:8080/api/users/1').
    then(function(response) {
        $scope.greeting = response.data;
        
    });
	console.log('Im here');
});