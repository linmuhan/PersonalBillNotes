$(function () {
   var data4Vue = {
       uri : "validateUser",
       user:{name:'', password:''}
   };
   var vue = new Vue({
       el:"#workArea",
       data:data4Vue,
       mounted:function () {

       },
       methods:{
           login:function () {
               if(0 == this.user.name.length){
                   layer.msg("账号不能为空！请输入账号。");
                   return;
               }else if(0 == this.user.password.length){
                   layer.msg("密码不能为空！请输入密码。");
               }else{
                   var url = this.uri;
                   axios.post(url,vue.user).then(function (value) {
                       var data = value.data;
                       if(data.code == 0){
                           location.href = 'index/'+data.data;
                       }else if(data == 'noExist'){
                           layer.msg("账号不存在，请重试！");
                       }else if(data == 'error'){
                           layer.msg("登录错误，请重试！");
                       }
                   })
               }
           }
       }
   });

    layui.use('layer', function(){
        var layer = layui.layer;
    });

});