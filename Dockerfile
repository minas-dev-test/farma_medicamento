FROM openjdk:8
ADD target/api-medicamento.jar api-medicamento.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-medicamento.jar"]