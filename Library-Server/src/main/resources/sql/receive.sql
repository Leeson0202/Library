use library;


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


