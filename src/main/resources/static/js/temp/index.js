$(function () {

    /*补一下上传*/
    var data4Vue = {
        user:{id:'',name:'',password:''},
        welUrl:"/welUser",
        addCategoryUrl:"/addCategory",
        deleteCategoryUrl:"/deleteCategory",
        updateCategoryUrl:"/updateCategory",
        listCategory:"/listCategory",
        listRecordUrl:"/listRecord",
        addRecordUrl:"/addRecord",
        monthCostUrl:"/monthRecord",
        setConfigUrl:"/setConfig",
        pathId:'',
        addOfCategory:{id:'',name:'',uid:''},
        updateOfCategory:{id:'',name:'',uid:''},
        category:{id:'',name:'',uid:''},
        categories:[],
        record:{id:'',spend:'',cid:'',comment:'',date:'',uid:'',categoryName:''},
        listRecord:[],
        monthCost:[],
        config:{id:'',value:'',uid:''},
        costChart:{monthCost:'',todayCost:'',surplusCost:'',dailyAvg:'',dailyUse:'',surplusDay:''},
        dataChart:[]
    };
    var vue = new Vue({
        el: "#workArea",
        data: data4Vue,
        mounted:function () {
            this.prepareFor();
            this.welcomeName();
            this.loadRecord();
            this.loadChart();
            this.loadCostChart();
        },
        updated:function(){
            layui.use('form', function(){
                var form = layui.form;
                form.render(null,'formTest');
            });
        },
        methods:{
            prepareFor:function(){
                var val = location.pathname.substring(7);
                this.pathId = val;
                if(this.pathId != ''){
                    this.loadCategory();
                }
            },
            welcomeName:function () {
                if(this.pathId != null){
                    var url = this.welUrl + "/" + this.pathId;
                    axios.get(url).then(function (value) {
                        var data = value.data.data;
                        vue.user = data;
                    })
                }
            },
            loadChart:function(){
              var url = this.monthCostUrl + "/" + this.pathId;
              axios.get(url).then(function (value) {
                  var data = value.data;
                  if(data.code == 0){
                      vue.monthCost = data.data;
                      var ctx = document.getElementById('myChart').getContext('2d');
                      var ctx2 = document.getElementById('chartPie').getContext('2d');
                      var myChart = new Chart(ctx, {
                          type: 'bar',
                          data: {
                              labels: ['1月', '2月', '3月', '4月', '5月', '6月','7月', '8月', '9月', '10月', '11月', '12月'],
                              datasets: [{
                                  label: '今年月消费柱状图',
                                  data: vue.monthCost,
                                  borderColor:'blue',
                                  borderWidth: 1,
                                  fill: false
                              }]
                          },
                          options: {
                              tooltips: {
                                  intersect: false,
                                  mode: 'index'
                              }
                          }
                      });
                      var chartPie = new Chart(ctx2, {
                          type: 'pie',
                          data: {
                              labels: ['1月', '2月', '3月', '4月', '5月', '6月','7月', '8月', '9月', '10月', '11月', '12月'],
                              datasets: [{
                                  label: '今年月消费饼图',
                                  data: vue.monthCost,
                                  borderColor:'lightGray',
                                  backgroundColor:['pink','skyblue','LightYellow','LawnGreen','MediumPurple','orange','red','blue','green','yellow','grey','#68fff8'],
                                  borderWidth: 1
                              }]
                          }
                      });
                  }else{
                      console.log("加载今年月消费记录失败！")
                  }
              });
            },
            loadCostChart:function(){
                var costUrl =  "/costChart"+"/"+ this.pathId;
                axios.get(costUrl).then(function (value) {
                    var data = value.data;
                    if(data.code == 0){
                        vue.costChart = data.data;
                        vue.dataChart[0] = vue.costChart.monthCost;
                        vue.dataChart[1] = vue.costChart.surplusCost;
                        var ctxx = document.getElementById('doughnutCost').getContext('2d');
                        var myCharts = new Chart(ctxx, {
                            type: 'doughnut',
                            data: {
                                labels: ['已消费', '未消费'],
                                datasets: [{
                                    label: '本月消费图',
                                    data: vue.dataChart,
                                    borderColor:'lightGray',
                                    backgroundColor:['lightgreen','skyblue'],
                                    borderWidth: 1
                                }]
                            }
                        });
                    }else{
                        console.log("加载月消费一览图失败！");
                    }
                })
            },
            loadCategory:function(){
                var url = this.listCategory+"/"+this.pathId;
                axios.get(url).then(function (value) {
                    var data = value.data;
                    if(data.code == 0){
                        var beans = data.data;
                        vue.categories = beans;
                    }else{
                        console.log("加载失败！")
                    }
                })
            },
            deleteCategory:function(id){
                layer.open({
                    type: 1,
                    title: '您确认要删除该条分类吗？',
                    area: ['300px', '100px'],
                    content: '',
                    btn: ['确认','取消'],
                    yes: function(index, layero){
                        layer.close(index);
                        vue.addOfCategory.id = id;
                        vue.addOfCategory.uid = vue.pathId;
                        axios.post(vue.deleteCategoryUrl,vue.addOfCategory).then(function (value) {
                            var data = value.data;
                            if(data.code == 0){
                                layer.msg("删除成功！");
                                vue.addOfCategory.name = '';
                                vue.addOfCategory.id = '';
                                vue.loadCategory();
                            }else{
                                layer.msg("删除失败！");
                            }
                        })
                    },
                    '取消':function (index,layero) {

                    }
                })
            },
            updateCategory(id,name){
                vue.updateOfCategory.name = name;
                vue.updateOfCategory.id = id;
                vue.updateOfCategory.uid = vue.pathId;
                layer.open({
                    type: 1,
                    title: '您正在对该条分类进行编辑',
                    area: ['350px', '200px'],
                    content: $('#updateInfo'),
                    btn: ['确认','取消'],
                    yes:function (index,layero) {
                        var url = vue.updateCategoryUrl;
                        axios.put(url,vue.updateOfCategory).then(function (value) {
                            var data = value.data;
                            if(data.code == 0){
                                layer.msg("更新成功！");
                                vue.updateOfCategory.id = '';
                                vue.updateOfCategory.name = '';
                                vue.updateOfCategory.uid = '';
                                vue.loadCategory();
                            }else{
                                layer.msg("更新失败！");
                            }
                        });
                        layer.close(index);
                    },
                    '取消':function (index,layero) {

                    }
                })
            },
            funAddCategory:function () {
                this.addOfCategory.uid = this.pathId;
                if(vue.addOfCategory.name != ''){
                    var url = vue.addCategoryUrl;
                    axios.post(url,vue.addOfCategory).then(function (value) {
                        var data = value.data;
                        if(data.code == 0){
                            layer.msg("添加成功");
                            vue.loadCategory();
                            vue.addOfCategory.name = '';
                        }else{
                            layer.msg("添加失败");
                        }
                    })
                }
            },
            funAddRecord:function () {
                this.record.uid = this.pathId;
                var url = this.addRecordUrl;
                this.record.date = $('#timeSelect').val();
                var str = $('#selectC').val().split('?');
                this.record.cid = str[0];
                this.record.categoryName = str[1];
                axios.post(url,vue.record).then(function (value) {
                    var data = value.data;
                    if(data.code == 0){
                        vue.record.comment = '';
                        vue.record.date = '';
                        vue.record.spend = '';
                        vue.record.cid = '';
                        vue.record.categoryName = '';
                        layer.msg("记录成功！");
                        vue.loadRecord();
                        vue.loadChart();
                        vue.loadCostChart();
                    }else{
                        layer.msg("记录失败！请重试。")
                    }
                })
            },
            loadRecord:function () {
                var url = this.listRecordUrl + "/" + this.pathId;
                axios.get(url).then(function (value) {
                    var data = value.data;
                    if(data.code == 0){
                        vue.listRecord = data.data;
                    }else{
                        console.log("加载账单失败!");
                    }
                })
            },
            toTop:function () {
                document.body.scrollTop = document.documentElement.scrollTop = 0;
            },
            quit:function(){
                var url = "/quit" + "/" + this.pathId;
                axios.get(url).then(function (value) {
                  var data = value.data;
                  if(data.code == 0){
                      window.location.href = '/login';
                  }else{
                      layer.msg("系统繁忙，请稍后再试。")
                  }
              })
            },
            setConfig:function () {
                var url = this.setConfigUrl;
                this.config.uid = this.pathId;
                axios.post(url,vue.config).then(function (value) {
                    var data = value.data;
                    if(data.code == 0){
                        layer.msg("设置成功！");
                        vue.config.value = '';
                        vue.loadCostChart();
                    }else{
                        layer.msg("设置失败！");
                    }
                })
            }
        }
    });

    layui.use('layer', function(){
        var layer = layui.layer;
        $('#addLayer').click(function () {
            layer.open({
                type: 1,
                title: '增加分类',
                area: ['350px', '200px'],
                content: $("#addInfo"),
                btn:'确认添加',
                yes: function(index, layero){
                    layer.close(index);
                    vue.funAddCategory();
                }
            })
        })
    });
    layui.use('element', function(){
        var element = layui.element;

        //一些事件监听
        element.on('tab(demo)', function(data){
            console.log(data);
        });
    });
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#timeSelect' //指定元素
        });
    });
    layui.use('form', function(){
        var form = layui.form;
    });

});