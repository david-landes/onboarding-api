(function() {
    angular.module('onboardApp')
        .factory('users', userFactory);

    userFactory.$inject = ['$http', 'config'];
    function userFactory($http, config) {

        return {
            getUser: getUser,
            getUsers: getUsers,
            createUser: createUser
        };

        function getUser(userId) {
            return $http.get(config.apiUrl + 'users/' + userId);
        }

        function getUsers() {
            return $http.get(config.apiUrl + 'users');
        }

        function createUser(user) {
            return $http.post(config.apiUrl + 'users', user);
        }
    }
}());

