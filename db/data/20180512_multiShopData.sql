/*初始化数据*/
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('system_name', 'system', '多商铺平台', 0, '系统名称', 1);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('system_icon', 'system', '', 0, '多商铺平台图标的url', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('system_logo', 'system', '', 0, '多商铺平台logo的url', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('system_picture', 'system', '', 0, '多商铺平台轮播图片的url，多个以,分隔', 0);

Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('company_name', 'system', '梦想蓝天网络科技', 0, '公司名称', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('company_address', 'system', '广东省广州市番禺经济开发区58号', 0, '公司地址', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('company_record', 'system', '琼ICP备12345678号', 0, '公司备案号', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('copy_right', 'system', '2018-2050', 0, '版权有效期', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('telephone', 'system', '13533273266', 0, '联系手机', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('phone', 'system', '0755-66889888', 0, '联系电话', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('email', 'system', 'admin@qq.com', 0, '邮箱地址', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('qq', 'system', '888877777', 0, 'qq号码', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('service_time', 'system', '9:30-18:30', 0, '服务时间', 0);

Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('forum_name', 'forum', '论坛名称', 0, '论坛名称', 1);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('forum_icon', 'forum', '', 0, '论坛图标的url', 0);
Insert into sys_parameter (NAME, TYPE, VALUE, VALUETYPE, DESCRIPTION, MUST)
 Values ('forum_logo', 'forum', '', 0, '论坛logo的url', 0);

Insert into tb_right (ID, NAME, TYPE)
 Values ('back_manage', '后台管理', 'back');
Insert into tb_right (ID, NAME, TYPE)
 Values ('system_manage', '系统管理', 'system');
Insert into tb_right (ID, NAME, TYPE)
 Values ('forum_manage', '论坛管理', 'system');
Insert into tb_right (ID, NAME, TYPE)
 Values ('message_manage', '消息管理', 'message');
Insert into tb_right (ID, NAME, TYPE)
 Values ('user_manage', '用户管理', 'user');
Insert into tb_right (ID, NAME, TYPE)
 Values ('visitor_manage', '访客管理', 'visitor');
Insert into tb_right (ID, NAME, TYPE)
 Values ('oplog_manage', '日志管理', 'oplog');

Insert into tb_right (ID, NAME, TYPE)
 Values ('shop_manage', '店铺管理', 'shop');
Insert into tb_right (ID, NAME, TYPE)
 Values ('shop_basic', '店铺信息', 'shop');
Insert into tb_right (ID, NAME, TYPE)
 Values ('shop_list', '店铺列表', 'shop');
Insert into tb_right (ID, NAME, TYPE)
 Values ('product_manage', '商品管理', 'shop');
Insert into tb_right (ID, NAME, TYPE)
 Values ('shop_announce', '店铺公告', 'shop');
Insert into tb_right (ID, NAME, TYPE)
 Values ('shop_type', '店铺分类', 'shop');
Insert into tb_right (ID, NAME, TYPE)
 Values ('product_type', '商品分类', 'shop');

Insert into tb_rightgroup (ID, NAME, RIGHTS)
 Values ('admin_rightgroup', '管理员角色', 'back_manage,system_manage,forum_manage,message_manage,user_manage,visitor_manage,oplog_manage,shop_manage,shop_list,product_manage,shop_announce,shop_type,product_type');
Insert into tb_rightgroup (ID, NAME, RIGHTS)
 Values ('shopkeeper_rightgroup', '店长角色', 'back_manage,message_manage,user_manage,shop_manage,shop_basic,product_manage,shop_announce,product_type');
Insert into tb_rightgroup (ID, NAME, RIGHTS)
 Values ('salesclerk_rightgroup', '店员角色', 'back_manage,message_manage,shop_manage,shop_basic,product_manage,shop_announce,product_type');

Insert into tb_shop (ID, NAME)
 Values ('system', '系统');
Insert into tb_user (ID, NAME, PASSWD, SHOP_ID, USER_STATUS, LOGIN_STATUS, CREATE_TIME, RIGHTGROUPS)
 Values ('admin', 'admin', '123456aB', 'system', 1, 0, sysdate(), 'admin_rightgroup');

commit;
