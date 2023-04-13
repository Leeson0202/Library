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
values ('A1001', 'dsackacbjakcaw3', 'A01', 2, 1, 0, 1, 2),
       ('A1002', 'dsackacbjakcaw3', 'A02', 5, 1, 0, 1, 2),
       ('A1003', 'dsackacbjakcaw3', 'A03', 8, 1, 0, 1, 2),
       ('A1004', 'dsackacbjakcaw3', 'A04', 11, 1, 0, 1, 2),
       ('A1005', 'dsackacbjakcaw3', 'A05', 14, 1, 0, 1, 2),
       ('A1006', 'dsackacbjakcaw3', 'A06', 17, 1, 0, 1, 2),
       ('A1025', 'dsackacbjakcaw3', 'A06', 20, 1, 0, 1, 2),

       ('A1007', 'dsackacbjakcaw3', 'A07', 2, 4, 0, 1, 2),
       ('A1008', 'dsackacbjakcaw3', 'A08', 5, 4, 0, 1, 2),
       ('A1009', 'dsackacbjakcaw3', 'A09', 8, 4, 0, 1, 2),
       ('A1010', 'dsackacbjakcaw3', 'A10', 11, 4, 0, 1, 2),
       ('A1011', 'dsackacbjakcaw3', 'A11', 14, 4, 0, 1, 2),
       ('A1012', 'dsackacbjakcaw3', 'A06', 17, 4, 0, 1, 2),
       ('A1026', 'dsackacbjakcaw3', 'A06', 20, 4, 0, 1, 2),

       ('A1013', 'dsackacbjakcaw3', 'A13', 2, 7, 0, 1, 2),
       ('A1014', 'dsackacbjakcaw3', 'A14', 5, 7, 0, 1, 2),
       ('A1015', 'dsackacbjakcaw3', 'A15', 8, 7, 0, 1, 2),
       ('A1016', 'dsackacbjakcaw3', 'A16', 11, 7, 0, 1, 2),
       ('A1017', 'dsackacbjakcaw3', 'A17', 14, 7, 0, 1, 2),
       ('A1018', 'dsackacbjakcaw3', 'A06', 17, 7, 0, 1, 2),
       ('A1027', 'dsackacbjakcaw3', 'A06', 20, 7, 0, 1, 2),

       ('A1019', 'dsackacbjakcaw3', 'A13', 2, 10, 0, 1, 2),
       ('A1020', 'dsackacbjakcaw3', 'A14', 5, 10, 0, 1, 2),
       ('A1021', 'dsackacbjakcaw3', 'A15', 8, 10, 0, 1, 2),
       ('A1022', 'dsackacbjakcaw3', 'A16', 11, 10, 0, 1, 2),
       ('A1023', 'dsackacbjakcaw3', 'A17', 14, 10, 0, 1, 2),
       ('A1024', 'dsackacbjakcaw3', 'A18', 17, 10, 0, 1, 2),
       ('A1028', 'dsackacbjakcaw3', 'A18', 20, 10, 0, 1, 2),

       ('A1029', 'dsackacbjakcaw3', 'A16', 23, 1, 0, 1, 2),
       ('A1030', 'dsackacbjakcaw3', 'A17', 23, 4, 0, 1, 2),
       ('A1031', 'dsackacbjakcaw3', 'A18', 23, 7, 0, 1, 2),
       ('A1032', 'dsackacbjakcaw3', 'A18', 23, 10, 0, 1, 2);

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
);