(function(){
    angular.module('myApp').controller('addUser', addUser);

    addUser.$inject = ['users'];

    function addUser(users) {
        var vm = this;

        vm.error = null;
        vm.success = false;

        vm.saveUser = saveUser;

        function saveUser() {
            // hard coded position for now...
            vm.user.position = {
                "id": 1,
                "position": "Contractor"
            };

            users.createUser(vm.user)
                .then(function(data) { vm.success = true; })
                .catch(function(error) {
                    console.log(error);
                    vm.error = "Sorry, we encountered an error.";
                });
        }
    }
}());