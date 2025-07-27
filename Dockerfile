FROM eclipse-temurin:23-jre

ADD target/NotificationService.jar /NotificationService.jar
ADD docker/collector/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT java -javaagent:/opentelemetry-javaagent.jar -jar /NotificationService.jar