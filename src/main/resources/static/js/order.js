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
    }).state('manage.adminCount', {
        url: '/adminCount',
        templateUrl: 'templates/adminCount/adminCount.html',
        controller: 'adminCountCtrl'
    }).state('manage.audit', {
        url: '/audit',
        templateUrl: 'templates/leader/audit.html',
        controller: 'auditCtrl'
    }).state('manage.overTimeApplication', {
        url: '/overTimeApplication',
        templateUrl: 'templates/overTime/overTimeApplication.html',
        controller: 'overTimeApplicationCtrl'
    }).state('manage.dishesOfTheDay', {
        url: '/dishesOfTheDay',
        templateUrl: 'templates/dishes/dishesOfTheDay.html',
        controller: 'dishesOfTheDayCtrl'
    });
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
        username: '',
        name:''
    }

    //查询用户信息
    $scope.search = function () {
        console.log($scope.cond.username);
        $http({
            url: 'getusers',
            method: 'get',
            params: {
                username:$scope.cond.username,
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
                //获取用户类型
                $http({
                    url: 'getDictValue',
                    method: 'get',
                    params: {
                        dictType:'user_type',
                    }
                }).then(function (resp) {
                    $scope.userTypes=resp.data.dicts;
                });

                //获取用户所属组
                $http({
                    url: 'getDictValue',
                    method: 'get',
                    params: {
                        dictType:'group_type',
                    }
                }).then(function (resp) {
                    $scope.groups=resp.data.dicts;
                });

                $scope.user = {
                    username: '',
                    password: '',
                    confirm: '',
                    userType:'',
                    groupId:'',
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
                    if($scope.user.userType == ''){
                        $scope.userTypeTip = '请选择用户身份';
                        return;
                    }else {
                        $scope.userTypeTip = '';
                    }
                    if($scope.user.groupId == ''){
                        $scope.groupIdTip = '请选择用户所属组';
                        return;
                    }else {
                        $scope.groupIdTip = '';
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
app.controller('dishesOfTheDayCtrl', function ($scope, $http,$uibModal, $state) {

    $scope.pageNumbers = [20, 30, 50,100];
    $scope.displayCount = "20";//默认显示条数

    $scope.selectedAll = false;//全选，默认为false
    $scope.selectedDishes = [];
    $scope.chooseAll = function(){
        if($scope.selectedAll){
            for(let i=0; i<$scope.dishes.length; i++){
                $scope.dishes[i].selected = true;
                $scope.selectedDishes.push($scope.dishes[i].id);
            }
        } else {
            $scope.selectedDishes = [];
            for(let i=0; i<$scope.dishes.length; i++){
                $scope.dishes[i].selected = false;
            }
        }
    }
    $scope.choose = function(dish){
        if(dish.selected) {//选中，直接放入选中的数组
            $scope.selectedDishes.push(dish.id);
        } else {//没有选中，则从数组从移除
            for(let i=0; i<$scope.selectedDishes.length; i++){
                if($scope.selectedDishes[i] == dish.id){
                    $scope.selectedDishes.splice(i, 1);
                    break;
                }
            }
        }
        $scope.selectedAll = ($scope.selectedUsernames.length == $scope.totalCount);
    }

    //查询已配置当日菜品信息
  /*  $scope.search = function () {
        $http({
            url: 'getdishes',
            method: 'get',
        }).then(function (resp) {
            $scope.dishes=resp.data.dishes;
        });
    }*/

    //添加当日菜单信息
    $scope.showAddModal = function () {
        let parent = $scope;
        $uibModal.open({
            size: 'md',
            backdrop: 'static',
            templateUrl: 'templates/dishes/dishesAddModal.html',
            controller: function ($scope, $uibModalInstance) {

                $scope.cond={
                    name:'',
                    adaptTime:'',
                    counts:1
                }
                //查询已经有的菜品信息
                $scope.search = function () {
                    $http({
                        url: 'getdishes',
                        method: 'get',
                        params:{
                            name:$scope.cond.name
                        }
                    }).then(function (resp) {
                        $scope.dishes=resp.data.dishes;
                    });
                }

                $scope.selectedAll = false;//全选，默认为false
                $scope.selectedDishes = [];
                $scope.chooseAll = function(){
                    if($scope.selectedAll){
                        for(let i=0; i<$scope.dishes.length; i++){
                            $scope.dishes[i].selected = true;
                            $scope.selectedDishes.push($scope.dishes[i].id);
                        }
                    } else {
                        $scope.selectedDishes = [];
                        for(let i=0; i<$scope.dishes.length; i++){
                            $scope.dishes[i].selected = false;
                        }
                    }
                }
                $scope.choose = function(dish){
                    if(dish.selected) {//选中，直接放入选中的数组
                        $scope.selectedDishes.push(dish.id);
                    } else {//没有选中，则从数组从移除
                        for(let i=0; i<$scope.selectedDishes.length; i++){
                            if($scope.selectedDishes[i] == dish.id){
                                $scope.selectedDishes.splice(i, 1);
                                break;
                            }
                        }
                    }
                    $scope.selectedAll = ($scope.selectedDishes.length == $scope.totalCount);
                }

                $scope.save = function(){
                    delete $scope.user.confirm;
                    $http({
                        url: 'MenuOfTheDay',
                        method: 'post',
                        params: {
                            adaptTime:$scope.cond.adaptTime,
                            ids:$scope.selectedDishes,
                            counts:$scope.cond.counts,
                        }
                    }).then(function (resp) {
                        if(resp.data == 1){//添加成功
                            alert("添加成功");
                            $scope.cancel();
                            parent.search();
                        } else{
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
app.controller('adminCountCtrl', function ($scope, $http,$uibModal, $state) {

    $scope.pageNumbers = [20, 30, 50,100];
    $scope.displayCount = "20";//默认显示条数


    //查询统计信息
    $scope.search = function () {

        $http({
            url: 'getTodayAdminCount',
            method: 'get',
            params: {
               page:0
            }
        }).then(function (resp) {
            $scope.counts=resp.data.todayList;
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
                //获取用户类型
                $http({
                    url: 'getDictValue',
                    method: 'get',
                    params: {
                        dictType:'user_type',
                    }
                }).then(function (resp) {
                    $scope.userTypes=resp.data.dicts;
                });

                //获取用户所属组
                $http({
                    url: 'getDictValue',
                    method: 'get',
                    params: {
                        dictType:'group_type',
                    }
                }).then(function (resp) {
                    $scope.groups=resp.data.dicts;
                });

                $scope.user = {
                    username: '',
                    password: '',
                    confirm: '',
                    userType:'',
                    groupId:'',
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
                    if($scope.user.userType == ''){
                        $scope.userTypeTip = '请选择用户身份';
                        return;
                    }else {
                        $scope.userTypeTip = '';
                    }
                    if($scope.user.groupId == ''){
                        $scope.groupIdTip = '请选择用户所属组';
                        return;
                    }else {
                        $scope.groupIdTip = '';
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

app.controller('auditCtrl', function ($scope, $http,$uibModal, $state) {

    //查询组内成员信息
    $scope.searchMenbers = function () {

        $http({
            url: 'leaderErgodicRequests',
            method: 'get',
            params: {

            }
        }).then(function (resp) {
            $scope.members=resp.data.leaderList;
        });
    }

    $scope.agreeApply = function (user_id) {

        $http({
            url: 'leaderAgreeOverTime',
            method: 'get',
            params: {
                userId:user_id
            }
        }).then(function (resp) {
            $scope.agreeResult=resp.data.agreeResult;
            alert( $scope.agreeResult);
        });
    }

});

app.controller('overTimeApplicationCtrl', function ($scope, $http,$uibModal, $state) {

    //查询组内成员信息
    $scope.overTimeApply = function () {

        $http({
            url: 'getAdminCountById',
            method: 'get',
            params: {
            }
        }).then(function (resp) {
            $scope.adminLists=resp.data.adminLists;
        });
    }
});


