FROM openjdk:11
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/backend-websocket-0.0.1-SNAPSHOT.jar springbootwebsocketbackend.jar
EXPOSE 6969
# ENTRYPOINT exec java $JAVA_OPTS -jar springbootwebsocketbackend.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar springbootwebsocketbackend.jar
