
#数据库:
CREATE DATABASE IF NOT EXISTS rep DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

use rep;

#商品类型 不做页面,只写查询接口,数据手动导入
create table if not exists item_type
(
  id int not null auto_increment,
  `name` varchar(20) not null comment '商品类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '商品类型表';

#商品名称
create table if not exists item_name
(
  id int not null auto_increment,
  type_id int not null comment '商品类型id',
  `name` varchar(100) not null comment '商品名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '商品名称表';

#商品
create table if not exists item
(
  id int not null auto_increment,
  type_id int not null comment '商品类型id',
  name_id int not null comment '商品名称id',
  serial_number varchar(50) not null unique comment '商品编号,编号和规格是一一对应的',
  specifications varchar(50) not null comment '商品规格,没有规格的商品，写无',
  quantity_all int not null default 0 comment '商品入库总数,扩大10倍存储',
  quantity_use int not null default 0 comment '商品出库总数,扩大10倍存储',
  quantity_current int not null default 0 comment '商品当前剩余总数量,扩大10倍存储',
  company varchar(30) not null comment '厂商',
  price int comment '售价，单价',
  state int not null comment '状态0,表示已删除, 1表示正常状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '商品表';

#出入库记录表
create table if not exists records
(
  id int not null auto_increment,
  item_id int not null comment '商品id',
  item_name_id int not null comment '商品名称id',
  item_type_id int not null comment '商品类型id',
  action_type int not null comment '1: 出库,2:入库',
  action_detail int not null comment '入库, 1. 原始库存入库; 2:补货,来自平台; 3: 调货，其他渠道. 出库, 101: 手术; 102: 报废; 103: 遗失; 104: 出货给二级代理商; 105: 退货给平台',
  transactional_number varchar(30)  comment  '如果是入库，流水号必填',
  src_or_dst varchar(30) comment '入库来源或者出库目的地',
  quantity_before int not null comment '入库或出库前的数量',
  quantity int not null comment '数量,扩大10倍存储',
  quantity_after int not null comment '入库或出库后的数量',
  price int  comment '如果是出库 单价,可以由页面输入，未输入则采用商品表中的数据',
  price_put_in int comment '如果是出库 0： 单价来自商品表，1： 单价来自网页输入',
  all_price int  comment '如果是出库 总价，可以由页面输入，未输入则采用单价乘以数量',
  all_price_put_in int  comment '如果是出库 0： 总价来自计算，1： 总价来自网页输入',
  `time` datetime not null COMMENT '出入库时间,不填则默认为创建时间',
  img_url varchar(100) comment '相关记录图片URL',
  docter_name varchar(10) comment '如果是手术出货,医生姓名',
  gentai_name varchar(10) comment '如果是手术出货,跟台人姓名',
  patient_name varchar(10) comment '如果是手术出货,病人姓名',
  zhuyuan_no varchar(10) comment '如果是手术出货,住院号',
  state int not null DEFAULT 0 comment '状态0,表示已删除, 1表示正常状态',
  action_desc varchar(300) comment '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '出入库记录表';
