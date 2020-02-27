FROM java
MAINTAINER cjh
VOLUME /app
EXPOSE 8888
ARG APP
ADD target/${APP}.jar /app/app.jar
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ["sh","/run.sh"]
#ENTRYPOINT ["java","-jar","/app/app.jar","--server.port=8888"]