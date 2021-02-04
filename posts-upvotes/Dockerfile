FROM ubuntu:18.04
WORKDIR /posts-upvotes
COPY . .
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant;
RUN apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;  
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME      
RUN apt-get install maven -y
RUN mvn clean package
RUN sed ':a;N;$!ba;s/\n/ /g' pom.xml > temp-pom.xml
RUN grep -E -o '</name>.*<version>[0-9*].[0-9*].[0-9*]</version>' temp-pom.xml > temp-version.txt
ENTRYPOINT ["./start.sh"]
EXPOSE 8080