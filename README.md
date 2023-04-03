# 基于微信小程序的座位预约系统

### 介绍

这是我的 2023 年毕业设计，做一个基于微信小程序的座位预约系统，系统分用户端（小程序）和管理员端（Web）。

小程序    ----> 微信搜索：Leeson Library

  前 端     ----> [Leeson Library](https://library.leeson.cool)

#### 特点

- 像选电影票一样进行选座
- 快速预约（定时预约，短信提醒），为考研人保驾护航
- 个人数据展示
- 图书馆数据展示
- 个人计划提示

- 

### **小程序端展示**

- 三个 swichTable

<img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/F3D99B18-1D2B-41D0-B7C4-8CF87246260B_1_102_o.jpeg" alt="F3D99B18-1D2B-41D0-B7C4-8CF87246260B_1_102_o" style="width: 30%;" /> <img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/896E174C-19AB-4F5A-B648-DC56DEBC118D_1_102_o.jpeg" alt="896E174C-19AB-4F5A-B648-DC56DEBC118D_1_102_o" style="width: 30%;" /> <img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/261DB400-7880-4A87-94AA-7E20DB0E5AD7_1_102_o.jpeg" alt="261DB400-7880-4A87-94AA-7E20DB0E5AD7_1_102_o" style="width: 30%;" />

- 功能相关页面（一般预约、快速预约、我的记录、我的规则）

  <img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/7C2448C5-1220-44B1-880B-B6B40EA96441_1_102_o.jpeg" alt="7C2448C5-1220-44B1-880B-B6B40EA96441_1_102_o" style="width:30%;" /> 				<img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/6B27B77B-9D7C-49A1-8AE7-F7DB6B3F2095_1_102_o.jpeg" alt="6B27B77B-9D7C-49A1-8AE7-F7DB6B3F2095_1_102_o" style="width:30%;" />

  

   <img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/IMG_0015.png" style="width:30%;" />				<img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/D9D0779C-6979-4C59-955D-9E0104BDD28E_1_102_o.jpeg" alt="D9D0779C-6979-4C59-955D-9E0104BDD28E_1_102_o" style="width:30%;" />

- 个人中心相关页面 (修改资料、我的学习数据、设置)

<img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/9CDCC6FD-D483-4068-B3B7-91276DBB556F_1_102_o.jpeg" alt="9CDCC6FD-D483-4068-B3B7-91276DBB556F_1_102_o" style="width:25%;" />			<img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/IMG_0018.png" alt="D9D0779C-6979-4C59-955D-9E0104BDD28E_1_102_o" style="width:25%;" />			<img src="https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/3B0C4037-2D98-487F-BF20-7DA0C23C1BF5_1_102_o.jpeg" alt="3B0C4037-2D98-487F-BF20-7DA0C23C1BF5_1_102_o" style="width: 25%;" />



### Web管理员端展示

- 登陆

  ![image-20230404024244132](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024244132.png)

- 首页

  ![image-20230404024402631](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024402631.png)

- 学校管理

![image-20230404024431101](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024431101.png)

![image-20230404024448935](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024448935.png)



![image-20230404024459792](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024459792.png)



- 预约管理

![image-20230404024524309](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024524309.png)



![image-20230404024532040](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024532040.png)







- 信用管理

![image-20230404024550521](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024550521.png)



- 反馈信息

![image-20230404024612807](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024612807.png)

- 设置

![image-20230404024645660](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024645660.png)

![image-20230404024625052](https://ghproxy.com/https://raw.githubusercontent.com/Leeson0202/imgRepository/main/image-20230404024625052.png)





### 系统介绍

系统分为管理员（web）和用户端（微信小程序）。

文件大致分三个部分：

- server （后端）
- miniProject（小程序端）
- vue（前端）

[后端文档](./Library-Server/README.md)

[小程序文档](./Library-miniProject.md)

[前端文档](./Library-vue/README.md)



### 日志

- 【2023/03/31】  小程序和后端基本完成，正在开发web管理员端。
- 【2023/04/04】  vue界面基本完成。
