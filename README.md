# a-multi-shop
多商铺平台，请捐赠我们！  

成果：  
1.有管理后台+前台产品的展示页面。  
2.用户分有管理员、店铺店长、店铺成员和普通用户4种类型。  
3.不说那么多，先给个网址你们看下效果：http://39.107.87.88/multiShop/ 本网址到2018-12-01将过期。  
4.该网站具体有哪些功能，可以参看文档目录的详细设计，只是还有平台的微信和QQ快捷登陆，以及论坛那方面还没时间弄而已。  

技术：  
1.建议用eclipse编码。  
2.前端用到了angularjs、jQuery和bootstrap框架，后端用到了strust2、spring和hibernate框架，数据库是单库形式用mysql 5.7，服务器用tomcat 8.5。  

项目搭建：  
（注意，这里项目搭建需要有一点web项目搭建经验和eclipse使用经验的，一些比较基础的操作在本文中就没有提到，若看到不太懂的可以百度一下）  
1.需要下载jdk 1.8。  
2.需要数据库mysql 5.7，并创建一个命名为multishop，编码为utf8的数据库。  
3.需要下载tomcat 8.5。  
4.将eclipse的jdk和tomcat环境配置成上面几步下载的jdk 1.8和tomcat 8.5。  
5.在eclipse中导入项目，code目录下的multiShop。  
6.项目导入进来后，可能会有一些编码错误（一般情况下是不会的，如果在第4步把jdk和tomcat都配置好的话，若没有问题的话可以直接跳过此步），这时就需要自己再配置一下该项目的jdk和tomcat环境了，当然也有可能是一些jar包的指向有问题。  
7.到项目代码目录的multiShop\src\config\jdbc下，修改数据库配置文件jdbc.properties：数据库的ip地址和端口号，数据库的登陆用户名和密码。  
8.在mysql的multishop数据库中，依次执行下面sql文件中的sql语句，a-multi-shop\db\struct\20180512_multiShop.sql和a-multi-shop\db\data\20180512_multiShopData.sql。  
9.在eclipse中通过tomcat来运行这个web项目了。  
10.tomcat运行成功后，打开网页http://localhost:8080/multiShop ，即可到网站首页，若想到后台页面，可点右上角的商家中心。  

用途：  
1.此项目可以用来二次开发成真正可用的商业平台，担心高并发量？担心数据量？只需要在tomcat这一层面上加上nginx代理和在mysql这一层面上实现分布式数据即可。  
2.此项目也可以用在大学生们的毕业论文哦，只需将此项目拷贝下来，按照上面的步骤部署后，一个多商铺平台网站即可在您的计算机上跑起来了。  

若此项目对您有所帮助，请捐赠下我们呗！谢谢各位支持哈！  

