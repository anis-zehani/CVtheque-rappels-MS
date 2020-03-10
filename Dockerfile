### STAGE 1: Build ###
FROM openjdk:8-alpine
ADD target/rappels-MS.jar rappels-MS.jar
ENTRYPOINT ["java","-jar","/rappels-MS.jar"]
