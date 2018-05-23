var listsapp = angular.module('listsapp', []);
listsapp.controller('movieListPostController', function($scope, $http, $location) {
    $scope.submitForm = function(){
        var url = $location.absUrl() + "movielists";
         
        var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        }
        var data = {
            nombre: $scope.nombre,
            ownerId: $scope.ownerId
        };
         
         
        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = "Lista creada";
        }, function (response) {
            $scope.postResultMessage = "Falló la creacion";
        });
         
        $scope.nombre = "";
        $scope.ownerId = "";
    }
});
 
listsapp.controller('movieListGetController', function($scope, $http, $location) {
    $scope.getfunction = function(){
        var url = $location.absUrl() + "movielists";
         
        var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        }
         
        $http.get(url, config).then(function (response) {
            $scope.response = response.data
        }, function (response) {
            $scope.getResultMessage = "Error!";
        });
    }
});