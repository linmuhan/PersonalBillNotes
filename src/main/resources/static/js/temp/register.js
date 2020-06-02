$(function () {
    var data4Vue = {
        uri : "insertUser",
        data : {name:'',password:''},
        user : {name:'', password:'',surePassword:''}
    };

    var vue = new Vue({
        el:"#workArea",
        data:data4Vue,
        mounted:function () {

        },
        methods:{
            register:function () {
                if(0 == this.user.name.length){
                    layer.msg("账号不能为空！");
                    return;
                }else if(0 == this.user.password.length){
                    layer.msg("密码不能为空！");
                    return;
                }else if(0 == this.user.surePassword.length || this.user.password != this.user.surePassword){
                    layer.msg("两次密码不一致！")
                    return;
                }else{
                    this.data.name = this.user.name;
                    this.data.password  = this.user.password;
                    var url = this.uri;
                    axios.post(url,vue.data).then(function (value) {
                        var data = value.data;
                        if(data == "ok"){
                            layer.msg("注册成功！");
                            vue.user.name = "";
                            vue.user.password = "";
                            vue.user.surePassword = "";
                        }else if(data == "exist"){
                            layer.msg("该用户名已经被占用");
                        }else if(data == "error"){
                            layer.msg("注册失败！请重试。")
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