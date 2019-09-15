# AppChallange

AppChallange is a simple command line aplication to search data available in json files. Searched results are displayed in a human readable format.

Requirments
------------

* JDK 1.8 
* Maven 3.x

Dependencies
------------
All dependencies are available in pom.xml.

Note
------------
* Please make sure to place users.json, tickets.json and organization.json under AppChallange/JsonStore.
* Please do not change json files name.

Build Jar
------------
```
$ cd <path.to.AppChallange>
$ mvn clean compile assembly:single
```
Jar will be file created in <path.to.AppChallange>/target/AppChallenge-1.0-SNAPSHOT-jar-with-dependencies.jar

Run Jar
------------
```
$ cd <path.to.AppChallenge-1.0-SNAPSHOT-jar-with-dependencies.jar>
$ java -jar target/AppChallenge-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Run Tests
------------
```
$ cd <path.to.AppChallange>
$ mvn test
```
