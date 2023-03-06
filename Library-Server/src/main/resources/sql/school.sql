use library;

-- 学校表
drop table if exists school;
create table school
(
    school_id varchar(100) primary key comment 'id',
    `name`    varchar(40) comment '名称'
);
insert into school(school_id, name) value ('dcajhbadhcavacda', '重庆邮电大学');

-- 图书馆
drop table if exists library;
create table library
(
    library_id varchar(100) primary key comment '图书馆Id',
    school_id  varchar(100) comment '学校id',
    `name`     varchar(20) comment '图书馆名字',
    `describe` varchar(300) comment '图书馆描述'
);
insert into library
values ('jdgchvauajkuvbh', 'dcajhbadhcavacda', '数字图书馆', '新图书馆'),
       ('jdgchvauajkavavvbh', 'dcajhbadhcavacda', '老图书馆', '老图书馆');


-- 图书室
drop table if exists libraryRoom;
create table libraryRoom
(
    room_id    varchar(100) primary key comment '图书室id',
    library_id varchar(100) comment '图书馆Id',
    `name`     varchar(200) comment '图书室名字',
    'describe' varchar(300) comment '图书室描述'
);


-- 图书座位
drop table if exists librarySeat;
create table librarySeat
(
    seat_id varchar(100) primary key comment '座位id',
    room_id varchar(100) comment '图书室id',

    `name`  varchar(20) comment '座位名称'
);


-- 学校图书室设备
drop table if exists libraryEquipment;
create table libraryEquipment
(
    equipment_id varchar(100) primary key comment '设备id',
    school_id    varchar(100) comment '学校id',
    room_id      varchar(100) primary key comment '图书室id'
);

-- 通知
drop table if exists notification;
create table notification
(
    notification_id varchar(50) primary key comment 'id',
    school_id       varchar(100) comment '学校id',
    user_id         varchar(100) comment '用户id',
    'title'         varchar(50) comment '标题',
    `context`       varchar(2000) comment '内容',
    `view`          int comment '浏览量',
    `date`          datetime comment '发布日期'
);

-- 预约规则
drop table if exists school_rule;
create table school_rule
(
    school_id varchar(100) primary key comment '学校id',
    `context` varchar(2000) comment '内容'
);

-- 反馈信息
drop table if exists feedback;
create table feedback
(
    feedback_id varchar(50) primary key comment 'id',
    school_id   varchar(100) comment '学校id',
    user_id     varchar(100) comment '用户id',
    'title'     varchar(50) comment '标题',
    `context`   varchar(2000) comment '内容',
    `date`      datetime comment '发布日期'
);