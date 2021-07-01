FROM ubuntu:18.04

WORKDIR /posts-upvotes

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME      

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk ant ca-certificates-java maven && \
    apt-get clean && \
    update-ca-certificates -f

COPY . .

RUN sed ':a;N;$!ba;s/\n/ /g' pom.xml > temp-pom.xml 
RUN grep -E -o '</name>.*<version>[0-9*].[0-9*].[0-9*]</version>' temp-pom.xml > temp-version.txt
RUN mvn clean package

ENTRYPOINT ["./start.sh"]
EXPOSE 8080