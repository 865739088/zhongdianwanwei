let app = angular.module('orderApp', ['ui.router', 'ui.bootstrap']);
app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'loginCtrl'
    }).state('manage', {
        url: '/manage',
        templateUrl: 'templates/manage.html',
        controller: 'manageCtrl'
    }).state('manage.user', {
        url: '/user',
        templateUrl: 'templates/user/user.html',
        controller: 'userCtrl'
    }).state('manage.dishes', {
        url: '/dishes',
        templateUrl: 'templates/dishes/dishes.html',
        controller: 'dishesCtrl'
    });;
    $urlRouterProvider.otherwise("/login");
});
app.controller('loginCtrl', function ($scope, $http, $state) {
    $scope.error=false;
    $scope.user = {
        username: 'admin',
        password: 'admin'
    };
    $scope.login = function () {
        if($scope.user.username == ''){
            $scope.error=true;
            $scope.errorMsg = "用户名不能为空";
            return;
        }
        if($scope.user.password == ''){
            $scope.error=true;
            $scope.errorMsg = "密码不能为空";
            return;
        }
        $scope.errorMsg = '';
        $http({
            url: 'login',
            method: 'get',
            params: {
                userName:$scope.user.username,
                password:$scope.user.password
            }
        }).then(function (resp) {
            if(resp.data.msg == 1){
                $state.go("manage");
            } else if(resp.data.msg == -1){
                $scope.error=true;
                $scope.errorMsg = '用户名不存在，请先注册';
            } else {
                $scope.error=true;
                $scope.errorMsg = '用户名或密码错误，请重新输入';
            }
        });
    }

});
app.controller('manageCtrl', function ($scope, $http, $state) {
/*    $scope.init = function () {
        $http({
            url: 'menu',
            method: 'get',
        }).then(function (resp) {
            $scope.menus = resp.data.menus;
            for(let i=0; i<$scope.menus.length; i++){
                if($scope.menus[i].router == $scope.currentRoute){
                    $scope.menus[i].selected = true;//选中菜单
                    break;
                }
            }
            $scope.currentUser = resp.data.user;
        });
    }
    $scope.select = function (menu) {
        for(let i=0; i<$scope.menus.length; i++){
            $scope.menus[i].selected = ($scope.menus[i] == menu);
        }
    }
    $scope.init();//路由模板加载完成之后开始执行*/
});
app.controller('userCtrl', function ($scope, $http,$uibModal, $state) {

    $scope.pageNumbers = [20, 30, 50,100];
    $scope.displayCount = "20";//默认显示条数

    $scope.cond={
        userName: '',
        name:''
    }
    let user = $scope.user;

    //查询用户信息
    $scope.search = function () {
        $http({
            url: 'getusers',
            method: 'get',
            params: {
                userName:$scope.cond.userName,
                name:$scope.cond.name
            }
        }).then(function (resp) {
            $scope.users=resp.data.users;
        });
    }

    //添加用户信息
    $scope.showAddModal = function () {
        let parent = $scope;
        $uibModal.open({
            size: 'md',
            backdrop: 'static',
            templateUrl: 'templates/user/userAddModal.html',
            controller: function ($scope, $uibModalInstance) {
                $scope.user = {
                    username: '',
                    password: '',
                    confirm: '',
                    name: ''
                };
                $scope.save = function(){
                    if($scope.user.username == ''){
                        $scope.usernameTip = '请输入用户名';
                        return;
                    } else {
                        $scope.usernameTip = '';
                    }
                    if($scope.user.password == ''){
                        $scope.passwordTip = '请输入密码';
                        return;
                    }else {
                        $scope.passwordTip = '';
                    }
                    if($scope.user.confirm != $scope.user.password){
                        $scope.confirmTip = '两次密码输入不一致';
                        return;
                    }else {
                        $scope.confirmTip = '';
                    }
                    if($scope.user.name == ''){
                        $scope.nameTip = '请输入姓名';
                        return;
                    }else {
                        $scope.nameTip = '';
                    }
                    //删除无用属性
                    delete $scope.user.confirm;
                    $http({
                        url: 'user',
                        method: 'post',
                        params: $scope.user
                    }).then(function (resp) {
                        if(resp.data == 1){//添加成功
                            alert("添加成功");
                            $scope.cancel();
                            parent.search();
                        } else if(resp.data == -1){
                            alert("该用户名已被注册");
                        } else {
                            alert("添加失败");
                        }
                    })
                }

                $scope.cancel = function () {
                    $uibModalInstance.close();
                }
            }
        });
    }

});
app.controller('dishesCtrl', function ($scope, $http, $state) {
    $scope.pageNumbers = [20, 30, 50,100];
    $scope.displayCount = "20";//默认显示条数

    $scope.search = function () {
        $http({
            url: 'getdishes',
            method: 'get',

        }).then(function (resp) {
            $scope.dishes=resp.data.dishes;
        });
    }
});