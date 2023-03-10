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
insert into school value ('dcajhbadhcavacda', '重庆邮电大学',
                                           '重庆邮电大学（Chongqing University of Posts and Telecommunications）简称“重邮”，坐落于直辖市——重庆市，是中华人民共和国工业和信息化部与重庆市人民政府共建的教学研究型大学，入选国家“中西部高校基础能力建设工程”、国家“卓越工程师教育培养计划”，是国家“2011计划”核心协同高校、中国政府奖学金来华留学生接收院校、国家大学生文化素质教育基地、国家布点设立并重点建设的四所邮电高校之一，重庆市一流学科建设高校，CDIO工程教育联盟成员单位。',
                                           'https://bkimg.cdn.bcebos.com/pic/3c6d55fbb2fb43163d37add525a4462309f7d371?x-bce-process=image/resize,m_lfit,w_536,limit_1',
                                           'https://bkimg.cdn.bcebos.com/pic/d53f8794a4c27d1ef114acd816d5ad6eddc438ff?x-bce-process=image/resize,m_lfit,w_1280,limit_1');

-- 图书馆
drop table if exists library;
create table library
(
    library_id varchar(100) primary key comment '图书馆Id'R,
    school_id  varchar(100) comment '学校id',
    `name`     varchar(20) comment '图书馆名字',
    `descs`    varchar(300) comment '简介',
    background varchar(500) comment '背景'
);
insert into library
values ('jdgchvauajkuvbh', 'dcajhbadhcavacda', '数字图书馆', '新图书馆',
        'http://ehall.cqupt.edu.cn/new/portal/css/dark/millennium/images/bg/bg.jpg'),
       ('jdgchvauajkavavvbh', 'dcajhbadhcavacda', '老图书馆', '老图书馆',
        'https://ids.cqupt.edu.cn/authserver/cquptDzTheme/static/web/dzimages/bg1.jpg');


-- 图书室
drop table if exists library_room;
create table library_room
(
    room_id    varchar(100) primary key comment '图书室id',
    library_id varchar(100) comment '图书馆Id',
    `name`     varchar(200) comment '图书室名字',
    `descs`    varchar(300) comment '图书室描述',
    seat_num   int comment '座位数量',
    weekend    boolean comment '周末是否开放',
    begin_time time comment '每日开放开始时间',
    end_time   time comment '每日开放结束时间'
);
insert into library_room
values ('dsackacbjakcaw', 'jdgchvauajkuvbh', '一楼阅览室', '一楼阅览室', 12, true, '7:00:00', '22:00:00'),
       ('dsackacbjakcdfw', 'jdgchvauajkuvbh', '二楼阅览室', '二楼阅览室', 12, true, '7:00:00', '22:00:00');


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
values ('A101', 'dsackacbjakcaw', 'A101', false, 1, 1, 3, 11, 11),
       ('A102', 'dsackacbjakcaw', 'A102', false, 1, 2, 3, 11, 11),
       ('A103', 'dsackacbjakcaw', 'A103', false, 3, 1, 1, 11, 11),
       ('A104', 'dsackacbjakcaw', 'A104', false, 3, 2, 1, 11, 11),
       ('A105', 'dsackacbjakcaw', 'A105', false, 4, 1, 3, 11, 11),
       ('A106', 'dsackacbjakcaw', 'A106', false, 4, 2, 3, 11, 11),
       ('A107', 'dsackacbjakcaw', 'A107', false, 5, 1, 1, 11, 11),
       ('A108', 'dsackacbjakcaw', 'A108', false, 5, 2, 1, 11, 11),
       ('A109', 'dsackacbjakcaw', 'A109', false, 7, 1, 3, 11, 11),
       ('A110', 'dsackacbjakcaw', 'A110', false, 7, 2, 3, 11, 11),
       ('A111', 'dsackacbjakcaw', 'A111', false, 8, 1, 1, 11, 11),
       ('A112', 'dsackacbjakcaw', 'A112', false, 8, 2, 1, 11, 11);


-- 桌子
drop table if exists library_table;
create table library_table
(
    table_id varchar(100) primary key comment '桌子id',
    room_id  varchar(100) comment '图书室id',
    `name`   varchar(20) comment '座位名称',
    x        int comment 'x坐标',
    y        int comment 'y坐标',
    width    int comment '宽',
    height   int comment '高'
);

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
values ('kdjabcdakabedcvd', 'dcajhbadhcavacda', '', '关于寒假闭馆通知',
        '劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。', 10, '20220210100000');

-- 预约规则
drop table if exists school_rule;
create table school_rule
(
    school_id varchar(100) primary key comment '学校id',
    `context` varchar(2000) comment '内容'
);

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
);