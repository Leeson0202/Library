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
    phone_num varchar(20) comment '电话',
    email     varchar(200) comment '邮件',
    pwd       varchar(30) comment '密码',
    openid    varchar(100) unique comment '微信id'

);

insert into user
values ('admin', '1', 'asedrfa@163.com', null, null),
       ('test', '1', 'asedrfa@icloud.com', null, null)
;

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
    real_name   varchar(50) comment '真实姓名',
    age         smallint comment '年龄',
    stu_id      int comment '学生id',
    background  varchar(500) comment '背景'    default 'http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1010/13/c6/5494373_5494373_1286955435968.jpg'
);

insert into user_info(user_id, nick_name, gender, age, avatar_url)
values ('admin', 'Leeson', '男', 23,
        'https://img1.baidu.com/it/u=1047817807,648960205&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500'),
       ('test', 'Leeson', '男', 23,
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
    begin_time   datetime comment '开始时间',
    end_time     datetime comment '结束时间',
    `learn_time` int comment '今日的时长'
);


-- 用户-学校关系表
drop table if exists user_school;
create table user_school
(
    id         varchar(100) primary key comment '用户学校记录id',
    user_id    varchar(100) comment 'userId',
    school_id  varchar(100) comment '学校id',
    management bool default false comment '是否管理'
);

insert into user_school
values ('knadjcva', 'admin', 'schoolId', true), # 管理员
       ('asdjsadv', 'test', 'schoolId', false); # 测试





use library;

-- 学校表
drop table if exists school;
create table school
(
    school_id  varchar(100) primary key comment 'id',
    `name`     varchar(40) comment '名称',
    descs      varchar(500) comment '简介',
    icon       varchar(500) comment '图标',
    background varchar(500) comment '背景'
);
insert into school value ('schoolId', '重庆邮电大学',
                          '重庆邮电大学（Chongqing University of Posts and Telecommunications）简称“重邮”，坐落于直辖市——重庆市，是中华人民共和国工业和信息化部与重庆市人民政府共建的教学研究型大学，入选国家“中西部高校基础能力建设工程”、国家“卓越工程师教育培养计划”，是国家“2011计划”核心协同高校、中国政府奖学金来华留学生接收院校、国家大学生文化素质教育基地、国家布点设立并重点建设的四所邮电高校之一，重庆市一流学科建设高校，CDIO工程教育联盟成员单位。',
                          'https://bkimg.cdn.bcebos.com/pic/3c6d55fbb2fb43163d37add525a4462309f7d371?x-bce-process=image/resize,m_lfit,w_536,limit_1',
                          '/img/schoolbg.jpeg');

-- 图书馆
drop table if exists library;
create table library
(
    library_id varchar(100) primary key comment '图书馆Id',
    school_id  varchar(100) comment '学校id',
    `name`     varchar(20) comment '图书馆名字',
    `descs`    varchar(300) comment '简介',
    background varchar(500) comment '背景',
    weekend    boolean comment '周末是否开放',
    begin_time time comment '每日开放开始时间',
    end_time   time comment '每日开放结束时间',
    tt         time comment '时段长度'
);
insert into library
values ('jdgchvauajkuvbh', 'schoolId', '数字图书馆', '新图书馆',
        'http://ehall.cqupt.edu.cn/new/portal/css/dark/millennium/images/bg/bg.jpg', true, '8:00:00', '22:00:00',
        '00:30:00'),
       ('jdgchvauajkavavvbh', 'schoolId', '老图书馆', '老图书馆',
        'https://ids.cqupt.edu.cn/authserver/cquptDzTheme/static/web/dzimages/bg1.jpg', true, '8:00:00', '22:00:00',
        '00:30:00');


-- 图书室
drop table if exists library_room;
create table library_room
(
    room_id    varchar(100) primary key comment '图书室id',
    library_id varchar(100) comment '图书馆Id',
    `name`     varchar(200) comment '图书室名字',
    `descs`    varchar(300) comment '图书室描述',
    seat_num   int comment '座位数量'
);
insert into library_room
values ('dsackacsvjakcaw1', 'jdgchvauajkavavvbh', '一楼电脑室', '一楼电脑室', 12),
       ('dsavasckacbjakcaw2', 'jdgchvauajkavavvbh', '二楼电脑室', '二楼电脑室', 12),
       ('dsackacbjakcaw3', 'jdgchvauajkuvbh', '一楼阅览室', '一楼阅览室', 12),
       ('dsackacbjakcdfw4', 'jdgchvauajkuvbh', '二楼阅览室', '二楼阅览室', 12);


-- 图书座位
drop table if exists library_seat;
create table library_seat
(
    seat_id   varchar(100) primary key comment '座位id',
    room_id   varchar(100) comment '图书室id',
    `name`    varchar(20) comment '座位名称',
    `repair`  boolean comment '维修状态',
    x         int comment 'x坐标',
    y         int comment 'y坐标',
    direction smallint comment '方向',
    width     int comment '宽',
    height    int comment '高'
);
insert into library_seat
values ('A1001', 'dsackacbjakcaw3', 'A1001', false, 1, 1, 3, 11, 11),
       ('A1002', 'dsackacbjakcaw3', 'A1002', false, 1, 2, 3, 11, 11),
       ('A1003', 'dsackacbjakcaw3', 'A1003', false, 3, 1, 1, 11, 11),
       ('A1004', 'dsackacbjakcaw3', 'A1004', false, 3, 2, 1, 11, 11),
       ('A1005', 'dsackacbjakcaw3', 'A1005', false, 4, 1, 3, 11, 11),
       ('A1006', 'dsackacbjakcaw3', 'A1006', false, 4, 2, 3, 11, 11),
       ('A1007', 'dsackacbjakcaw3', 'A1007', false, 6, 1, 1, 11, 11),
       ('A1008', 'dsackacbjakcaw3', 'A1008', false, 6, 2, 1, 11, 11),
       ('A1009', 'dsackacbjakcaw3', 'A1009', false, 7, 1, 3, 11, 11),
       ('A1010', 'dsackacbjakcaw3', 'A1010', false, 7, 2, 3, 11, 11),
       ('A1011', 'dsackacbjakcaw3', 'A1011', false, 9, 1, 1, 11, 11),
       ('A1012', 'dsackacbjakcaw3', 'A1012', false, 9, 2, 1, 11, 11),
       ('A1013', 'dsackacbjakcaw3', 'A1013', false, 10, 1, 3, 11, 11),
       ('A1014', 'dsackacbjakcaw3', 'A1014', false, 10, 2, 3, 11, 11),
       ('A1015', 'dsackacbjakcaw3', 'A1015', false, 12, 1, 1, 11, 11),
       ('A1016', 'dsackacbjakcaw3', 'A1016', false, 12, 2, 1, 11, 11),
       ('A1017', 'dsackacbjakcaw3', 'A1017', false, 13, 1, 3, 11, 11),
       ('A1018', 'dsackacbjakcaw3', 'A1018', false, 13, 2, 3, 11, 11),
       ('A1019', 'dsackacbjakcaw3', 'A1019', false, 15, 1, 1, 11, 11),
       ('A1020', 'dsackacbjakcaw3', 'A1020', false, 15, 2, 1, 11, 11),
       ('A1021', 'dsackacbjakcaw3', 'A1021', false, 16, 1, 3, 11, 11),
       ('A1022', 'dsackacbjakcaw3', 'A1022', false, 16, 2, 3, 11, 11),
       ('A1023', 'dsackacbjakcaw3', 'A1023', false, 18, 1, 1, 11, 11),
       ('A1024', 'dsackacbjakcaw3', 'A1024', false, 18, 2, 1, 11, 11),
       ('A1200', 'dsackacbjakcaw3', 'A1200', false, 19, 1, 3, 11, 11),
       ('A1201', 'dsackacbjakcaw3', 'A1201', false, 19, 2, 3, 11, 11),
       ('A1202', 'dsackacbjakcaw3', 'A1202', false, 21, 1, 1, 11, 11),
       ('A1203', 'dsackacbjakcaw3', 'A1203', false, 21, 2, 1, 11, 11),
       ('A1025', 'dsackacbjakcaw3', 'A1025', false, 1, 4, 3, 11, 11),
       ('A1026', 'dsackacbjakcaw3', 'A1026', false, 1, 5, 3, 11, 11),
       ('A1027', 'dsackacbjakcaw3', 'A1027', false, 3, 4, 1, 11, 11),
       ('A1028', 'dsackacbjakcaw3', 'A1028', false, 3, 5, 1, 11, 11),
       ('A1029', 'dsackacbjakcaw3', 'A1029', false, 4, 4, 3, 11, 11),
       ('A1030', 'dsackacbjakcaw3', 'A1030', false, 4, 5, 3, 11, 11),
       ('A1031', 'dsackacbjakcaw3', 'A1031', false, 6, 4, 1, 11, 11),
       ('A1032', 'dsackacbjakcaw3', 'A1032', false, 6, 5, 1, 11, 11),
       ('A1033', 'dsackacbjakcaw3', 'A1033', false, 7, 4, 3, 11, 11),
       ('A1034', 'dsackacbjakcaw3', 'A1134', false, 7, 5, 3, 11, 11),
       ('A1035', 'dsackacbjakcaw3', 'A1135', false, 9, 4, 1, 11, 11),
       ('A1036', 'dsackacbjakcaw3', 'A1136', false, 9, 5, 1, 11, 11),
       ('A1037', 'dsackacbjakcaw3', 'A109', false, 10, 4, 3, 11, 11),
       ('A1038', 'dsackacbjakcaw3', 'A110', false, 10, 5, 3, 11, 11),
       ('A1039', 'dsackacbjakcaw3', 'A111', false, 12, 4, 1, 11, 11),
       ('A1040', 'dsackacbjakcaw3', 'A112', false, 12, 5, 1, 11, 11),
       ('A1041', 'dsackacbjakcaw3', 'A109', false, 13, 4, 3, 11, 11),
       ('A1042', 'dsackacbjakcaw3', 'A110', false, 13, 5, 3, 11, 11),
       ('A1043', 'dsackacbjakcaw3', 'A111', false, 15, 4, 1, 11, 11),
       ('A1044', 'dsackacbjakcaw3', 'A112', false, 15, 5, 1, 11, 11),
       ('A1045', 'dsackacbjakcaw3', 'A109', false, 16, 4, 3, 11, 11),
       ('A1046', 'dsackacbjakcaw3', 'A110', false, 16, 5, 3, 11, 11),
       ('A1047', 'dsackacbjakcaw3', 'A111', false, 18, 4, 1, 11, 11),
       ('A1048', 'dsackacbjakcaw3', 'A112', false, 18, 5, 1, 11, 11),
       ('A1205', 'dsackacbjakcaw3', 'A109', false, 19, 4, 3, 11, 11),
       ('A1206', 'dsackacbjakcaw3', 'A110', false, 19, 5, 3, 11, 11),
       ('A1207', 'dsackacbjakcaw3', 'A111', false, 21, 4, 1, 11, 11),
       ('A1208', 'dsackacbjakcaw3', 'A112', false, 21, 5, 1, 11, 11),

       ('A1049', 'dsackacbjakcaw3', 'A101', false, 1, 7, 3, 11, 11),
       ('A1050', 'dsackacbjakcaw3', 'A102', false, 1, 8, 3, 11, 11),
       ('A1051', 'dsackacbjakcaw3', 'A103', false, 3, 7, 1, 11, 11),
       ('A1052', 'dsackacbjakcaw3', 'A104', false, 3, 8, 1, 11, 11),
       ('A1053', 'dsackacbjakcaw3', 'A105', false, 4, 7, 3, 11, 11),
       ('A1054', 'dsackacbjakcaw3', 'A106', false, 4, 8, 3, 11, 11),
       ('A1055', 'dsackacbjakcaw3', 'A107', false, 6, 7, 1, 11, 11),
       ('A1056', 'dsackacbjakcaw3', 'A108', false, 6, 8, 1, 11, 11),
       ('A1057', 'dsackacbjakcaw3', 'A109', false, 7, 7, 3, 11, 11),
       ('A1058', 'dsackacbjakcaw3', 'A110', false, 7, 8, 3, 11, 11),
       ('A1059', 'dsackacbjakcaw3', 'A111', false, 9, 7, 1, 11, 11),
       ('A1060', 'dsackacbjakcaw3', 'A112', false, 9, 8, 1, 11, 11),
       ('A1061', 'dsackacbjakcaw3', 'A109', false, 10, 7, 3, 11, 11),
       ('A1062', 'dsackacbjakcaw3', 'A110', false, 10, 8, 3, 11, 11),
       ('A1063', 'dsackacbjakcaw3', 'A111', false, 12, 7, 1, 11, 11),
       ('A1064', 'dsackacbjakcaw3', 'A112', false, 12, 8, 1, 11, 11),
       ('A1065', 'dsackacbjakcaw3', 'A109', false, 13, 7, 3, 11, 11),
       ('A1066', 'dsackacbjakcaw3', 'A110', false, 13, 8, 3, 11, 11),
       ('A1067', 'dsackacbjakcaw3', 'A111', false, 15, 7, 1, 11, 11),
       ('A1068', 'dsackacbjakcaw3', 'A112', false, 15, 8, 1, 11, 11),
       ('A1069', 'dsackacbjakcaw3', 'A109', false, 16, 7, 3, 11, 11),
       ('A1070', 'dsackacbjakcaw3', 'A110', false, 16, 8, 3, 11, 11),
       ('A1071', 'dsackacbjakcaw3', 'A111', false, 18, 7, 1, 11, 11),
       ('A1072', 'dsackacbjakcaw3', 'A112', false, 18, 8, 1, 11, 11),
       ('A1209', 'dsackacbjakcaw3', 'A109', false, 19, 7, 3, 11, 11),
       ('A1210', 'dsackacbjakcaw3', 'A110', false, 19, 8, 3, 11, 11),
       ('A1211', 'dsackacbjakcaw3', 'A111', false, 21, 7, 1, 11, 11),
       ('A1212', 'dsackacbjakcaw3', 'A112', false, 21, 8, 1, 11, 11),

       ('A1073', 'dsackacbjakcaw3', 'A101', false, 1, 10, 3, 11, 11),
       ('A1074', 'dsackacbjakcaw3', 'A102', false, 1, 11, 3, 11, 11),
       ('A1075', 'dsackacbjakcaw3', 'A103', false, 3, 10, 1, 11, 11),
       ('A1076', 'dsackacbjakcaw3', 'A104', false, 3, 11, 1, 11, 11),
       ('A1077', 'dsackacbjakcaw3', 'A105', false, 4, 10, 3, 11, 11),
       ('A1078', 'dsackacbjakcaw3', 'A106', false, 4, 11, 3, 11, 11),
       ('A1079', 'dsackacbjakcaw3', 'A107', false, 6, 10, 1, 11, 11),
       ('A1080', 'dsackacbjakcaw3', 'A108', false, 6, 11, 1, 11, 11),
       ('A1081', 'dsackacbjakcaw3', 'A109', false, 7, 10, 3, 11, 11),
       ('A1082', 'dsackacbjakcaw3', 'A110', false, 7, 11, 3, 11, 11),
       ('A1083', 'dsackacbjakcaw3', 'A111', false, 9, 10, 1, 11, 11),
       ('A1084', 'dsackacbjakcaw3', 'A112', false, 9, 11, 1, 11, 11),
       ('A1085', 'dsackacbjakcaw3', 'A109', false, 10, 10, 3, 11, 11),
       ('A1086', 'dsackacbjakcaw3', 'A110', false, 10, 11, 3, 11, 11),
       ('A1087', 'dsackacbjakcaw3', 'A111', false, 12, 10, 1, 11, 11),
       ('A1088', 'dsackacbjakcaw3', 'A112', false, 12, 11, 1, 11, 11),
       ('A1089', 'dsackacbjakcaw3', 'A109', false, 13, 10, 3, 11, 11),
       ('A1090', 'dsackacbjakcaw3', 'A110', false, 13, 11, 3, 11, 11),
       ('A1091', 'dsackacbjakcaw3', 'A111', false, 15, 10, 1, 11, 11),
       ('A1092', 'dsackacbjakcaw3', 'A112', false, 15, 11, 1, 11, 11),
       ('A1093', 'dsackacbjakcaw3', 'A109', false, 16, 10, 3, 11, 11),
       ('A1094', 'dsackacbjakcaw3', 'A110', false, 16, 11, 3, 11, 11),
       ('A1095', 'dsackacbjakcaw3', 'A111', false, 18, 10, 1, 11, 11),
       ('A1096', 'dsackacbjakcaw3', 'A112', false, 18, 11, 1, 11, 11),
       ('A1213', 'dsackacbjakcaw3', 'A109', false, 19, 10, 3, 11, 11),
       ('A1214', 'dsackacbjakcaw3', 'A110', false, 19, 11, 3, 11, 11),
       ('A1215', 'dsackacbjakcaw3', 'A111', false, 21, 10, 1, 11, 11),
       ('A1216', 'dsackacbjakcaw3', 'A112', false, 21, 11, 1, 11, 11),

       ('A1217', 'dsackacbjakcaw3', 'A109', false, 22, 1, 3, 11, 11),
       ('A1218', 'dsackacbjakcaw3', 'A110', false, 22, 2, 3, 11, 11),
       ('A1219', 'dsackacbjakcaw3', 'A111', false, 22, 4, 3, 11, 11),
       ('A1220', 'dsackacbjakcaw3', 'A112', false, 22, 5, 3, 11, 11),
       ('A1221', 'dsackacbjakcaw3', 'A109', false, 22, 7, 3, 11, 11),
       ('A1222', 'dsackacbjakcaw3', 'A110', false, 22, 8, 3, 11, 11),
       ('A1223', 'dsackacbjakcaw3', 'A111', false, 22, 10, 3, 11, 11),
       ('A1224', 'dsackacbjakcaw3', 'A112', false, 22, 11, 3, 11, 11),

       ('A1225', 'dsackacbjakcaw3', 'A109', false, 24, 1, 1, 11, 11),
       ('A1226', 'dsackacbjakcaw3', 'A110', false, 24, 2, 1, 11, 11),
       ('A1227', 'dsackacbjakcaw3', 'A111', false, 24, 4, 1, 11, 11),
       ('A1228', 'dsackacbjakcaw3', 'A112', false, 24, 5, 1, 11, 11),
       ('A1229', 'dsackacbjakcaw3', 'A109', false, 24, 7, 1, 11, 11),
       ('A1230', 'dsackacbjakcaw3', 'A110', false, 24, 8, 1, 11, 11),
       ('A1231', 'dsackacbjakcaw3', 'A111', false, 24, 10, 1, 11, 11),
       ('A1232', 'dsackacbjakcaw3', 'A112', false, 24, 11, 1, 11, 11);


-- 桌子
drop table if exists library_table;
create table library_table
(
    table_id  varchar(100) primary key comment '桌子id',
    room_id   varchar(100) comment '图书室id',
    `name`    varchar(20) comment '座位名称',
    x         int comment 'x坐标',
    y         int comment 'y坐标',
    direction smallint comment '方向',
    width     int comment '宽',
    height    int comment '高'
);
insert into library_table
values ('A01', 'dsackacbjakcaw3', 'A01', 2, 1, 0, 1, 2),
       ('A02', 'dsackacbjakcaw3', 'A02', 5, 1, 0, 1, 2),
       ('A03', 'dsackacbjakcaw3', 'A03', 8, 1, 0, 1, 2),
       ('A04', 'dsackacbjakcaw3', 'A04', 11, 1, 0, 1, 2),
       ('A05', 'dsackacbjakcaw3', 'A05', 14, 1, 0, 1, 2),
       ('A06', 'dsackacbjakcaw3', 'A06', 17, 1, 0, 1, 2),
       ('A25', 'dsackacbjakcaw3', 'A06', 20, 1, 0, 1, 2),

       ('A07', 'dsackacbjakcaw3', 'A07', 2, 4, 0, 1, 2),
       ('A08', 'dsackacbjakcaw3', 'A08', 5, 4, 0, 1, 2),
       ('A09', 'dsackacbjakcaw3', 'A09', 8, 4, 0, 1, 2),
       ('A10', 'dsackacbjakcaw3', 'A10', 11, 4, 0, 1, 2),
       ('A11', 'dsackacbjakcaw3', 'A11', 14, 4, 0, 1, 2),
       ('A12', 'dsackacbjakcaw3', 'A06', 17, 4, 0, 1, 2),
       ('A26', 'dsackacbjakcaw3', 'A06', 20, 4, 0, 1, 2),

       ('A13', 'dsackacbjakcaw3', 'A13', 2, 7, 0, 1, 2),
       ('A14', 'dsackacbjakcaw3', 'A14', 5, 7, 0, 1, 2),
       ('A15', 'dsackacbjakcaw3', 'A15', 8, 7, 0, 1, 2),
       ('A16', 'dsackacbjakcaw3', 'A16', 11, 7, 0, 1, 2),
       ('A17', 'dsackacbjakcaw3', 'A17', 14, 7, 0, 1, 2),
       ('A18', 'dsackacbjakcaw3', 'A06', 17, 7, 0, 1, 2),
       ('A27', 'dsackacbjakcaw3', 'A06', 20, 7, 0, 1, 2),

       ('A19', 'dsackacbjakcaw3', 'A13', 2, 10, 0, 1, 2),
       ('A20', 'dsackacbjakcaw3', 'A14', 5, 10, 0, 1, 2),
       ('A21', 'dsackacbjakcaw3', 'A15', 8, 10, 0, 1, 2),
       ('A22', 'dsackacbjakcaw3', 'A16', 11, 10, 0, 1, 2),
       ('A23', 'dsackacbjakcaw3', 'A17', 14, 10, 0, 1, 2),
       ('A24', 'dsackacbjakcaw3', 'A18', 17, 10, 0, 1, 2),
       ('A28', 'dsackacbjakcaw3', 'A18', 20, 10, 0, 1, 2),

       ('A29', 'dsackacbjakcaw3', 'A16', 23, 1, 0, 1, 2),
       ('A30', 'dsackacbjakcaw3', 'A17', 23, 4, 0, 1, 2),
       ('A31', 'dsackacbjakcaw3', 'A18', 23, 7, 0, 1, 2),
       ('A32', 'dsackacbjakcaw3', 'A18', 23, 10, 0, 1, 2);

-- 图书室 暂停开放规则
drop table if exists library_room_stop;
create table library_room_stop
(
    id        varchar(100) primary key comment 'id',
    room_id   varchar(100) comment '图书室id',
    stop_date date comment '暂停开放日期 0000-00-00'
);

-- 学校图书室设备
drop table if exists library_equipment;
create table library_equipment
(
    equipment_id varchar(100) primary key comment '设备id',
    school_id    varchar(100) comment '学校id',
    room_id      varchar(100) comment '图书室id'
);

-- 通知
drop table if exists school_notification;
create table school_notification
(
    notification_id varchar(50) primary key comment 'id',
    school_id       varchar(100) comment '学校id',
    user_id         varchar(100) comment '用户id',
    `title`         varchar(50) comment '标题',
    `context`       varchar(2000) comment '内容',
    `view`          int comment '浏览量',
    `date`          datetime comment '发布日期'
);
insert into school_notification
values ('kdjabcdakabedcvd', 'schoolId', 'admin', '关于寒假闭馆通知',
        '劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。', 10, '20220910100000');

-- 预约规则
drop table if exists school_rule;
create table school_rule
(
    school_id varchar(100) primary key comment '学校id',
    `context` text comment '内容'
);
insert into school_rule
values ('schoolId', '1、每天7：00开始，读者可以预约当日或次日的座位，预约成功后读者可以使用该座位至当日闭馆。预约系统登录用户名为学号/工号，密码为公共数据库密码。
2、读者可通过以下三种方式预约并使用当日座位
(1)8：00之前预约当日座位，并在8：30之前通过门禁闸机刷卡入馆，系统即可自动完成签到。
(2)当日其他开放时段，在馆外通过网络预约当日座位，并在预约成功后30分钟内前往图书馆通过门禁闸机刷卡入馆，系统即可自动完成签到。
(3)通过图书馆门禁闸机刷卡入馆，再通过网络或现场预约机预约当日座位，预约成功后即自动完成签到。
3、预约次日座位的读者，需在次日8：30前通过门禁闸机刷卡入馆(系统自动完成签到)。
4、取消预约：读者预约次日座位后，可在次日7：00之前取消预约(不限次数)。预约当日座位后，限每天取消1次。具体操作：点击我的中心--我的预约--取消预约。
5、“未签到”违规：预约成功后未在第2、第3条规定时间内签到，也未在第4条规定时间内取消预约的读者将被记“未签到”违规1次。
6、临时离开：在选座机上刷卡或从网页或从微信端选择“临时离开”，座位将保留60分钟(中午10:30-13:00和下午16:30-19:00期间离开分别保留120分钟和90分钟，从离开时间起算)。已选择“临时离开”的读者在保留时间内返回时，通过门禁闸机入馆即自动完成签到。若选择了“临时离开”，但实际又没离馆，也需在规定时间内在选座机上刷卡或通过网页端操作完成签到。读者未在保留时间内返回签到，系统将自动释放座位供他人选用，并记“临时离开超时”违规1次。
7、离馆：读者每次离馆(含临时离馆)均需刷卡从门禁闸机出馆，否则将被记“离开未刷卡”违规。读者未选择“临时离开”，通过门禁闸机出馆时，座位将自动释放。
8、没有公共数据库账号的读者(校友等)，不能通过网上提前预约座位，请入馆后在现场选座机上选择预约座位。
9、请读者自觉维护馆内秩序，勿随意移动桌椅，个人物品请自行妥善保管。每天闭馆时，请带走所有个人物品(长期存包箱内物品除外)。如被清理，产生的损失由读者本人自负。');

-- 反馈信息
drop table if exists library_feedback;
create table library_feedback
(
    feedback_id varchar(50) primary key comment 'id',
    school_id   varchar(100) comment '学校id',
    user_id     varchar(100) comment '用户id',
    `title`     varchar(50) comment '标题',
    `context`   varchar(2000) comment '内容',
    tag         int comment '处理情况',
    `date`      datetime comment '发布日期'
);use library;

-- 重邮用户表
drop table if exists cquptInfo;
create table cquptInfo
(
    user_id            varchar(100) primary key comment 'id',
    `role`             varchar(50) comment 'role',
    `name`             varchar(50) comment '姓名',
    cqupt_id           varchar(50) unique comment 'cqupt_id',
    student_id         varchar(50) comment '学生号',
    grade              varchar(50) comment '年级',
    `classs`           varchar(50) comment '班级',
    unit_name          varchar(50) comment '学院',
    profession_name    varchar(50) comment '专业',
    gender             varchar(50) comment '性别',
    counselor_name     varchar(50) comment '辅导员',
    counselor_cqupt_id varchar(50) comment '辅导员cqupt_id'
);use library;


-- 用户预约详细表
drop table if exists receive_item;
create table receive_item
(
    receive_id   varchar(100) primary key comment 'id',
    user_id      varchar(100) comment '用户id',
    library_id   varchar(100) comment '图书馆id',
    room_id      varchar(100) comment '图书室id',
    seat_id      varchar(100) comment '座位id',
    -- 多少号几点
    receive_date date comment '预约日期',
    time_idx     smallint comment '预约时间',
    `time`       datetime comment '创建时间'

);

-- 用户在线
drop table if exists user_online;
create table user_online
(
    user_id  varchar(100) primary key comment 'id',
    `online` int comment '在座状态',
    `date`   datetime comment '开始就坐时间'
);

-- 快速预约_订单 (具体体位置 时间) 面向考研
drop table if exists receive_fast;
create table receive_fast
(
    user_id    varchar(100) primary key comment 'id',
    school_id  varchar(100) comment '学校id',
    library_id varchar(100) comment '图书馆Id',
    room_id    varchar(100) comment '图书室id',
    seat_id    varchar(100) comment '座位id',
    `open`     boolean comment '是否打开'
);


insert into receive_fast
values ('admin', 'schoolId', 'jdgchvauajkuvbh', 'dsackacbjakcaw3', 'A1001', 1);


drop table  if exists library_seat;
create table library_seat
(
    seat_id   varchar(100) not null comment '座位id'
        primary key,
    room_id   varchar(100) null comment '图书室id',
    name      varchar(20)  null comment '座位名称',
    repair    tinyint(1)   null comment '维修状态',
    x         int          null comment 'x坐标',
    y         int          null comment 'y坐标',
    direction smallint     null comment '方向',
    width     int          null comment '宽',
    height    int          null comment '高'
);

INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('0akn9ykp8mdddve7wog81', 'dsackacsvjakcaw1', '0qiq17z1sms', 0, 12, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('17f4r84y8q9', 'dsackacbjakcaw3', 'ue4na9jk6to', 0, 14, 12, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('1xf2tqi8kinizkfv5hweu9', 'dsackacbjakcdfw4', 'rt4mx548z6d', 0, 2, 2, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('2623djj8c2zdyvclugx8qc', 'dsackacsvjakcaw1', '5lp3vwhw8r8', 0, 9, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('3k9wwycvuy7fyuwajakiys', 'dsackacsvjakcaw1', 'zs6gophq5l', 0, 11, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('3ldfv7xoitg1csczsfvbz3', 'dsackacsvjakcaw1', 'ypwnls2xvqc', 0, 7, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('3ridr0z3z6opccrxa653er', 'dsackacsvjakcaw1', 'fwlecor6wkf', 0, 3, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('40d8kjckt2h0b36e2npsujo', 'dsackacbjakcdfw4', 'u44y9rnvqb', 0, 0, 1, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('44gvbn9t85eqbn6zex4nlr', 'dsackacbjakcdfw4', 'dw42dsamcvm', 0, 4, 1, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('49lmomv82vx', 'dsackacbjakcaw3', 'xnd4inepiz', 0, 14, 13, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('5plu8p4hxx8qlkzx5cgw0r', 'dsackacsvjakcaw1', '3iphx891y6', 0, 3, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('63zzr2lwagxq2g40v972ig', 'dsackacbjakcdfw4', 'd7qtdebyt68', 0, 2, 3, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('6blel019tpm9or7agaeprs', 'dsackacsvjakcaw1', 'xfxmbu81imq', 0, 4, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('6f13z12wma6dkkgqbpfd2o', 'dsackacsvjakcaw1', 'c1kjdqetlh6', 0, 10, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('6hj1wxa4ftctzogbt3iqmo', 'dsackacbjakcdfw4', 'alselxu01qn', 0, 4, 0, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('6jdfk5c3r7ez5aho984dal', 'dsackacsvjakcaw1', 'hc3z0j8yji', 0, 4, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('6zh02j091822g01boa4ydw', 'dsackacsvjakcaw1', 'u89jc8rrqel', 0, 5, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('754kgrs9qdozjahr7ixygq', 'dsackacbjakcdfw4', 'va0gjcggda', 0, 6, 0, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('7783s9drl2x', 'dsackacbjakcaw3', '7lezi1ljr79', 0, 11, 12, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('77za5wemjx4ap72f1q4e6r', 'dsackacsvjakcaw1', '1h241rfylug', 0, 5, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('7pfy8qhfi1dvro1qee80mk', 'dsackacsvjakcaw1', 'dzun8gbr41', 0, 1, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('7xt8bxzt18hr18wbkywwbd', 'dsackacsvjakcaw1', '9nai6z75a4h', 0, 1, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('81tpqm5x8r5h06h67ndki', 'dsackacsvjakcaw1', 'fnvg9ljq9s7', 0, 10, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('8j67voxxvx8t0m0rc2ryqe', 'dsackacbjakcdfw4', 'ikbqsyhikw', 0, 6, 2, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('8pcym8vs6qatceo83nt9i', 'dsackacsvjakcaw1', 'fbpy60tfkeq', 0, 8, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('8pzt7xyytcbrdcsbxptmq', 'dsackacsvjakcaw1', 'z48kz6f0ive', 0, 0, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('8sow51kodll6t25len3cvx', 'dsackacbjakcdfw4', 'a7p8c0hu34k', 0, 0, 2, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('8xzgk48wyyf9bdla0q0ztk', 'dsackacbjakcdfw4', 'jg4v78pfbej', 0, 0, 3, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('95t7ha66d2k', 'dsackacbjakcaw3', 'zmb6oiuh6fk', 0, 15, 13, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('9zk5e1x3xqj', 'dsackacbjakcaw3', '3bohomrw093', 0, 21, 12, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1001', 'dsackacbjakcaw3', 'A1001', 0, 0, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1002', 'dsackacbjakcaw3', 'A1002', 0, 0, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1003', 'dsackacbjakcaw3', 'A1003', 0, 2, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1004', 'dsackacbjakcaw3', 'A1004', 0, 2, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1005', 'dsackacbjakcaw3', 'A1005', 0, 3, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1006', 'dsackacbjakcaw3', 'A1006', 0, 3, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1007', 'dsackacbjakcaw3', 'A1007', 0, 5, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1008', 'dsackacbjakcaw3', 'A1008', 0, 5, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1009', 'dsackacbjakcaw3', 'A1009', 0, 6, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1010', 'dsackacbjakcaw3', 'A1010', 0, 6, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1011', 'dsackacbjakcaw3', 'A1011', 0, 8, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1012', 'dsackacbjakcaw3', 'A1012', 0, 8, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1013', 'dsackacbjakcaw3', 'A1013', 0, 9, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1014', 'dsackacbjakcaw3', 'A1014', 0, 9, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1015', 'dsackacbjakcaw3', 'A1015', 0, 11, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1016', 'dsackacbjakcaw3', 'A1016', 0, 11, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1017', 'dsackacbjakcaw3', 'A1017', 0, 12, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1018', 'dsackacbjakcaw3', 'A1018', 0, 12, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1019', 'dsackacbjakcaw3', 'A1019', 0, 14, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1020', 'dsackacbjakcaw3', 'A1020', 0, 14, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1021', 'dsackacbjakcaw3', 'A1021', 0, 15, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1022', 'dsackacbjakcaw3', 'A1022', 0, 15, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1023', 'dsackacbjakcaw3', 'A1023', 0, 17, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1024', 'dsackacbjakcaw3', 'A1024', 0, 17, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1025', 'dsackacbjakcaw3', 'A1025', 0, 0, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1026', 'dsackacbjakcaw3', 'A1026', 0, 0, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1027', 'dsackacbjakcaw3', 'A1027', 0, 2, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1028', 'dsackacbjakcaw3', 'A1028', 0, 2, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1029', 'dsackacbjakcaw3', 'A1029', 0, 3, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1030', 'dsackacbjakcaw3', 'A1030', 0, 3, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1031', 'dsackacbjakcaw3', 'A1031', 0, 5, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1032', 'dsackacbjakcaw3', 'A1032', 0, 5, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1033', 'dsackacbjakcaw3', 'A1033', 0, 6, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1034', 'dsackacbjakcaw3', 'A1134', 0, 6, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1035', 'dsackacbjakcaw3', 'A1135', 0, 8, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1036', 'dsackacbjakcaw3', 'A1136', 0, 8, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1037', 'dsackacbjakcaw3', 'A109', 0, 9, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1038', 'dsackacbjakcaw3', 'A110', 0, 9, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1039', 'dsackacbjakcaw3', 'A111', 0, 11, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1040', 'dsackacbjakcaw3', 'A112', 0, 11, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1041', 'dsackacbjakcaw3', 'A109', 0, 12, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1042', 'dsackacbjakcaw3', 'A110', 0, 12, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1043', 'dsackacbjakcaw3', 'A111', 0, 14, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1044', 'dsackacbjakcaw3', 'A112', 0, 14, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1045', 'dsackacbjakcaw3', 'A109', 0, 15, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1046', 'dsackacbjakcaw3', 'A110', 0, 15, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1047', 'dsackacbjakcaw3', 'A111', 0, 17, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1048', 'dsackacbjakcaw3', 'A112', 0, 17, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1049', 'dsackacbjakcaw3', 'A101', 0, 0, 7, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1050', 'dsackacbjakcaw3', 'A102', 0, 0, 6, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1051', 'dsackacbjakcaw3', 'A103', 0, 2, 7, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1052', 'dsackacbjakcaw3', 'A104', 0, 2, 6, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1053', 'dsackacbjakcaw3', 'A105', 0, 3, 7, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1054', 'dsackacbjakcaw3', 'A106', 0, 3, 6, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1055', 'dsackacbjakcaw3', 'A107', 0, 5, 7, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1056', 'dsackacbjakcaw3', 'A108', 0, 5, 6, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1057', 'dsackacbjakcaw3', 'A109', 0, 6, 7, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1058', 'dsackacbjakcaw3', 'A110', 0, 6, 6, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1059', 'dsackacbjakcaw3', 'A111', 0, 8, 7, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1060', 'dsackacbjakcaw3', 'A112', 0, 8, 6, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1061', 'dsackacbjakcaw3', 'A109', 0, 9, 7, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1062', 'dsackacbjakcaw3', 'A110', 0, 9, 6, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1063', 'dsackacbjakcaw3', 'A111', 0, 11, 7, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1064', 'dsackacbjakcaw3', 'A112', 0, 11, 6, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1065', 'dsackacbjakcaw3', 'A109', 0, 12, 7, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1066', 'dsackacbjakcaw3', 'A110', 0, 12, 6, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1067', 'dsackacbjakcaw3', 'A111', 0, 14, 7, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1068', 'dsackacbjakcaw3', 'A112', 0, 14, 6, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1069', 'dsackacbjakcaw3', 'A109', 0, 15, 7, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1070', 'dsackacbjakcaw3', 'A110', 0, 15, 6, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1071', 'dsackacbjakcaw3', 'A111', 0, 17, 7, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1072', 'dsackacbjakcaw3', 'A112', 0, 17, 6, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1073', 'dsackacbjakcaw3', 'A101', 0, 0, 10, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1074', 'dsackacbjakcaw3', 'A102', 0, 0, 9, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1075', 'dsackacbjakcaw3', 'A103', 0, 2, 10, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1076', 'dsackacbjakcaw3', 'A104', 0, 2, 9, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1077', 'dsackacbjakcaw3', 'A105', 0, 3, 10, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1078', 'dsackacbjakcaw3', 'A106', 0, 3, 9, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1079', 'dsackacbjakcaw3', 'A107', 0, 5, 10, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1080', 'dsackacbjakcaw3', 'A108', 0, 5, 9, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1081', 'dsackacbjakcaw3', 'A109', 0, 6, 10, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1082', 'dsackacbjakcaw3', 'A110', 0, 6, 9, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1083', 'dsackacbjakcaw3', 'A111', 0, 8, 10, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1084', 'dsackacbjakcaw3', 'A112', 0, 8, 9, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1085', 'dsackacbjakcaw3', 'A109', 0, 9, 10, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1086', 'dsackacbjakcaw3', 'A110', 0, 9, 9, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1087', 'dsackacbjakcaw3', 'A111', 0, 11, 10, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1088', 'dsackacbjakcaw3', 'A112', 0, 11, 9, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1089', 'dsackacbjakcaw3', 'A109', 0, 12, 10, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1090', 'dsackacbjakcaw3', 'A110', 0, 12, 9, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1091', 'dsackacbjakcaw3', 'A111', 0, 14, 10, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1092', 'dsackacbjakcaw3', 'A112', 0, 14, 9, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1093', 'dsackacbjakcaw3', 'A109', 0, 15, 10, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1094', 'dsackacbjakcaw3', 'A110', 0, 15, 9, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1095', 'dsackacbjakcaw3', 'A111', 0, 17, 10, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1096', 'dsackacbjakcaw3', 'A112', 0, 17, 9, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1200', 'dsackacbjakcaw3', 'A1200', 0, 18, 6, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1201', 'dsackacbjakcaw3', 'A1201', 0, 18, 7, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1202', 'dsackacbjakcaw3', 'A1202', 0, 20, 6, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1203', 'dsackacbjakcaw3', 'A1203', 0, 20, 7, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1205', 'dsackacbjakcaw3', 'A109', 0, 18, 10, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1206', 'dsackacbjakcaw3', 'A110', 0, 18, 9, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1207', 'dsackacbjakcaw3', 'A111', 0, 20, 10, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1208', 'dsackacbjakcaw3', 'A112', 0, 20, 9, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1209', 'dsackacbjakcaw3', 'A109', 0, 3, 13, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1210', 'dsackacbjakcaw3', 'A110', 0, 3, 12, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1211', 'dsackacbjakcaw3', 'A111', 0, 5, 13, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1212', 'dsackacbjakcaw3', 'A112', 0, 5, 12, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1213', 'dsackacbjakcaw3', 'A109', 0, 0, 13, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1214', 'dsackacbjakcaw3', 'A110', 0, 0, 12, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1215', 'dsackacbjakcaw3', 'A111', 0, 2, 13, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1216', 'dsackacbjakcaw3', 'A112', 0, 2, 12, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1217', 'dsackacbjakcaw3', 'A109', 0, 21, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1218', 'dsackacbjakcaw3', 'A110', 0, 18, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1219', 'dsackacbjakcaw3', 'A111', 0, 21, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1220', 'dsackacbjakcaw3', 'A112', 0, 21, 4, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1221', 'dsackacbjakcaw3', 'A109', 0, 18, 0, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1222', 'dsackacbjakcaw3', 'A110', 0, 21, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1223', 'dsackacbjakcaw3', 'A111', 0, 18, 3, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1224', 'dsackacbjakcaw3', 'A112', 0, 18, 1, 3, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1225', 'dsackacbjakcaw3', 'A109', 0, 20, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1226', 'dsackacbjakcaw3', 'A110', 0, 20, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1227', 'dsackacbjakcaw3', 'A111', 0, 23, 4, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1228', 'dsackacbjakcaw3', 'A112', 0, 23, 3, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1229', 'dsackacbjakcaw3', 'A109', 0, 20, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1230', 'dsackacbjakcaw3', 'A110', 0, 20, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1231', 'dsackacbjakcaw3', 'A111', 0, 23, 0, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('A1232', 'dsackacbjakcaw3', 'A112', 0, 23, 1, 1, 11, 11);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('absfghll8zmxckx8b98xs', 'dsackacbjakcdfw4', 'ebksy3frw3', 0, 6, 3, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('aq5uo86dagk', 'dsackacbjakcaw3', 'igeaf4pe1x', 0, 20, 13, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('az42fyqvdh7ryf0yfb3tgm', 'dsackacbjakcdfw4', 'wc3w3fcdphp', 0, 2, 1, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('b9ax6pi6wehp5236qljmp7', 'dsackacsvjakcaw1', 'lyf52uun7tm', 0, 5, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('bzagjxss0graf2670zkueq', 'dsackacsvjakcaw1', 'co7jiaytond', 0, 12, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('bzccltml5u', 'dsackacbjakcaw3', '6xzktzftum7', 0, 11, 13, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('c03foqmbq6t8g2gcf3oq', 'dsackacsvjakcaw1', '3fox3u6d56b', 0, 2, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('c6awt9bvqff6hr0shasojk', 'dsackacsvjakcaw1', '4yvur3pvv3h', 0, 8, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ceh8srm0ctq', 'dsackacbjakcaw3', 'g4scfpywbxr', 0, 9, 13, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('cp7vodfwrlzxsnx4w6xr', 'dsackacbjakcdfw4', '9p2vfotchnn', 0, 0, 0, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('cq19u6d0wl', 'dsackacbjakcaw3', 'glbhs240owj', 0, 21, 7, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('cr4ly8v0bna7f75y69u4p3', 'dsackacsvjakcaw1', 'tjzleqzodlh', 0, 4, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('crndojo2lar614a8kbw62', 'dsackacsvjakcaw1', '3dp3yx6tbvy', 0, 9, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('cwtwuaqzws7s0ik8qj5cie', 'dsackacsvjakcaw1', 'skm481jp1ba', 0, 0, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('d6v5irx2anw3rv69pmak92', 'dsackacsvjakcaw1', 'm9v02mt5guk', 0, 4, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('d79ugbesn2u2t29fl598qd', 'dsackacsvjakcaw1', 'r1vk1y9pqy8', 0, 2, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('e39ap7ybot', 'dsackacbjakcaw3', 'l5d4oy8l8e', 0, 17, 12, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('e6pjfomui0r', 'dsackacbjakcaw3', 's7u8qg2qd6n', 0, 15, 12, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('em6pfv1vhasbrvwd6eo8lf', 'dsackacsvjakcaw1', 'b5td1hqbku', 0, 11, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('geoq0pjrz4c906t4z9jvo', 'dsackacsvjakcaw1', 'hxugb5v3kiu', 0, 9, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('glps0ulzej4', 'dsackacbjakcaw3', 'yu2fos3mbed', 0, 23, 7, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('hbyr45xktlw74s1ldpjfqq', 'dsackacsvjakcaw1', 'tz2yg40zzs', 0, 8, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('hf7tam2a06n', 'dsackacbjakcaw3', '0hmoq4ole80i', 0, 12, 13, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('hqaehfipelnybzbnqxx5dd', 'dsackacsvjakcaw1', 'hd9ejtzk8ua', 0, 8, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('hs8zaj0e5kr', 'dsackacbjakcaw3', '6n10m7qfssl', 0, 6, 12, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('i17ks3ckuw8pnf2gtlnn5', 'dsackacsvjakcaw1', 'u9zjcjirau', 0, 8, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('i46cxyzy2fykjfrtwjdz', 'dsackacsvjakcaw1', '9p2foiqygot', 0, 6, 11, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ilny6fwj9lstyo5mdmwue', 'dsackacsvjakcaw1', 'on9lxwjch3l', 0, 9, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ipo5uslaoospli8ksz830s', 'dsackacbjakcdfw4', 'woqnn9oknvj', 0, 6, 1, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('j8zptv91llerszhemla3y8', 'dsackacbjakcdfw4', 'lb7b7op72eo', 0, 4, 2, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('jfoo99n8y24gfh9zuh0ql', 'dsackacsvjakcaw1', 'tvruqnqli78', 0, 11, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('jkbc194tuc55ystq576cm', 'dsackacsvjakcaw1', 'zh1d52kyyia', 0, 8, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ju2t1qqyihp', 'dsackacbjakcaw3', '3lx4f8izznm', 0, 23, 10, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('juh94rac60nvo75pxh6ff', 'dsackacsvjakcaw1', 'ywm95en4mgj', 0, 10, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('kb2taxa7sapyyilgn0o45b', 'dsackacsvjakcaw1', 'ad2sd3kjlo', 0, 0, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('kdom068atyh5p6ph32oavh', 'dsackacsvjakcaw1', 'nmfycapphun', 0, 7, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('lc2dnaf1wnp', 'dsackacbjakcaw3', 'xchg4d113lh', 0, 17, 13, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('lga9h3qybgb06v6fxa57zea', 'dsackacsvjakcaw1', '3m5lyasderl', 0, 7, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ltxfjcurj3e', 'dsackacbjakcaw3', 'qrnxmqb8ey', 0, 12, 12, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('mme0t3zi8q', 'dsackacbjakcaw3', 'wqt3gpf4mz', 0, 21, 13, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('mqllremecaj0cg28ez04hmg', 'dsackacbjakcdfw4', '2flg6iagull', 0, 4, 3, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('mujcu5iuq3npo0eqlkfol', 'dsackacsvjakcaw1', 'tzf3zrhylb', 0, 5, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ncqnv9fis8z23pqyoztja', 'dsackacsvjakcaw1', 'sn4py785ao', 0, 0, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('niret6widxlo38o1hu5r7', 'dsackacsvjakcaw1', '58nlzuouogf', 0, 12, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('nk7vgcbh5oq4uchufdi3n', 'dsackacsvjakcaw1', 'u8m1m2bmrac', 0, 12, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('np4vak5jsp', 'dsackacbjakcaw3', '0k0cmg0knry', 0, 21, 6, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('nprkt3uxfyrrj281yovt9', 'dsackacsvjakcaw1', 'x8bs0e8m8j', 0, 3, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('nr766n7kgi8fpzhrliz85s', 'dsackacsvjakcaw1', 'ozgdngc22qq', 0, 11, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('o1rvbrufc5g', 'dsackacbjakcaw3', 'du371194q1l', 0, 23, 6, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('oss5nx24um', 'dsackacbjakcaw3', 'g2jxntuiiyk', 0, 18, 13, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('p3ibremnbz9fnyqjqwqfw', 'dsackacsvjakcaw1', 'xehnjbujyro', 0, 3, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('p3xvmzjpinrx3glchlfe6', 'dsackacsvjakcaw1', '2j20r7f58ip', 0, 10, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('p951ydfb4umhto336jycxj', 'dsackacsvjakcaw1', 'scoukpzp16c', 0, 5, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('qacs5ld6qns5tcshrrka9', 'dsackacsvjakcaw1', '9wor7zfn058', 0, 7, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('qs52hlhhvdl', 'dsackacbjakcaw3', '51bllm1p0b', 0, 6, 13, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('qt327egeew', 'dsackacbjakcaw3', 'epqyh67e1tl', 0, 21, 9, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('quk2cqgkgmiy008msub8zl', 'dsackacsvjakcaw1', 'jp4cw7svbh', 0, 3, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('rg99so0bhn8j6ko2h7kakq', 'dsackacsvjakcaw1', 'ntu2wdi5mq', 0, 3, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('s0pfo2n471m', 'dsackacbjakcaw3', '0zmzukvaxe1g', 0, 21, 10, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('sf907f9iok56zctgzj7sm', 'dsackacsvjakcaw1', '6fhaii2qcmp', 0, 11, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('snu754yh52', 'dsackacbjakcaw3', 'm3zbrqfpzr', 0, 23, 13, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('t48754c0df', 'dsackacbjakcaw3', '90u0dfwcpyg', 0, 9, 12, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('t9v026qhbx', 'dsackacbjakcaw3', '0zcjs3alfpdb', 0, 23, 9, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('u02okram64bi06t6gafhlf', 'dsackacbjakcdfw4', 'pd4gbbsz2f', 0, 2, 0, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ucbdna3vbjrh1eumzxf4wk', 'dsackacsvjakcaw1', '6qubgxc1ku3', 0, 11, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('uicnkvoabho', 'dsackacbjakcaw3', '4qebigbyaxh', 0, 8, 12, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('unn8furhvnjpu7gbabjan', 'dsackacsvjakcaw1', '5ml4misvkgs', 0, 1, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('uss4rhmxe89ncpg38ivnv', 'dsackacsvjakcaw1', 'ck7jd1xq44w', 0, 7, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('v1dljci1e5oycz0wuz27p', 'dsackacsvjakcaw1', 'xr6cfdinkas', 0, 10, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('v3db0m49snfuf27f8xjy6i', 'dsackacsvjakcaw1', 'yqxuq2rns7', 0, 1, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('vfc0vptj38j71e11nt9ewa', 'dsackacsvjakcaw1', 'rww0b3xywng', 0, 1, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('vjb7xj55rm', 'dsackacbjakcaw3', '3y9ffixi8ze', 0, 18, 12, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('vkdhxfpiktf22evdgwx1ab', 'dsackacsvjakcaw1', 'dgtumkhcd0o', 0, 5, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('vop6zp290dos08sntbwhs', 'dsackacsvjakcaw1', 'b7p2rzi40a5', 0, 12, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('vprxn8fjdeeqge9yqdnp3', 'dsackacsvjakcaw1', '455497sxw38', 0, 2, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('vqc6lvjd74je7zjboc5jt', 'dsackacsvjakcaw1', 'hs6gkbmtodg', 0, 7, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('w0f82xi6qwm5njxtfs4pge', 'dsackacsvjakcaw1', 'oa8dyzfvb4h', 0, 4, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('wa9pfhfl9q0j1xe551hl3k', 'dsackacsvjakcaw1', 'maab7viba2d', 0, 12, 1, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ws3lxhgd3cksv6pp5yfzw', 'dsackacsvjakcaw1', '7d63fxlq57q', 0, 0, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('x3eh1wqk7i', 'dsackacbjakcaw3', 'pxvjbrchw1', 0, 23, 12, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('x8sc0we97zjziie04a0k', 'dsackacsvjakcaw1', 'e3eop46oefv', 0, 2, 6, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('xe8ywvtakbjrucpqqpb0g', 'dsackacsvjakcaw1', 'ffqksj3kpch', 0, 2, 4, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('xeoqe9f5wo4rve2fshyu7', 'dsackacsvjakcaw1', 'w1lc2r1x4at', 0, 9, 3, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('xk460lyxwyflnx7oirt23o', 'dsackacsvjakcaw1', 'nfom781zmi', 0, 0, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('xlh7k95g4si1lik6s1qu7o', 'dsackacsvjakcaw1', 'bxhmjabkqgc', 0, 4, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('y70pfa5tiod', 'dsackacbjakcaw3', 'k7gwu3i8cb', 0, 8, 13, 3, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ycrdrmifijaaizebkjcl9', 'dsackacsvjakcaw1', 'abh8smh1il6', 0, 10, 7, 0, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('yor5s9e72ji', 'dsackacbjakcaw3', 'j8bpzmxteph', 0, 20, 12, 1, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('ysrlfeaxrok5kyd5aenwcd', 'dsackacsvjakcaw1', 'wtm2pdcerxn', 0, 9, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('z8ro382ccbcdwo7uecayer', 'dsackacsvjakcaw1', '4w9xevfvf7', 0, 2, 9, 2, 1, 1);
INSERT INTO library.library_seat (seat_id, room_id, name, repair, x, y, direction, width, height) VALUES ('zu9dwdsin633kpydqob2h', 'dsackacsvjakcaw1', 'oc1h6shaj0l', 0, 1, 6, 2, 1, 1);
drop table if exists library_table;
create table library_table
(
    table_id  varchar(100) not null comment '桌子id'
        primary key,
    room_id   varchar(100) null comment '图书室id',
    name      varchar(20)  null comment '座位名称',
    x         int          null comment 'x坐标',
    y         int          null comment 'y坐标',
    direction smallint     null comment '方向',
    width     int          null comment '宽',
    height    int          null comment '高'
);

INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('1i5z4v6hovv', 'dsackacbjakcaw3', 'mnh7g429ocj', 22, 6, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('1oi7c2ldkcjta5c38rhsf', 'dsackacsvjakcaw1', 'cadbc1h0yhh', 4, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('1t2ms90kupong2du1qqn9', 'dsackacsvjakcaw1', 'gkpi77wdndg', 7, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('25nz83hovogd58qywvf73b', 'dsackacsvjakcaw1', 'e3s5ojzhnt8', 4, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('2ejczagty08', 'dsackacbjakcaw3', 'a9wt2tn7r3w', 10, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('2jl5vm757di6nlyxbz9cr9', 'dsackacsvjakcaw1', 'tc7qoo7sjrm', 0, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('4dyxhgvbs5z7gl9ehc7v1h', 'dsackacsvjakcaw1', 'a3w879j2igd', 2, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('4eru0pca5pgv4hhhnspln', 'dsackacsvjakcaw1', 'tbctse3g25', 5, 10, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('76sm10y24ebphut0ow43vc', 'dsackacsvjakcaw1', '3kxlsfxcy3y', 0, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('8lq8xu7m789m3t174ozaip', 'dsackacsvjakcaw1', 'uusrs71fr1', 11, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('8udwuzx0yafhijtne06a0b', 'dsackacsvjakcaw1', 'iutdigurmb', 2, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A01', 'dsackacbjakcaw3', 'A01', 1, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A02', 'dsackacbjakcaw3', 'A02', 4, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A03', 'dsackacbjakcaw3', 'A03', 7, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A04', 'dsackacbjakcaw3', 'A04', 10, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A05', 'dsackacbjakcaw3', 'A05', 13, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A06', 'dsackacbjakcaw3', 'A06', 16, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A07', 'dsackacbjakcaw3', 'A07', 1, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A08', 'dsackacbjakcaw3', 'A08', 4, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A09', 'dsackacbjakcaw3', 'A09', 7, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A10', 'dsackacbjakcaw3', 'A10', 10, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A11', 'dsackacbjakcaw3', 'A11', 13, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A12', 'dsackacbjakcaw3', 'A06', 16, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A13', 'dsackacbjakcaw3', 'A13', 1, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A14', 'dsackacbjakcaw3', 'A14', 4, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A15', 'dsackacbjakcaw3', 'A15', 7, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A16', 'dsackacbjakcaw3', 'A16', 10, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A17', 'dsackacbjakcaw3', 'A17', 13, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A18', 'dsackacbjakcaw3', 'A06', 16, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A19', 'dsackacbjakcaw3', 'A13', 1, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A20', 'dsackacbjakcaw3', 'A14', 4, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A21', 'dsackacbjakcaw3', 'A15', 7, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A22', 'dsackacbjakcaw3', 'A16', 10, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A23', 'dsackacbjakcaw3', 'A17', 13, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A24', 'dsackacbjakcaw3', 'A18', 16, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A25', 'dsackacbjakcaw3', 'A06', 19, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A26', 'dsackacbjakcaw3', 'A06', 19, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A27', 'dsackacbjakcaw3', 'A06', 4, 12, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A28', 'dsackacbjakcaw3', 'A18', 1, 12, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A29', 'dsackacbjakcaw3', 'A16', 19, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A30', 'dsackacbjakcaw3', 'A17', 22, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A31', 'dsackacbjakcaw3', 'A18', 19, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A32', 'dsackacbjakcaw3', 'A18', 22, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('b7b8vsyhlsb78c5ufjjq5', 'dsackacsvjakcaw1', 'iescm3m7mf', 5, 10, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('cfwtyb2cz7o', 'dsackacbjakcaw3', '47y61iosabc', 16, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('dd8g5nozwppwz01c9pfb5h', 'dsackacsvjakcaw1', 'cy2murrt43o', 4, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('dozgw9bg0dw97k3fjldyas', 'dsackacsvjakcaw1', 'rckljxz1stc', 7, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('h4euiefawbbyolnn1ddtt8', 'dsackacbjakcdfw4', 'yzsid7vzdmg', 5, 0, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('hdjtxpcgyqhbab7lqhzt5s', 'dsackacbjakcdfw4', 'yemviu1ay7m', 1, 0, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('hw9m6gn640p', 'dsackacbjakcaw3', 'u6cl1x7lqpr', 7, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('iig7q2i8ojo8v7sosxrqpg', 'dsackacsvjakcaw1', 'boygb8caxzm', 11, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('kdmwzqwg46k56mdoko2bf8', 'dsackacsvjakcaw1', '3mvwgetzfvj', 9, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('m3ogwh009ba6cy1gr0azr', 'dsackacsvjakcaw1', '509fvy1ivel', 0, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('mmmgkfrazb', 'dsackacbjakcaw3', 'k6vgeinkwdf', 22, 9, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('qdv1qqux3tobc7askf7mnn', 'dsackacsvjakcaw1', 'nufh1k1qfj9', 7, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('rwrguhjbzdq1v58dzwnxok', 'dsackacbjakcdfw4', '62o9m7tx2fh', 5, 2, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('sggwpayyahp', 'dsackacbjakcaw3', 'jqrf6jabc2h', 19, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('svbzx38c2za4mwhwl67y1v', 'dsackacsvjakcaw1', 't9nc71psck8', 9, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('vq9lcdrzphms1hg1iioe4', 'dsackacsvjakcaw1', 'lfncw1eal5', 2, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wjwlqddqmjzdudogrywco', 'dsackacbjakcdfw4', '96ym1r9lzhw', 1, 2, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wq9y2qgwrln', 'dsackacbjakcaw3', 'gftnwg8f1wu', 22, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wvzyfahk24oluzh4gppi6e', 'dsackacsvjakcaw1', 'u69axy2fns', 11, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wzws9qpantn', 'dsackacbjakcaw3', 'so4zh7js67', 13, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('zfl45vka7yazcvm4tzs4hb', 'dsackacsvjakcaw1', 'mrbpcufmhw', 9, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('zso8k5ni0hgei7qzt05ql', 'dsackacsvjakcaw1', '8hhwomdimwl', 6, 10, 1, 1, 1);
