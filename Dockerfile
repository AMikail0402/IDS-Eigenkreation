FROM eclipse-temurin:11
RUN mkdir /opt/app
COPY IDS-Eigenkreation.jar /opt/app
COPY rules.conf /
CMD ["ls"]
CMD ["java", "-jar", "/opt/app/IDS-Eigenkreation.jar"]
