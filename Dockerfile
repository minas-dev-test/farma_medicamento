FROM maven:3.6.0-jdk-8-alpine
ENV SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/database"

# Install Wait For It (for Database)
RUN curl -o /bin/wait-for-it https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x /bin/wait-for-it

# Base
WORKDIR /usr/src
COPY pom.xml /usr/src
RUN mvn verify clean --fail-never

# APP
COPY . /usr/src
RUN mvn -v
RUN mvn clean install
RUN mv ./target/api-medicamento.jar ./app.jar

EXPOSE 8080

CMD java -jar app.jar