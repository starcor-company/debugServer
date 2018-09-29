# debugServer

##### 简介：主要用于 作为xul项目模拟数据的服务器

#### 需求分析

创建独立的服务器程序来进行对xul项目模拟数据的添加

#### 开发设计方案

- 使用github仓库存储代码，使用git提交流程
- 使用Kotlin编程
- 使用Jfinal开发框架 https://www.w3cschool.cn/jfinal/
- 完成Plugin开发

#### 使用说明

1. 安装插件：
	- 打开idea 或者 android studio 插件管理工具，选择从桌面安装，安装完成后重启idea或者android studio
	- 完成安装后可见，如下图，点击即可使用
	![](https://i.imgur.com/5A1eIOq.png)
2. 使用相关
	- 如下图所示：
	![](https://i.imgur.com/5OVsM2P.png)
3. 注意事项
	- 在模拟数据时，需带有主地址和想要访问的接口参数
	- 示例： http://192.168.137.1:7777?apiname=n40_a_1
	- 参数格式固定为apiname=接口名称
	- 如使用xul框架访问，示例如下图：
	![](https://i.imgur.com/2ZlR3t1.png)
	
