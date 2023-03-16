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
                          '/img/schoolbg.jpeg');

-- 图书馆
drop table if exists library;
create table library
(
    library_id varchar(100) primary key comment '图书馆Id',
    school_id  varchar(100) comment '学校id',
    `name`     varchar(20) comment '图书馆名字',
    `descs`    varchar(300) comment '简介',
    background varchar(500) comment '背景'
);
insert into library
values ('jdgchvauajkuvbh', 'dcajhbadhcavacda', '数字图书馆', '新图书馆',
        '/img/bg.jpg'),
       ('jdgchvauajkavavvbh', 'dcajhbadhcavacda', '老图书馆', '老图书馆',
        '/img/bg1.jpg');


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
values ('dsackacsvjakcaw1', 'jdgchvauajkavavvbh', '一楼电脑室', '一楼电脑室', 12, true, '8:00:00', '22:00:00'),
       ('dsavasckacbjakcaw2', 'jdgchvauajkavavvbh', '二楼电脑室', '二楼电脑室', 12, true, '8:00:00', '22:00:00'),
       ('dsackacbjakcaw3', 'jdgchvauajkuvbh', '一楼阅览室', '一楼阅览室', 12, true, '8:00:00', '22:00:00'),
       ('dsackacbjakcdfw4', 'jdgchvauajkuvbh', '二楼阅览室', '二楼阅览室', 12, true, '8:00:00', '22:00:00');


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
    `context` text comment '内容'
);
insert into school_rule
values ('dcajhbadhcavacda', '1、每天7：00开始，读者可以预约当日或次日的座位，预约成功后读者可以使用该座位至当日闭馆。预约系统登录用户名为学号/工号，密码为公共数据库密码。
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
    `date`      datetime comment '发布日期'
);