#FROM ubuntu
#FROM openjdk:8u212-jre-alpine
FROM openjdk:8-jdk-alpine
WORKDIR /usr/local/code
#将源码复制到下一个镜像中
COPY ./target/Library-Server-1.0.jar /usr/local/code

RUN echo "Asia/Shanghai" > /etc/timezone

#声明项目所用端口
EXPOSE 8080

#启动java项目程序
ENTRYPOINT ["java","-jar","Library-Server-1.0.jar"]
# docker build -t library-server:1.0 . --no-cache
# docker run -d -p 8080:8080 --network docker-net --name library library-server:1.0
