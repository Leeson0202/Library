use library;

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
);