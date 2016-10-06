(function(){
    angular.module('myApp').factory('config', config);

    function config() {
        return {
            // apiUrl: 'http://onboarding.stgconsulting.com:8080/api/'
            apiUrl: 'http://localhost:8080/'
        }
    }
}());