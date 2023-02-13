angular.module('application',[]).controller('indexController',function ($scope,$http) {
   // const contextPath ='http://localhost:8189/application';

    $scope.loadProducts = function(){
        $http.get('http://localhost:8189/winter/api/v1/products').then(function (response){
            $scope.productList = response.data;
        });
    }

    $scope.showProductInfo = function(productId){
        $http.get('http://localhost:8189/winter/api/v1/products/'+productId).then(function (response){
            alert(response.data.title);
        });
    }

    $scope.deleteProductById = function(productId){
        $http.delete('http://localhost:8189/winter/api/v1/products/'+productId).then(function (response){
        $scope.loadProducts();
        });
    }

    $scope.loadProductsInCart = function(){
        $http.get('http://localhost:8189/winter/api/v1/cart').then(function (response){
            $scope.cart = response.data;
        });
    }

    $scope.addProductToCart = function(productId){
        $http.post('http://localhost:8189/winter/api/v1/cart/add/'+productId).then(function (response){
            $scope.loadProductsInCart();
            });
        }

    $scope.clearCart = function(){
        $http.delete('http://localhost:8189/winter/api/v1/cart').then(function (response){
            $scope.loadProductsInCart();
            });
        }

    $scope.loadProducts();
    $scope.loadProductsInCart();

//    $scope.loadProducts =function(){
//        $http.get(contextPath +'/products')
//            .then(function (response){
//                $scope.getProductList=response.data;
//            });
//    };
//
//    $scope.filterByCost = function(){
//        console.log($scope.filters);
//        $http({
//            url:contextPath +'/products/filter/diapasone',
//            method: 'GET',
//            params:{
//                minCost: $scope.filters.min,
//                maxCost: $scope.filters.max
//            }
//        })
//            .then(function(response){
//                $scope.getProductList=response.data;
//        });
//    };
//
//    $scope.removeProduct = function(id){
//        $http.get(contextPath+'/products/delete/'+id)
//            .then(function(response){
//                $scope.loadProducts();
//            });
//    };
//
//    $scope.addProduct = function(){
//          $http({
//              url:contextPath+'/products/add',
//              method: 'POST',
//              params:{
//                 title:document.getElementById('title').value,
//                 price:document.getElementById('price').value
//              }
//          })
//              .then(function(response){
//                  $scope.loadProducts();
//              });
//      };
//
//    $scope.loadProducts();

});