FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY /target/product-inventory-api.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]