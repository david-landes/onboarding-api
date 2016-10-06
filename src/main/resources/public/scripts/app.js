(function(){
    angular.module("myApp", ["ngRoute"])
        .config(function ($routeProvider) {
            $routeProvider
                .when("/home", {
                    templateUrl: "partials/dashboard.html",
                    controller: "dashboard"
                })
                .when("/accountInfo", {
                    templateUrl: "partials/accountInfo.html",
                    controller: "accountInfo"
                })
                .when("/dayOne", {
                    templateUrl: "partials/dayone.html",
                    controller: "dayOne"
                })
                .when("/weekly", {
                    templateUrl: "partials/weekly.html",
                    controller: "weekly"
                })
                .when("/ongoing", {
                    templateUrl: "partials/ongoing.html",
                    controller: "ongoing"
                })
                .when("/generalInfo", {
                    templateUrl: "partials/generalInfo.html",
                    controller: "generalInfo"
                })
                .when("/addUser", {
                    templateUrl: "partials/addUser.html",
                    controller: "addUser",
                    controllerAs: "vm"
                })
                .when("/users1", {
                    templateUrl: "partials/users1.html",
                    controller: "users1"
                });
        })
        .controller("dashboard", function ($scope) {
            $scope.message = "Dashboard"
        })
        .controller("accountInfo", function ($scope) {
            $scope.message = "Account Info"
        })
        .controller("dayOne", function ($scope) {
            $scope.message = "Day One"
        })
        .controller("weekly", function ($scope) {
            $scope.message = "Weekly"
        })
        .controller("ongoing", function ($scope) {
            $scope.message = "Ongoing"
        })
        .controller("generalInfo", function ($scope) {
            $scope.message = "General Info"
        });
}());
