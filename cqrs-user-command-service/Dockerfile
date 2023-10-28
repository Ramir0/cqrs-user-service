FROM azul/zulu-openjdk:17-latest
LABEL authors="amir"

VOLUME /tmp
COPY build/libs/*.jar user-cmd.jar
ENTRYPOINT ["java","-jar","/user-cmd.jar"]
