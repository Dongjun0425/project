# 1. 사용할 이미지 지정
FROM openjdk:17-jdk-alpine

# 2. JAR 파일을 컨테이너 안에 복사
ARG JAR_FILE=build/libs/my-app.jar
COPY ${JAR_FILE} app.jar

# 3. 애플리케이션 실행 명령
ENTRYPOINT ["java", "-jar", "/app.jar"]
