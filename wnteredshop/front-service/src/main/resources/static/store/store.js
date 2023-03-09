angular.module('market').controller('storeController', function ($scope, $http,  $localStorage) {

    const cartContextPath ='http://localhost:5555/cart/';
    const contextPath ='http://localhost:5555/core/';

    $scope.loadProducts = function (page = 1) {
        $http({
            url: 'http://localhost:5555/core/api/v1/products',
            method: 'GET',
            params: {
                p: page,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.generatePagesList($scope.productsPage.totalPages);
        });
    };



    $scope.showProductInfo = function(productId){
        $http.get(contextPath+'api/v1/products/'+productId).then(function (response){
            alert(response.data.title);
        });
    }

    $scope.deleteProductById = function(productId){
        $http.delete(contextPath+'api/v1/products/'+productId).then(function (response){
        $scope.loadProducts();
        });
    }

    $scope.loadProductsInCart = function(){
        $http.get(cartContextPath+'api/v1/cart').then(function (response){
            $scope.cart = response.data;
        });
    }

    $scope.addProductToCart = function(productId){
        $http.post(cartContextPath+'api/v1/cart/add/'+productId).then(function (response){
        $scope.loadProductsInCart();
        });
    }

    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }

    $scope.loadProducts();
});