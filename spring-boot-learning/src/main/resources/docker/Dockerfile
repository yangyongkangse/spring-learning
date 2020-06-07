# 基于哪个镜像
FROM java:11
# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 维护者信息
MAINTAINER yangyk "yangyongkang@yangyk.com"
# 拷贝文件到容器
ADD springboot-learning-0.0.1-SNAPSHOT.jar app.jar
# 执行命令
RUN bash -c 'touch app.jar'
# 声明端口
EXPOSE 8081
# 配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","app.jar"]