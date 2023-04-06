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
    real_name   varchar(50) comment '真实姓名',
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

# insert into user_school
# values ('knadjcva', '12344321', 'dcajhbadhcavacda', true);





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
# insert into school value ('dcajhbadhcavacda', '重庆邮电大学',
#                           '重庆邮电大学（Chongqing University of Posts and Telecommunications）简称“重邮”，坐落于直辖市——重庆市，是中华人民共和国工业和信息化部与重庆市人民政府共建的教学研究型大学，入选国家“中西部高校基础能力建设工程”、国家“卓越工程师教育培养计划”，是国家“2011计划”核心协同高校、中国政府奖学金来华留学生接收院校、国家大学生文化素质教育基地、国家布点设立并重点建设的四所邮电高校之一，重庆市一流学科建设高校，CDIO工程教育联盟成员单位。',
#                           'https://bkimg.cdn.bcebos.com/pic/3c6d55fbb2fb43163d37add525a4462309f7d371?x-bce-process=image/resize,m_lfit,w_536,limit_1',
#                           '/img/schoolbg.jpeg');

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
    tt         int comment '时段长度'
);
# insert into library
# values ('jdgchvauajkuvbh', 'dcajhbadhcavacda', '数字图书馆', '新图书馆',
#         'http://ehall.cqupt.edu.cn/new/portal/css/dark/millennium/images/bg/bg.jpg'),
#        ('jdgchvauajkavavvbh', 'dcajhbadhcavacda', '老图书馆', '老图书馆',
#         'https://ids.cqupt.edu.cn/authserver/cquptDzTheme/static/web/dzimages/bg1.jpg');


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
# insert into library_room
# values ('dsackacsvjakcaw1', 'jdgchvauajkavavvbh', '一楼电脑室', '一楼电脑室', 12, true, '8:00:00', '22:00:00'),
#        ('dsavasckacbjakcaw2', 'jdgchvauajkavavvbh', '二楼电脑室', '二楼电脑室', 12, true, '8:00:00', '22:00:00'),
#        ('dsackacbjakcaw3', 'jdgchvauajkuvbh', '一楼阅览室', '一楼阅览室', 12, true, '8:00:00', '22:00:00'),
#        ('dsackacbjakcdfw4', 'jdgchvauajkuvbh', '二楼阅览室', '二楼阅览室', 12, true, '8:00:00', '22:00:00');


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
# insert into library_seat
# values ('A1001', 'dsackacbjakcaw3', 'A101', false, 1, 1, 3, 11, 11),
#        ('A1002', 'dsackacbjakcaw3', 'A102', false, 1, 2, 3, 11, 11),
#        ('A1003', 'dsackacbjakcaw3', 'A103', false, 3, 1, 1, 11, 11),
#        ('A1004', 'dsackacbjakcaw3', 'A104', false, 3, 2, 1, 11, 11),
#        ('A1005', 'dsackacbjakcaw3', 'A105', false, 4, 1, 3, 11, 11),
#        ('A1006', 'dsackacbjakcaw3', 'A106', false, 4, 2, 3, 11, 11),
#        ('A1007', 'dsackacbjakcaw3', 'A107', false, 6, 1, 1, 11, 11),
#        ('A1008', 'dsackacbjakcaw3', 'A108', false, 6, 2, 1, 11, 11),
#        ('A1009', 'dsackacbjakcaw3', 'A109', false, 7, 1, 3, 11, 11),
#        ('A1010', 'dsackacbjakcaw3', 'A110', false, 7, 2, 3, 11, 11),
#        ('A1011', 'dsackacbjakcaw3', 'A111', false, 9, 1, 1, 11, 11),
#        ('A1012', 'dsackacbjakcaw3', 'A112', false, 9, 2, 1, 11, 11),
#        ('A1013', 'dsackacbjakcaw3', 'A109', false, 10, 1, 3, 11, 11),
#        ('A1014', 'dsackacbjakcaw3', 'A110', false, 10, 2, 3, 11, 11),
#        ('A1015', 'dsackacbjakcaw3', 'A111', false, 12, 1, 1, 11, 11),
#        ('A1016', 'dsackacbjakcaw3', 'A112', false, 12, 2, 1, 11, 11),
#        ('A1017', 'dsackacbjakcaw3', 'A109', false, 13, 1, 3, 11, 11),
#        ('A1018', 'dsackacbjakcaw3', 'A110', false, 13, 2, 3, 11, 11),
#        ('A1019', 'dsackacbjakcaw3', 'A111', false, 15, 1, 1, 11, 11),
#        ('A1020', 'dsackacbjakcaw3', 'A112', false, 15, 2, 1, 11, 11),
#        ('A1021', 'dsackacbjakcaw3', 'A109', false, 16, 1, 3, 11, 11),
#        ('A1022', 'dsackacbjakcaw3', 'A110', false, 16, 2, 3, 11, 11),
#        ('A1023', 'dsackacbjakcaw3', 'A111', false, 18, 1, 1, 11, 11),
#        ('A1024', 'dsackacbjakcaw3', 'A112', false, 18, 2, 1, 11, 11),
#        ('A1200', 'dsackacbjakcaw3', 'A109', false, 19, 1, 3, 11, 11),
#        ('A1201', 'dsackacbjakcaw3', 'A110', false, 19, 2, 3, 11, 11),
#        ('A1202', 'dsackacbjakcaw3', 'A111', false, 21, 1, 1, 11, 11),
#        ('A1203', 'dsackacbjakcaw3', 'A112', false, 21, 2, 1, 11, 11),
#
#        ('A1025', 'dsackacbjakcaw3', 'A101', false, 1, 4, 3, 11, 11),
#        ('A1026', 'dsackacbjakcaw3', 'A102', false, 1, 5, 3, 11, 11),
#        ('A1027', 'dsackacbjakcaw3', 'A103', false, 3, 4, 1, 11, 11),
#        ('A1028', 'dsackacbjakcaw3', 'A104', false, 3, 5, 1, 11, 11),
#        ('A1029', 'dsackacbjakcaw3', 'A105', false, 4, 4, 3, 11, 11),
#        ('A1030', 'dsackacbjakcaw3', 'A106', false, 4, 5, 3, 11, 11),
#        ('A1031', 'dsackacbjakcaw3', 'A107', false, 6, 4, 1, 11, 11),
#        ('A1032', 'dsackacbjakcaw3', 'A108', false, 6, 5, 1, 11, 11),
#        ('A1033', 'dsackacbjakcaw3', 'A109', false, 7, 4, 3, 11, 11),
#        ('A1034', 'dsackacbjakcaw3', 'A110', false, 7, 5, 3, 11, 11),
#        ('A1035', 'dsackacbjakcaw3', 'A111', false, 9, 4, 1, 11, 11),
#        ('A1036', 'dsackacbjakcaw3', 'A112', false, 9, 5, 1, 11, 11),
#        ('A1037', 'dsackacbjakcaw3', 'A109', false, 10, 4, 3, 11, 11),
#        ('A1038', 'dsackacbjakcaw3', 'A110', false, 10, 5, 3, 11, 11),
#        ('A1039', 'dsackacbjakcaw3', 'A111', false, 12, 4, 1, 11, 11),
#        ('A1040', 'dsackacbjakcaw3', 'A112', false, 12, 5, 1, 11, 11),
#        ('A1041', 'dsackacbjakcaw3', 'A109', false, 13, 4, 3, 11, 11),
#        ('A1042', 'dsackacbjakcaw3', 'A110', false, 13, 5, 3, 11, 11),
#        ('A1043', 'dsackacbjakcaw3', 'A111', false, 15, 4, 1, 11, 11),
#        ('A1044', 'dsackacbjakcaw3', 'A112', false, 15, 5, 1, 11, 11),
#        ('A1045', 'dsackacbjakcaw3', 'A109', false, 16, 4, 3, 11, 11),
#        ('A1046', 'dsackacbjakcaw3', 'A110', false, 16, 5, 3, 11, 11),
#        ('A1047', 'dsackacbjakcaw3', 'A111', false, 18, 4, 1, 11, 11),
#        ('A1048', 'dsackacbjakcaw3', 'A112', false, 18, 5, 1, 11, 11),
#        ('A1205', 'dsackacbjakcaw3', 'A109', false, 19, 4, 3, 11, 11),
#        ('A1206', 'dsackacbjakcaw3', 'A110', false, 19, 5, 3, 11, 11),
#        ('A1207', 'dsackacbjakcaw3', 'A111', false, 21, 4, 1, 11, 11),
#        ('A1208', 'dsackacbjakcaw3', 'A112', false, 21, 5, 1, 11, 11),
#
#        ('A1049', 'dsackacbjakcaw3', 'A101', false, 1, 7, 3, 11, 11),
#        ('A1050', 'dsackacbjakcaw3', 'A102', false, 1, 8, 3, 11, 11),
#        ('A1051', 'dsackacbjakcaw3', 'A103', false, 3, 7, 1, 11, 11),
#        ('A1052', 'dsackacbjakcaw3', 'A104', false, 3, 8, 1, 11, 11),
#        ('A1053', 'dsackacbjakcaw3', 'A105', false, 4, 7, 3, 11, 11),
#        ('A1054', 'dsackacbjakcaw3', 'A106', false, 4, 8, 3, 11, 11),
#        ('A1055', 'dsackacbjakcaw3', 'A107', false, 6, 7, 1, 11, 11),
#        ('A1056', 'dsackacbjakcaw3', 'A108', false, 6, 8, 1, 11, 11),
#        ('A1057', 'dsackacbjakcaw3', 'A109', false, 7, 7, 3, 11, 11),
#        ('A1058', 'dsackacbjakcaw3', 'A110', false, 7, 8, 3, 11, 11),
#        ('A1059', 'dsackacbjakcaw3', 'A111', false, 9, 7, 1, 11, 11),
#        ('A1060', 'dsackacbjakcaw3', 'A112', false, 9, 8, 1, 11, 11),
#        ('A1061', 'dsackacbjakcaw3', 'A109', false, 10, 7, 3, 11, 11),
#        ('A1062', 'dsackacbjakcaw3', 'A110', false, 10, 8, 3, 11, 11),
#        ('A1063', 'dsackacbjakcaw3', 'A111', false, 12, 7, 1, 11, 11),
#        ('A1064', 'dsackacbjakcaw3', 'A112', false, 12, 8, 1, 11, 11),
#        ('A1065', 'dsackacbjakcaw3', 'A109', false, 13, 7, 3, 11, 11),
#        ('A1066', 'dsackacbjakcaw3', 'A110', false, 13, 8, 3, 11, 11),
#        ('A1067', 'dsackacbjakcaw3', 'A111', false, 15, 7, 1, 11, 11),
#        ('A1068', 'dsackacbjakcaw3', 'A112', false, 15, 8, 1, 11, 11),
#        ('A1069', 'dsackacbjakcaw3', 'A109', false, 16, 7, 3, 11, 11),
#        ('A1070', 'dsackacbjakcaw3', 'A110', false, 16, 8, 3, 11, 11),
#        ('A1071', 'dsackacbjakcaw3', 'A111', false, 18, 7, 1, 11, 11),
#        ('A1072', 'dsackacbjakcaw3', 'A112', false, 18, 8, 1, 11, 11),
#        ('A1209', 'dsackacbjakcaw3', 'A109', false, 19, 7, 3, 11, 11),
#        ('A1210', 'dsackacbjakcaw3', 'A110', false, 19, 8, 3, 11, 11),
#        ('A1211', 'dsackacbjakcaw3', 'A111', false, 21, 7, 1, 11, 11),
#        ('A1212', 'dsackacbjakcaw3', 'A112', false, 21, 8, 1, 11, 11),
#
#        ('A1073', 'dsackacbjakcaw3', 'A101', false, 1, 10, 3, 11, 11),
#        ('A1074', 'dsackacbjakcaw3', 'A102', false, 1, 11, 3, 11, 11),
#        ('A1075', 'dsackacbjakcaw3', 'A103', false, 3, 10, 1, 11, 11),
#        ('A1076', 'dsackacbjakcaw3', 'A104', false, 3, 11, 1, 11, 11),
#        ('A1077', 'dsackacbjakcaw3', 'A105', false, 4, 10, 3, 11, 11),
#        ('A1078', 'dsackacbjakcaw3', 'A106', false, 4, 11, 3, 11, 11),
#        ('A1079', 'dsackacbjakcaw3', 'A107', false, 6, 10, 1, 11, 11),
#        ('A1080', 'dsackacbjakcaw3', 'A108', false, 6, 11, 1, 11, 11),
#        ('A1081', 'dsackacbjakcaw3', 'A109', false, 7, 10, 3, 11, 11),
#        ('A1082', 'dsackacbjakcaw3', 'A110', false, 7, 11, 3, 11, 11),
#        ('A1083', 'dsackacbjakcaw3', 'A111', false, 9, 10, 1, 11, 11),
#        ('A1084', 'dsackacbjakcaw3', 'A112', false, 9, 11, 1, 11, 11),
#        ('A1085', 'dsackacbjakcaw3', 'A109', false, 10, 10, 3, 11, 11),
#        ('A1086', 'dsackacbjakcaw3', 'A110', false, 10, 11, 3, 11, 11),
#        ('A1087', 'dsackacbjakcaw3', 'A111', false, 12, 10, 1, 11, 11),
#        ('A1088', 'dsackacbjakcaw3', 'A112', false, 12, 11, 1, 11, 11),
#        ('A1089', 'dsackacbjakcaw3', 'A109', false, 13, 10, 3, 11, 11),
#        ('A1090', 'dsackacbjakcaw3', 'A110', false, 13, 11, 3, 11, 11),
#        ('A1091', 'dsackacbjakcaw3', 'A111', false, 15, 10, 1, 11, 11),
#        ('A1092', 'dsackacbjakcaw3', 'A112', false, 15, 11, 1, 11, 11),
#        ('A1093', 'dsackacbjakcaw3', 'A109', false, 16, 10, 3, 11, 11),
#        ('A1094', 'dsackacbjakcaw3', 'A110', false, 16, 11, 3, 11, 11),
#        ('A1095', 'dsackacbjakcaw3', 'A111', false, 18, 10, 1, 11, 11),
#        ('A1096', 'dsackacbjakcaw3', 'A112', false, 18, 11, 1, 11, 11),
#        ('A1213', 'dsackacbjakcaw3', 'A109', false, 19, 10, 3, 11, 11),
#        ('A1214', 'dsackacbjakcaw3', 'A110', false, 19, 11, 3, 11, 11),
#        ('A1215', 'dsackacbjakcaw3', 'A111', false, 21, 10, 1, 11, 11),
#        ('A1216', 'dsackacbjakcaw3', 'A112', false, 21, 11, 1, 11, 11),
#
#        ('A1217', 'dsackacbjakcaw3', 'A109', false, 22, 1, 3, 11, 11),
#        ('A1218', 'dsackacbjakcaw3', 'A110', false, 22, 2, 3, 11, 11),
#        ('A1219', 'dsackacbjakcaw3', 'A111', false, 22, 4, 3, 11, 11),
#        ('A1220', 'dsackacbjakcaw3', 'A112', false, 22, 5, 3, 11, 11),
#        ('A1221', 'dsackacbjakcaw3', 'A109', false, 22, 7, 3, 11, 11),
#        ('A1222', 'dsackacbjakcaw3', 'A110', false, 22, 8, 3, 11, 11),
#        ('A1223', 'dsackacbjakcaw3', 'A111', false, 22, 10, 3, 11, 11),
#        ('A1224', 'dsackacbjakcaw3', 'A112', false, 22, 11, 3, 11, 11),
#
#        ('A1225', 'dsackacbjakcaw3', 'A109', false, 24, 1, 1, 11, 11),
#        ('A1226', 'dsackacbjakcaw3', 'A110', false, 24, 2, 1, 11, 11),
#        ('A1227', 'dsackacbjakcaw3', 'A111', false, 24, 4, 1, 11, 11),
#        ('A1228', 'dsackacbjakcaw3', 'A112', false, 24, 5, 1, 11, 11),
#        ('A1229', 'dsackacbjakcaw3', 'A109', false, 24, 7, 1, 11, 11),
#        ('A1230', 'dsackacbjakcaw3', 'A110', false, 24, 8, 1, 11, 11),
#        ('A1231', 'dsackacbjakcaw3', 'A111', false, 24, 10, 1, 11, 11),
#        ('A1232', 'dsackacbjakcaw3', 'A112', false, 24, 11, 1, 11, 11);


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
# insert into library_table
# values ('A1001', 'dsackacbjakcaw3', 'A01', 2, 1, 0, 1, 2),
#        ('A1002', 'dsackacbjakcaw3', 'A02', 5, 1, 0, 1, 2),
#        ('A1003', 'dsackacbjakcaw3', 'A03', 8, 1, 0, 1, 2),
#        ('A1004', 'dsackacbjakcaw3', 'A04', 11, 1, 0, 1, 2),
#        ('A1005', 'dsackacbjakcaw3', 'A05', 14, 1, 0, 1, 2),
#        ('A1006', 'dsackacbjakcaw3', 'A06', 17, 1, 0, 1, 2),
#        ('A1025', 'dsackacbjakcaw3', 'A06', 20, 1, 0, 1, 2),
#
#        ('A1007', 'dsackacbjakcaw3', 'A07', 2, 4, 0, 1, 2),
#        ('A1008', 'dsackacbjakcaw3', 'A08', 5, 4, 0, 1, 2),
#        ('A1009', 'dsackacbjakcaw3', 'A09', 8, 4, 0, 1, 2),
#        ('A1010', 'dsackacbjakcaw3', 'A10', 11, 4, 0, 1, 2),
#        ('A1011', 'dsackacbjakcaw3', 'A11', 14, 4, 0, 1, 2),
#        ('A1012', 'dsackacbjakcaw3', 'A06', 17, 4, 0, 1, 2),
#        ('A1026', 'dsackacbjakcaw3', 'A06', 20, 4, 0, 1, 2),
#
#        ('A1013', 'dsackacbjakcaw3', 'A13', 2, 7, 0, 1, 2),
#        ('A1014', 'dsackacbjakcaw3', 'A14', 5, 7, 0, 1, 2),
#        ('A1015', 'dsackacbjakcaw3', 'A15', 8, 7, 0, 1, 2),
#        ('A1016', 'dsackacbjakcaw3', 'A16', 11, 7, 0, 1, 2),
#        ('A1017', 'dsackacbjakcaw3', 'A17', 14, 7, 0, 1, 2),
#        ('A1018', 'dsackacbjakcaw3', 'A06', 17, 7, 0, 1, 2),
#        ('A1027', 'dsackacbjakcaw3', 'A06', 20, 7, 0, 1, 2),
#
#        ('A1019', 'dsackacbjakcaw3', 'A13', 2, 10, 0, 1, 2),
#        ('A1020', 'dsackacbjakcaw3', 'A14', 5, 10, 0, 1, 2),
#        ('A1021', 'dsackacbjakcaw3', 'A15', 8, 10, 0, 1, 2),
#        ('A1022', 'dsackacbjakcaw3', 'A16', 11, 10, 0, 1, 2),
#        ('A1023', 'dsackacbjakcaw3', 'A17', 14, 10, 0, 1, 2),
#        ('A1024', 'dsackacbjakcaw3', 'A18', 17, 10, 0, 1, 2),
#        ('A1028', 'dsackacbjakcaw3', 'A18', 20, 10, 0, 1, 2),
#
#        ('A1029', 'dsackacbjakcaw3', 'A16', 23, 1, 0, 1, 2),
#        ('A1030', 'dsackacbjakcaw3', 'A17', 23, 4, 0, 1, 2),
#        ('A1031', 'dsackacbjakcaw3', 'A18', 23, 7, 0, 1, 2),
#        ('A1032', 'dsackacbjakcaw3', 'A18', 23, 10, 0, 1, 2);

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
# insert into school_notification
# values ('kdjabcdakabedcvd', 'dcajhbadhcavacda', '', '关于寒假闭馆通知',
#         '劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。', 10, '20220210100000');

-- 预约规则
drop table if exists school_rule;
create table school_rule
(
    school_id varchar(100) primary key comment '学校id',
    `context` text comment '内容'
);
# insert into school_rule
# values ('dcajhbadhcavacda', '1、每天7：00开始，读者可以预约当日或次日的座位，预约成功后读者可以使用该座位至当日闭馆。预约系统登录用户名为学号/工号，密码为公共数据库密码。
# 2、读者可通过以下三种方式预约并使用当日座位
# (1)8：00之前预约当日座位，并在8：30之前通过门禁闸机刷卡入馆，系统即可自动完成签到。
# (2)当日其他开放时段，在馆外通过网络预约当日座位，并在预约成功后30分钟内前往图书馆通过门禁闸机刷卡入馆，系统即可自动完成签到。
# (3)通过图书馆门禁闸机刷卡入馆，再通过网络或现场预约机预约当日座位，预约成功后即自动完成签到。
# 3、预约次日座位的读者，需在次日8：30前通过门禁闸机刷卡入馆(系统自动完成签到)。
# 4、取消预约：读者预约次日座位后，可在次日7：00之前取消预约(不限次数)。预约当日座位后，限每天取消1次。具体操作：点击我的中心--我的预约--取消预约。
# 5、“未签到”违规：预约成功后未在第2、第3条规定时间内签到，也未在第4条规定时间内取消预约的读者将被记“未签到”违规1次。
# 6、临时离开：在选座机上刷卡或从网页或从微信端选择“临时离开”，座位将保留60分钟(中午10:30-13:00和下午16:30-19:00期间离开分别保留120分钟和90分钟，从离开时间起算)。已选择“临时离开”的读者在保留时间内返回时，通过门禁闸机入馆即自动完成签到。若选择了“临时离开”，但实际又没离馆，也需在规定时间内在选座机上刷卡或通过网页端操作完成签到。读者未在保留时间内返回签到，系统将自动释放座位供他人选用，并记“临时离开超时”违规1次。
# 7、离馆：读者每次离馆(含临时离馆)均需刷卡从门禁闸机出馆，否则将被记“离开未刷卡”违规。读者未选择“临时离开”，通过门禁闸机出馆时，座位将自动释放。
# 8、没有公共数据库账号的读者(校友等)，不能通过网上提前预约座位，请入馆后在现场选座机上选择预约座位。
# 9、请读者自觉维护馆内秩序，勿随意移动桌椅，个人物品请自行妥善保管。每天闭馆时，请带走所有个人物品(长期存包箱内物品除外)。如被清理，产生的损失由读者本人自负。');

-- 反馈信息
drop table if exists library_feedback;
create table library_feedback
(
    feedback_id varchar(50) primary key comment 'id',
    school_id   varchar(100) comment '学校id',
    user_id     varchar(100) comment '用户id',
    `title`     varchar(50) comment '标题',
    `context`   varchar(2000) comment '内容',
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
values ('12344321', 'dcajhbadhcavacda', 'jdgchvauajkuvbh', 'dsackacbjakcaw3', 'A1001', 1);


