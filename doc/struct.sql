
#商品类型:
CREATE DATABASE IF NOT EXISTS rep DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

use rep;

#商品类型
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
  name_id int not null comment '商品名称id',
  serial_number varchar(50) not null comment '商品编号',
  specifications varchar(50) not null comment '商品规格',
  company varchar(30) not null comment '厂商',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '商品表';

#出入库记录表
create table if not exists records
(
  id int not null auto_increment,
  item_id int not null comment '商品id',
  action_type int not null comment '1: 出库,2:入库',
  action_detail int not null comment '入库, 1. 原始库存入库; 2:补货; 3: 调货. 出库, 101: 手术; 102: 报废; 103: 遗失',
  action_desc varchar(300) comment '备注',
  src_or_dst varchar(30) comment '入库来源或者出库目的地',
  quantity int not null comment '数量,扩大10倍存储';
  `time` datetime not null COMMENT '时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '出入库记录表';
