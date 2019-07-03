FROM jdk1.8

ADD ConfigServer001-*.jar /app.jar

EXPOSE 10086
#设置启动端口参数-Dserver.port=8888
ENV  PORT=""

#设置注册中心地址
ENV EUREKA=""
ENTRYPOINT ["sh","-c","java $PORT $EUREKA -jar /app.jar"]
