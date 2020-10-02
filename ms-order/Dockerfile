FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/app.jar
WORKDIR /app
ENTRYPOINT java -jar app.jar
 
