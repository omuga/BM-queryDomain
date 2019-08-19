FROM java:8-jdk-alpine

LABEL maintainer="gcarvaja"

COPY demo/target/BM-queryDomain.jar /BM-queryDomain.jar

EXPOSE 8080

CMD ["java","-jar","BM-queryDomain.jar"]
