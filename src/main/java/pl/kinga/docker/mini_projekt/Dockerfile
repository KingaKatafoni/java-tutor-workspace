FROM eclipse-temurin:21-jdk
WORKDIR /app
RUN curl -O https://jdbc.postgresql.org/download/postgresql-42.7.5.jar
COPY code/ResidentApp.java .
RUN javac -cp postgresql-42.7.5.jar ResidentApp.java
CMD ["java", "-cp", ".:postgresql-42.7.5.jar", "ResidentApp"]