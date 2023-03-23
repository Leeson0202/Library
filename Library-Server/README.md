## Library-Server（后端）

### 技术栈

技术栈：SpringBoot、MyBatis、Redis、MySQL、JWT、阿里云SMS、邮箱

jdk：1.8

MySQL：8.0

Redis：7.0

### 如何运行？

1.resource目录下application.properties修改对应的配置。比如mysql、redis。

2.使用邮箱和短信服务的话，将resource/config/config.properties重命名private-config.properties，修改对应的配置。比如msm和email

3.Linux下运行resouce/sql/merge.sh后（就是将四个脚本放在all里面），运行数据库脚步all.sql。



