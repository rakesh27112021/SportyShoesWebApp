From openjdk:8
Expose 8095
Add target/sportyshoeswebapp.war sportyshoeswebapp.war
ENTRYPOINT  ["java","-jar","/sportyshoeswebapp.war"]
