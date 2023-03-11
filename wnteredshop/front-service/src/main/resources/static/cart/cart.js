angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage) {

    const contextPath ='http://localhost:5555/cart/';
    const coreContextPath ='http://localhost:5555/core/';

    $scope.loadProductsInCart = function(){
        $http.get(contextPath+'api/v1/cart/'+$localStorage.wnteredShopGuestCartId).then(function (response){
            $scope.cart = response.data;
        });
    }



    $scope.clearCart = function(){
        $http.delete(contextPath+'api/v1/cart/'+$localStorage.wnteredShopGuestCartId).then(function (response){
            $scope.loadProductsInCart();
            });
        }

    $scope.deleteItem = function(id){
        $http.delete(contextPath+'api/v1/cart/'+$localStorage.wnteredShopGuestCartId+'/'+id).then(function (response){
            $scope.loadProductsInCart();
        });
    }

    $scope.changeQuantity = function(id,delta){

        $http({
            url:contextPath+'api/v1/cart/'+$localStorage.wnteredShopGuestCartId+'/change',
            method: 'POST',
            params:{
                id:id,
                delta:delta
            }
        })
            .then(function (response){
                 $scope.loadProductsInCart();
            });
    }


        $scope.createOrder = function () {
            $http.post(coreContextPath+'api/v1/orders')
                .then(function (response) {
                    alert("Заказ оформлен")
                    $scope.loadProductsInCart();
                });
        }

        $scope.guestCreateOrder = function () {
            alert('Для оформления заказа необходимо войти в учетную запись');
        }

        $scope.loadProductsInCart();

});