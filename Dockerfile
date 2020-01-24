FROM adoptopenjdk/openjdk8:latest

EXPOSE 9011:9011
COPY ./build/libs/*.jar shortifyUrl.jar
ENTRYPOINT java -jar shortifyUrl.jar
