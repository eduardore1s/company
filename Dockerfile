FROM adoptopenjdk/openjdk8
MAINTAINER edsilveira.reis@gmail.com
RUN mkdir /app
WORKDIR /app
COPY ./target/company-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java","-jar","company-0.0.1-SNAPSHOT.jar"]
