angular.module('market').controller('registrationController', function ($scope, $http, $location, $localStorage) {
    const contextPath ='http://localhost:5555/auth/';

    $scope.registerFunction = function () {
       $http.post('http://localhost:5555/auth/registration', $scope.user).then(function (response) {
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.wnteredShopUser = {username: $scope.user.username, token: response.data.token};

                $scope.user.username = null;
                $scope.user.password = null;
            }
            $location.path("/")
        });
    };

});