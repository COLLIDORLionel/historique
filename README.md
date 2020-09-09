# Getting Started

## Prerequisites

Ensure you have the following installed on your machine:

- [ ] [Java 8](https://www.java.com/en/download/help/download_options.xml)
- [ ] [Maven 3.6.3](https://maven.apache.org/install.html)
- [ ] [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [ ] An IDE or Editor of your choice

### Running the Application

1. Clone the repository
```
$ git clone https://github.com/lionelcollidor/historique.git
```

2. Check into the cloned repository
```
$ cd historique
```

3. Install the dependencies and package the application
```
$ mvn package
```

4. Run the API
```
mvn spring-boot:run
```

5. Use the API at:
```
http://localhost:8080/status
http://localhost:8080/saches
http://localhost:8080/tacheStatuts
```

