FROM java:8
VOLUME /tmp
ADD discovery.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dgrails.env=prod","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]