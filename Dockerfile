From openjdk:8
Expose 8095
Add target/SportyShoesWebApp.jar SportyShoesWebApp.war
ENTRYPOINT  ["java","-jar","/SportyShoesWebApp.war"]