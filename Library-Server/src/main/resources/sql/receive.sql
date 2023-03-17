use library;

-- 用户预约订单
drop table if exists receive_order;
create table receive_order
(
    order_id varchar(100) primary key comment 'id',
    user_id  varchar(100) comment '用户id',
    `time`   datetime comment '创建时间'
);


-- 用户预约详细表
drop table if exists receive_item;
create table receive_item
(
    receive_id   varchar(100) primary key comment 'id',
    order_id     varchar(100) comment 'order_id',
    user_id      varchar(100) comment '用户id',
    library_id   varchar(100) comment '图书馆id',
    room_id      varchar(100) comment '图书室id',
    seat_id      varchar(100) comment '座位id',
    -- 多少号几点
    receive_time time comment '预约时间'

);

-- 用户在线
drop table if exists user_online;
create table user_online
(
    user_id  varchar(100) primary key comment 'id',
    `online` boolean comment '在座状态',
    `date`   datetime comment '开始就坐时间'
);

-- 快速预约_订单 (具体体位置 时间) 面向考研
drop table if exists receive_fast;
create table receive_fast
(
    receive_fast_id varchar(100) primary key comment 'id',
    user_id         varchar(100) comment 'id',
    school_id       varchar(100) comment '学校id',
    library_id      varchar(100) comment '图书馆Id',
    room_id         varchar(100) comment '图书室id',
    seat_id         varchar(100) comment '座位id'


);

-- 座位优先级
drop table if exists receive_fast_seat;
create table receive_fast_seat
(
    id              varchar(100) primary key comment 'id',
    receive_fast_id varchar(100) comment 'id',
    seat_id         varchar(100) comment '座位id',
    priority        int comment '优先级'
);
