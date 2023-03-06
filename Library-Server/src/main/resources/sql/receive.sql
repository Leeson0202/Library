use library;

-- 用户预约表
create table receive
(
    id      varchar(50) comment 'id',
    user_id varchar(100) primary key comment 'id',
    receive_time datetime comment '预约时间',

    `time` datetime comment '创建时间'
);

-- 用户在线
create table user_online(
    id varchar(50) comment 'id',
    user_id varchar(100) primary key comment 'id',
    `online` boolean comment '在座状态',
    'date' datetime comment '开始就坐时间'
);