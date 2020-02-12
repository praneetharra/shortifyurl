FROM adoptopenjdk/openjdk8:latest

EXPOSE 80:9011
COPY ./build/libs/*.jar shortifyUrl.jar
ENTRYPOINT java -jar shortifyUrl.jar
