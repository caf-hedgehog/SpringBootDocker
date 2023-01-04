FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /tmp
COPY ./src ./src
COPY ./pom.xml .
#RUN mvn package
RUN mvn -B package; echo ""
# ビルドで必要となるファイルのみCOPY
COPY ./src/ /usr/src/src/
RUN mvn -B package

FROM openjdk:17
COPY --from=builder /tmp/target/app.jar /app/app.jar
EXPOSE 8080