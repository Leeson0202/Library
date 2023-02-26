drop database if exists library;
create database library;

use library;


-- user 用户隐私信息
-- id    电话      邮件   密码  微信id
-- user_id  phone_num email  pwd  openid
create table user
(
    user_id       int primary key auto_increment comment 'id',
    phone_num varchar(20) unique comment '电话',
    email     varchar(200) unique comment '邮件',
    pwd       varchar(30) comment '密码',
    openid   varchar(100) unique comment '微信id'
);

-- user_info 用户信息
-- id     昵称     性别  年龄  头像     背景
-- uid nick_name gender age avatar background
create table user_info
(
    user_id        int primary key comment 'id',
    nick_name  varchar(50) comment '用户昵称',
    gender     boolean comment '性别',
    age        smallint comment '年龄',
    avatar     varchar(500) comment '头像',
    background varchar(500) comment '背景'
);

-- user_class 用户类型 1表示管理 0 表示
# create table user_class
# (
#     id      varchar(40) comment 'class id',
#     uid     varchar(40) comment 'id',
#     u_class boolean comment '用户类型'
# );


