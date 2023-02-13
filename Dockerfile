FROM gradle:jdk17 as builder
ENV HOME /var/lib/microservices
WORKDIR $HOME/kameleoonTask
ADD --chown=gradle:gradle ${PWD} ./
RUN gradle bootJar

FROM openjdk:17-jdk-alpine as packager
ENV HOME_PROJECT /opt/bintrader
ENV NAME_JAR kameleoonTask-0.0.1-SNAPSHOT.jar
RUN mkdir -p $HOME_PROJECT
COPY --from=builder /var/lib/microservices/kameleoonTask/build/libs/$NAME_JAR $HOME_PROJECT
RUN chmod a+x $HOME_PROJECT/$NAME_JAR
VOLUME ["/var/log/kameleoonTask/"]
EXPOSE 8080
ENTRYPOINT java -jar $HOME_PROJECT/$NAME_JAR