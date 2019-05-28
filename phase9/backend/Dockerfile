# fetch basic image
FROM maven:3.3.9-jdk-8

# application placed into /opt/app
RUN mkdir -p /opt/app
WORKDIR /opt/app

# selectively add the POM file and
# install dependencies
COPY C:/Users/asus/Desktop/backend/pom.xml /opt/app/
RUN mvn install

# rest of the project
COPY C:/Users/asus/Desktop/backend/src /opt/app/src
RUN mvn package

# local application port
EXPOSE 8080

# execute it
CMD ["mvn", "exec:java"]