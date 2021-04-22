FROM ubuntu:latest
RUN apt update
RUN  echo "America/New_York" > /etc/timezone
RUN apt install -y openjdk-14-jre
COPY alert-service-0.0.2.jar /var
CMD echo "America/New_York" > /etc/timezone ; rm -f /etc/localtime ;dpkg-reconfigure -f noninteractive tzdata ;java -jar -Dspring.datasource.password=11111111 -Dspring.datasource.username=admin -Dspring.datasource.jdbc-url=jdbc:mysql://myrds6510.cup5iuwqdfry.us-east-1.rds.amazonaws.com:3306/hunting_presentation_test  /var/alert-service-0.0.2.jar