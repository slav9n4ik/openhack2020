FROM openjdk:16-slim
USER root

COPY ./back-service/target/back-service-0.0.1.jar /tmp/back-service-0.0.1.jar
COPY ./back-service/src/main/resources/external-application.properties /tmp/external-application.properties
COPY ./ML/ml.py /tmp/ml.py

RUN apt-get update -y && apt-get install python3-pip -y

CMD java -jar /tmp/back-service-0.0.1.jar --spring.config.location=file:///tmp/external-application.properties
