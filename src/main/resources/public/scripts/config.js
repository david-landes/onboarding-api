(function(){
    angular.module('onboardApp').factory('config', config);

    function config() {
        return {
            apiUrl: 'http://localhost:8080/'
        }
    }
}());