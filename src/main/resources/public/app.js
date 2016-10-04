(function(){
    angular.module("onboardApp", []).controller('myCtrl', myCtrl);

    myCtrl.$inject = ['$scope', 'users'];

    function myCtrl($scope, users) {
        users.getUser(1)
            .then(function(response) {
                $scope.greeting = response.data;
            });
        console.log('Im here');
    }
}());