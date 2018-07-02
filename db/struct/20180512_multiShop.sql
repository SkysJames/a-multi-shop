/*
    系统参数表
*/
DROP TABLE IF EXISTS sys_parameter;
create table sys_parameter
(
    NAME            varchar(200) primary key comment '参数名（主键）',
    TYPE            varchar(100) not null comment '参数类型：system - 系统参数',
    VALUE           varchar(4000) comment '参数值',
    VALUETYPE       integer default 0 not null comment '参数值的类型：0 - 字符串；1 - 数字；2 - Boolean类型',
    DESCRIPTION     varchar(4000) comment '参数说明',
    MUST            integer default 0 not null comment '是否必须：0 - 不必须（默认）；1 - 必须'
);



/*
    用户表
*/
DROP TABLE IF EXISTS tb_user;
create table tb_user
(
    ID              varchar(36) primary key comment '主键ID',
    NAME            varchar(100) not null comment '用户名称',               
    PASSWD          varchar(64) comment '用户密码',
    SEX             integer default 0 comment '性别，0-未知，1-男，2-女',
    BIRTHDATE       varchar(32) comment '出生日期',
    SHOP_ID         varchar(36) comment '店铺ID，system - 系统用户（如：管理员），为空则为普通用户',
    RIGHTS          varchar(2000) comment '用户权限，多个以,隔开',
    RIGHTGROUPS     varchar(2000) comment '用户角色，多个以,隔开',
    USER_STATUS     integer default 1 not null comment '用户状态：0 - 禁用；1 - 启用',
    LOGIN_STATUS    integer default 0 not null comment '登录状态：0 - 未登录；1 - 已登录',
    LOGIN_IP        varchar(40) comment '登录IP',
    LOGIN_TIME      datetime comment '最近一次登录时间',                               
    LOGOUT_TIME     datetime comment '最近一次退出登录时间',                               
    REMARK          varchar(2000) comment '备注',                         
    CREATE_TIME     datetime comment '创建时间',
    WOPEN_ID        varchar(36) comment '微信openId',
    QQ				varchar(36) comment 'qq号',
    WECHAT			varchar(36) comment '微信号',
    TELEPHONE		varchar(36) comment '手机号'
);


/*
    访客表
*/
DROP TABLE IF EXISTS tb_visitor;
create table tb_visitor
(
    ID              varchar(36) primary key comment '主键ID',     
    IP              varchar(40) not null comment '访问IP',
    SHOP_ID         varchar(36) comment '店铺ID，system - 访问系统',
    STATUS          integer default 1 not null comment '状态：0 - 禁止访问；1 - 可以访问',  
    REMARK          varchar(4000) comment '备注',
    VISITED_TIMES   integer default 1 not null comment '访问次数',
    CREATE_TIME     datetime comment '创建时间',
    VISITED_TIME    datetime comment '最近访问时间'
);
alter table tb_visitor add index idx_tb_visitor_ip_si(IP,SHOP_ID);
alter table tb_visitor add index idx_tb_visitor_vt_si(VISITED_TIME,SHOP_ID);


/*
    权限表
*/
DROP TABLE IF EXISTS tb_right;
create table tb_right
(
    ID              varchar(36) primary key comment '主键ID',
    NAME            varchar(200) not null comment '权限名称',               
    TYPE            varchar(100) comment '权限类型'
);



/*
    角色表
*/
DROP TABLE IF EXISTS tb_rightgroup;
create table tb_rightgroup
(
    ID              varchar(36) primary key comment '主键ID',
    NAME            varchar(200) not null comment '角色名称',               
    RIGHTS          varchar(4000) comment '角色权限，多个以,隔开'
);



/*
    操作日志表
*/
DROP TABLE IF EXISTS tb_oplog;
create table tb_oplog
(
    ID              varchar(36) primary key comment '主键ID',     
    USER_ID         varchar(36) comment '用户ID',             
    OP_TIME         datetime not null comment '操作时间',                   
    OP_TYPE         varchar(50) not null comment '操作类型',    
    OP_DETAIL       varchar(4000) comment '操作详细信息',
    OP_IP           varchar(40) not null comment '操作IP'
);
alter table tb_oplog add index idx_tb_oplog_ot(OP_TIME);


/*
    消息表
*/
DROP TABLE IF EXISTS tb_message;
create table tb_message
(
    ID              varchar(36) primary key comment '主键ID',     
    FROM_USER       varchar(36) comment '发送用户ID',
    TO_USER       	varchar(36) comment '接收用户ID',
    CONTENT			varchar(4000) comment '消息内容',
    SEND_TIME       datetime not null comment '发送时间',
    STATUS         	integer default 0 not null comment '状态：0 - 未发送；1 - 已发送未接收；2 - 已接收',
    HREF     		varchar(400) comment '对象链接'
);
alter table tb_message add index idx_tb_message_tu(TO_USER);


/*
    类型表
*/
DROP TABLE IF EXISTS tb_type;
create table tb_type
(
    ID              varchar(36) primary key comment '主键ID',
    NAME            varchar(100) not null comment '类型名称',
    SHOP_ID         varchar(36) comment '店铺ID，只有表名为tb_product时才有值',
    TABLE_NAME		varchar(36) comment '表名称，tb_shop - 店铺类型；tb_product - 商品类型',
    PARENT_ID       varchar(36) comment '父类型ID',
    SORT	        integer comment '排序'
);


/*
    店铺表
*/
DROP TABLE IF EXISTS tb_shop;
create table tb_shop
(
    ID              varchar(36) primary key comment '主键ID',
    NAME            varchar(300) not null comment '店铺名称',
    POPULARITY		integer default 0 comment '人气值',
    RECOMMEND       integer default 0 comment '是否上推荐栏，0 - 普通，1 - 推荐',
    SHOP_TYPE		varchar(100) comment '店铺类型',
    ADD_TIME        datetime comment '入驻时间，即通过验证的时间',
    OVER_TIME       datetime comment '到期时间',
    SERVICE			varchar(600) comment '客服用户ID，多个以,隔开',
    LOGO       		varchar(200) comment 'logo图片路径',
    PICTURE			varchar(2000) comment '相关轮播图片路径，多个以,隔开',
    PICTURE_HREF    varchar(2000) comment '相关轮播图片的链接，按顺序与图片对应，多个以,隔开',
    BRIEF           varchar(600) comment '店铺简介',
    DESCRIPTION     text comment '店铺描述',
    MARK			decimal(2,1) comment '店铺评分，最高5分，所有评价分的平均值',
    ADDRESS         varchar(2000) comment '店铺所在地址',
    LONGITUDE       double(9,6) comment '店铺所在地，经度',
    LATITUDE        double(9,6) comment '店铺所在地，纬度',
    STATUS			integer default 0 comment '状态：-1-实际不存在该店铺;0-禁用；1-申请待验证；2-启用',
    PHONE			varchar(600) comment '电话号码',
    WECHAT_PIC		varchar(200) comment '微信公众号二维码，多个以,隔开',
    REMARK         	varchar(2000) comment '备注'
);


/*
    评价表
*/
DROP TABLE IF EXISTS tb_evaluate;
create table tb_evaluate
(
    ID              varchar(36) primary key comment '主键ID',     
    USER_ID       	varchar(36) comment '评价用户ID',
    OBJ_ID       	varchar(36) comment '被评价ID',
    TABLE_NAME		varchar(36) comment '表名称，tb_shop - 店铺类型评价',
    MARK			decimal(2,1) comment '评分，最高5分',
    CONTENT			varchar(4000) comment '评价内容',
    CREATE_TIME     datetime not null comment '评价时间',
    PICTURE         varchar(2000) comment '相关图片路径，多个以,隔开',
    STATUS         	integer default 0 not null comment '状态：0 - 未发送；1 - 已发送未接收；2 - 已接收'
);
alter table tb_evaluate add index idx_tb_evaluate_ct_tn_oi_s_m(CREATE_TIME,TABLE_NAME,OBJ_ID,STATUS,MARK);


/*
    商品表
*/
DROP TABLE IF EXISTS tb_product;
create table tb_product
(
    ID              varchar(36) primary key comment '主键ID',
    SHOP_ID         varchar(36) comment '店铺ID',
    NAME            varchar(300) comment '商品名称',
    CLICK_COUNT		integer default 0 comment '点击量',                  
    PRO_TYPE        varchar(36) comment '商品类型',             
    BRIEF           varchar(600) comment '商品简介',
    DESCRIPTION     text comment '商品描述',
    PICTURE         varchar(2000) comment '相关图片路径，多个以,隔开', 
    PRICE			decimal(12,2) comment '价格',
    PRO_STOCK		integer default 1 comment '商品库存',
    CREATE_TIME     datetime comment '创建时间',                            
    CREATE_USER     varchar(36) comment '创建用户ID',               
    UPDATE_TIME     datetime comment '更新时间',                        
    UPDATE_USER     varchar(36) comment '更新用户ID',
    STATUS			integer default 2 comment '状态：0-禁用；1-下架；2-上架'
);
alter table tb_product add index idx_tb_product_cc_s_si_pt(CLICK_COUNT,STATUS,SHOP_ID,PRO_TYPE);


/*
    商品购物车表
*/
DROP TABLE IF EXISTS tb_cart;
create table tb_cart
(
    ID              varchar(36) primary key comment '主键ID',
    PRODUCT_ID      varchar(36) comment '商品ID',
    USER_ID         varchar(36) comment '用户ID',
    PRO_NUM			integer default 1 comment '商品购买数量',
    CREATE_TIME     datetime comment '创建时间',
    UPDATE_TIME     datetime comment '更新时间',
    STATUS          integer default 0 comment '状态：0-未提交；1-已提交未完成；2-已完成'
);
alter table tb_cart add index idx_tb_cart_ui_s(USER_ID,STATUS);



/*
    公告表
*/
DROP TABLE IF EXISTS tb_announce;
create table tb_announce
(
    ID              varchar(36) primary key comment '主键ID',     
    NAME            varchar(300) comment '公告名称',
    SHOP_ID			varchar(36) comment '店铺ID，system - 系统公告',
    CONTENT         text comment '公告内容',
    CREATE_TIME     datetime comment '创建时间',
    CREATE_USER     varchar(36) comment '创建用户ID',
    UPDATE_TIME     datetime comment '更新时间',
    UPDATE_USER     varchar(36) comment '更新用户ID',
    OVER_TIME		datetime comment '过期时间',
    STATUS 			integer default 1 comment '状态：0-禁用；1-启用'
);
alter table tb_announce add index idx_tb_announce_si(SHOP_ID);


/*
    历史浏览/收藏表
*/
DROP TABLE IF EXISTS tb_prohistory;
create table tb_prohistory
(
    ID              varchar(36) primary key comment '主键ID',
    TYPE            integer comment '类型，1-历史浏览类型；2-收藏类型',
    OBJ_ID          varchar(36) comment '对象ID',
    TABLE_NAME      varchar(36) comment '表名称，tb_shop - 店铺收藏；tb_product - 商品收藏/历史；tb_bbstopic - 帖子（主帖）收藏/历史',                
    USER_ID         varchar(36) comment '用户ID',    
    CREATE_TIME     datetime comment '创建时间',           
    UPDATE_TIME     datetime comment '更新时间',
    HREF     		varchar(400) comment '对象链接'
);
alter table tb_prohistory add index idx_tb_prohistory_tn_ui(TABLE_NAME,USER_ID);


/*
    举报表
*/
DROP TABLE IF EXISTS tb_report;
create table tb_report
(
    ID              varchar(36) primary key comment '主键ID',
    OBJ_ID          varchar(36) comment '举报对象ID',
    TABLE_NAME      varchar(36) comment '表名称',
    USER_ID         varchar(36) comment '用户ID',  
    CONTENT         text comment '举报内容',
    CREATE_TIME     datetime comment '创建时间',
    STATUS          integer default 0 not null comment '状态：0 - 未发送；1 - 已发送未接收；2 - 已接收'
);



/*
	论坛板块表
*/
DROP TABLE IF EXISTS tb_bbssection;
CREATE TABLE `tb_bbssection` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键ID',
  `NAME` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '版块名称',
  `BRIEF` varchar(600) COLLATE utf8_bin COMMENT '简介',
  `HEADPIC`     varchar(400) comment '头像图片路径',
  `CREATE_TIME` datetime COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


/*
	论坛帖子表
*/
DROP TABLE IF EXISTS tb_bbstopic;
CREATE TABLE `tb_bbstopic` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '帖子ID',
  `SECTION_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '版块ID，引用版块表的版块ID',
  `MASTERID` varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '发帖人的用户ID，引用用户表的用户ID',
  `TOPIC_TYPE` integer COLLATE utf8_bin COMMENT '帖子类型，1-主帖；2-回帖',
  `TOPIC_ID` varchar(32) COLLATE utf8_bin COMMENT '主帖ID，只有回帖需要',
  `TOPIC_MASTERID` varchar(32) COLLATE utf8_bin COMMENT '主帖用户ID，只有回帖需要',
  `TOPIC_NAME` varchar(200) COLLATE utf8_bin COMMENT '标题名称，只有主帖需要',
  `CONTENT` text COLLATE utf8_bin COMMENT '正文，必须大于6个字符',
  `REPLY_COUNT` int(16) DEFAULT '0' COMMENT '回复数',
  `CLICK_COUNT` int(16) DEFAULT '0' COMMENT '点击数',
  `LIKE_NUM` int(16) DEFAULT '0' COMMENT '点赞数',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '发帖时间',
  `STATUS`      integer default 0 comment '状态,主帖STATUS默认2：0 - 未发布；1 - 已发布未接收；2 - 已接收',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*论坛首页的热帖榜*/
alter table tb_bbstopic add index idx_tb_bbstopic_cc(CLICK_COUNT);
/*论坛板块过滤的帖子和最新帖子*/
alter table tb_bbstopic add index idx_tb_bbstopic_si_ct(SECTION_ID,CREATE_TIME);
/*1.论坛主帖中的回帖展示，顺序排序；2.个人信息的帖子列表，可倒序排序*/
alter table tb_bbstopic add index idx_tb_bbstopic_ti_ct_tt(CREATE_TIME,TOPIC_TYPE,TOPIC_ID);
/*论坛用户的主帖回复提醒*/
alter table tb_bbstopic add index idx_tb_bbstopic_ti_tt_ct(TOPIC_MASTERID,TOPIC_TYPE,CREATE_TIME);


/*
	论坛回帖评论表
*/
DROP TABLE IF EXISTS tb_bbscomment;
CREATE TABLE `tb_bbscomment` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '评论ID',
  `MASTERID` varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '评论人的用户ID，引用用户表的用户ID',
  `ASSISTANTID` varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '被评论人的用户ID，引用用户表的用户ID',
  `REPLY_ID` varchar(32) COLLATE utf8_bin COMMENT '回帖ID',
  `CONTENT` text COLLATE utf8_bin COMMENT '正文，必须大于6个字符',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '评论时间',
  `STATUS`      integer default 0 comment '状态：0 - 未发布；1 - 已发布未接收；2 - 已接收',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*回帖的评论展示，顺序排序*/
alter table tb_bbscomment add index idx_tb_bbscomment_ct_ri(CREATE_TIME,REPLY_ID);
/*回帖的评论回复提醒*/
alter table tb_bbscomment add index idx_tb_bbscomment_ct_si(ASSISTANTID,CREATE_TIME);



