drop database if exists library;
create database library;

use library;


-- user 用户隐私信息
-- id    电话      邮件   密码  微信id
-- user_id  phone_num email  pwd  openid
drop table if exists user;
create table user
(
    user_id   int primary key auto_increment comment 'id',
    phone_num varchar(20) unique comment '电话',
    email     varchar(200) unique comment '邮件',
    pwd       varchar(30) comment '密码',
    openid    varchar(100) unique comment '微信id'

);

-- user_info 用户信息
-- id      微信id     会话密钥    头像      城市   国家     性别     语言      昵称      真实姓名 年龄   城市     学生id    背景
-- user_id openid session_key avatar_url city country gender language nick_name  realName age province stu_id  background
drop table if exists user_info;
create table user_info
(
    user_id     int primary key comment 'id',
    openid      varchar(50) unique comment '微信id',
    session_key varchar(50) comment '会话密钥',
    avatar_url  varchar(500) comment '头像',
    city        varchar(60) comment '城市',
    province    varchar(60) comment '省份',
    country     varchar(60) comment '国家',
    gender      varchar(5) comment '性别',
    `language`  varchar(20) comment '语言',
    nick_name   varchar(50) comment '用户昵称',
    real_name   varchar(50) comment '正式姓名',
    age         smallint comment '年龄',
    stu_id      int comment '学生id',
    background  varchar(500) comment '背景'
);

-- user_class 用户类型 1表示管理 0 表示
# create table user_class
# (
#     id      varchar(40) comment 'class id',
#     uid     varchar(40) comment 'id',
#     u_class boolean comment '用户类型'
# );


