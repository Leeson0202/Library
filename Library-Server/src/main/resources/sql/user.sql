drop database if exists library;
create database library;

use library;


-- user 用户隐私信息
-- id    电话      邮件   密码  微信id
-- user_id  phone_num email  pwd  openid
drop table if exists user;
create table user
(
    user_id   varchar(100) primary key comment 'id',
    phone_num varchar(20) unique comment '电话',
    email     varchar(200) unique comment '邮件',
    pwd       varchar(30) comment '密码',
    openid    varchar(100) unique comment '微信id'

);

insert into user value ('12344321', '18523681435', 'asedrfa@163.com', null, null);

-- user_info 用户信息
-- id      微信id     会话密钥    头像      城市   国家     性别     语言      昵称      真实姓名 年龄   城市     学生id    背景
-- user_id openid session_key avatar_url city country gender language nick_name  realName age province stu_id  background
drop table if exists user_info;
create table user_info
(
    user_id     varchar(100) primary key comment 'id',
    openid      varchar(50) unique comment '微信id',
    session_key varchar(50) comment '会话密钥',
    avatar_url  varchar(500) comment '头像'    default 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
    city        varchar(60) comment '城市'     default '重庆',
    province    varchar(60) comment '省份'     default '重庆',
    country     varchar(60) comment '国家'     default '中国',
    gender      varchar(5) comment '性别',
    `language`  varchar(20) comment '语言',
    nick_name   varchar(50) comment '用户昵称' default '用户',
    real_name   varchar(50) comment '正式姓名',
    age         smallint comment '年龄',
    stu_id      int comment '学生id',
    background  varchar(500) comment '背景'    default 'http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1010/13/c6/5494373_5494373_1286955435968.jpg'
);

insert into user_info(user_id, nick_name, gender, age, avatar_url)
values ('12344321', 'Leeson', '男', 23,
        'https://img1.baidu.com/it/u=1047817807,648960205&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500');

-- user_class 用户类型 1表示管理 0 表示
# create table user_class
# (
#     id      varchar(40) comment 'class id',
#     uid     varchar(40) comment 'id',
#     u_class boolean comment '用户类型'
# );

-- 用户信息 - 使用记录
drop table if exists user_record;
create table user_record
(
    user_id    varchar(100) primary key comment 'userId',
    credit     smallint comment '信用'         default 100,
    max_time   smallint comment '单日最长时间' default 0,
    all_time   smallint comment '总时间'       default 0,
    day_rank   smallint comment '单日排名'     default 0,
    week_rank  smallint comment '周排名'       default 0,
    month_rank smallint comment '月排名'       default 0,
    all_rank   smallint comment '总排名'       default 0

);
insert into user_record(user_id)
values ('12344321');

-- 用户信用记录
drop table if exists user_credit;
create table user_credit
(
    id            varchar(100) primary key comment '信用记录id',
    user_id       varchar(100) comment 'userId',
    user_credit   smallint comment '信用条目',
    `description` varchar(200) comment '备注'
);


-- 用户的个人学习数据表
drop table if exists user_learned;
create table user_learned
(
    id           varchar(100) primary key comment '用户的单日记录',
    user_id      varchar(100) comment 'userId',
    -- 0点
    `date`       datetime comment '日期',
    `learn_time` smallint comment '今日的时长'
);

-- 用户-学校关系表
drop table if exists user_school;
create table user_school
(
    id        varchar(100) primary key comment '用户学校记录id',
    user_id   varchar(100) comment 'userId',
    school_id varchar(100) comment '学校id'
);

insert into user_school
values ('knadjcva', '12344321', 'dcajhbadhcavacda');





