FROM java:8
VOLUME /tmp
ADD library-0.1.war app.war
RUN sh -c 'touch /app.war'
ENTRYPOINT ["java","-Dgrails.env=prod","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]