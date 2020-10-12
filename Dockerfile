FROM adoptopenjdk/openjdk8:latest
RUN mkdir /opt/app
COPY ./target/karros_demo-*.jar /opt/app/karros_demo.jar

CMD ["java", "-jar", "/opt/app/karros_demo.jar"]