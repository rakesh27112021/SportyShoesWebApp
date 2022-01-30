From openjdk:8
Expose 8095
Add target/SportyShoesWebApp.jar SportyShoesWebApp.jar
ENTRYPOINT  ["java","-jar","/SportyShoesWebApp.jar"]