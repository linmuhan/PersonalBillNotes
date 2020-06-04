### 个人账单系统
使用springboot+mybatis整合开发，配置了数据源druid,进行sql监控。
前台使用chart.js画表，layui+bootstarp做页面效果，vue进行数据绑定和请求接口。
整个项目严格遵守了Restful风格开发，并实现多用户注册登录单独使用。
具体界面展示效果：
##### （1）登录注册
![](https://img-blog.csdnimg.cn/20200604233615219.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/20200604233635943.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
##### （2）设置月消费额度
![](https://img-blog.csdnimg.cn/20200604233723707.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
##### （3）管理自己想要的消费分类
![](https://img-blog.csdnimg.cn/20200604233850481.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/20200604233953976.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/2020060423401330.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/2020060423403462.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
##### （4）记一笔
![](https://img-blog.csdnimg.cn/20200604234110102.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/20200604234141751.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
##### （6）消费账单
![](https://img-blog.csdnimg.cn/2020060423422841.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
##### （7）消费一览
![](https://img-blog.csdnimg.cn/20200604234253739.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTA1MDIw,size_16,color_FFFFFF,t_70)
### 总结
整个项目的结构十分简单，就是进行账单的记录和操作等。难点是在于梳理一些这些逻辑，梳理清楚后就能键步如飞了！
