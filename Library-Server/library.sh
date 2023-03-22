# /bin/bash
# 删除container
docker rm -vf library
# 删除image
docker rmi -f library-server:1.0
docker build -t library-server:1.0 . --no-cache
docker run -d -p 8080:8080 --network docker-net --name library library-server:1.0

# -p 设置暴露端口
# --network 加入网络
#