FROM maven as build
COPY . /app
WORKDIR /app
RUN mvn dependency:go-offline -Dmaven.repo.local=/app/local-repo/.m2/repository

# Соберите приложение без упаковки в jar-файл
RUN mvn package -DskipTests=true -Dmaven.repo.local=/app/local-repo/.m2/repository

FROM openjdk:17
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "/app/app.jar"]