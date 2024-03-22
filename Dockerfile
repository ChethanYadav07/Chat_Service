From openjdk:17
copy target/Petzy_Chat_Module-0.0.1-SNAPSHOT.jar Petzy_Chat_Module-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","Petzy_Chat_Module-0.0.1-SNAPSHOT.jar"]
EXPOSE 3001