(function(){
    angular.module('myApp').factory('config', config);

    function config() {
        return {
            apiUrl: 'http://localhost:8080/'
        }
    }
}());