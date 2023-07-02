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