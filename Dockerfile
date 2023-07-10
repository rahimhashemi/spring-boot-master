#FROM ubuntu:latest
#LABEL authors="simapth"
#
#ENTRYPOINT ["top", "-b"]

#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/spring-boot-docker-1.jar

#FROM openjdk:17-jdk-alpine
FROM khipu/openjdk17-alpine:latest
MAINTAINER springBoot
COPY target/docker-message-server-1.0.0.jar message-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]

#
# Build stage
#
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package
#
##
## Package stage
##
#FROM openjdk:11-jre-slim
#COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]