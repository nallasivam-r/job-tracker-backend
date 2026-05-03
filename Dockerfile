FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN echo "server.port=8080" > src/main/resources/application.properties && \
    echo "spring.datasource.url=jdbc:mysql://sql5.freesqldatabase.com:3306/sql5825171" >> src/main/resources/application.properties && \
    echo "spring.datasource.username=sql5825171" >> src/main/resources/application.properties && \
    echo "spring.datasource.password=BUZQg9zeHZ" >> src/main/resources/application.properties && \
    echo "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver" >> src/main/resources/application.properties && \
    echo "spring.jpa.hibernate.ddl-auto=update" >> src/main/resources/application.properties && \
    echo "spring.jpa.show-sql=true" >> src/main/resources/application.properties && \
    echo "spring.jpa.open-in-view=false" >> src/main/resources/application.properties && \
    echo "jwt.secret=mysupersecretkey12345678901234567890" >> src/main/resources/application.properties && \
    echo "jwt.expiration=86400000" >> src/main/resources/application.properties && \
    echo "spring.mail.host=smtp.gmail.com" >> src/main/resources/application.properties && \
    echo "spring.mail.port=587" >> src/main/resources/application.properties && \
    echo "spring.mail.username=nallasivam8220@gmail.com" >> src/main/resources/application.properties && \
    echo "spring.mail.password=wwtinjpgihbqpnca" >> src/main/resources/application.properties && \
    echo "spring.mail.properties.mail.smtp.auth=true" >> src/main/resources/application.properties && \
    echo "spring.mail.properties.mail.smtp.starttls.enable=true" >> src/main/resources/application.properties && \
    echo "frontend.url=https://job-tracker-frontend.vercel.app" >> src/main/resources/application.properties

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]