FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN printf 'server.port=8080\nspring.datasource.url=jdbc:mysql://sql5.freesqldatabase.com:3306/sql5825171\nspring.datasource.username=sql5825171\nspring.datasource.password=BUZQg9zeHZ\nspring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver\nspring.jpa.hibernate.ddl-auto=update\nspring.jpa.show-sql=true\nspring.jpa.open-in-view=false\njwt.secret=mysupersecretkey12345678901234567890\njwt.expiration=86400000\nspring.mail.host=smtp.gmail.com\nspring.mail.port=587\nspring.mail.username=nallasivam8220@gmail.com\nspring.mail.password=wwtinjpgihbqpnca\nspring.mail.properties.mail.smtp.auth=true\nspring.mail.properties.mail.smtp.starttls.enable=true\nfrontend.url=https://job-tracker-frontend.vercel.app\n' > src/main/resources/application.properties
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]