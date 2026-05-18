# ZonaFitSpring

[![Udemy Course](https://img.shields.io/badge/Udemy-course-orange?style=for-the-badge&logo=udemy)](https://www.udemy.com/share/101WEY3@2hS4FlKTOm2TDmpcivuL1ajHHl6rqZGLmfZ6s2OOE4kYi-0RRiv__fwcsuPm-rVznA==/)

A Spring Boot project for a gym client management application with multiple UI options.

This project started from a Udemy course example. The course used Java 21 and older framework versions, so I updated the code and dependencies to Java 25 and newer Spring/JSF tooling, refreshing many parts manually to make the application work on the current environment.

Course reference: https://www.udemy.com/share/101WEY3@2hS4FlKTOm2TDmpcivuL1ajHHl6rqZGLmfZ6s2OOE4kYi-0RRiv__fwcsuPm-rVznA==/

## What this project uses

- Java 25
- Spring Boot 4.0.6
- Spring Data JPA
- MySQL connector (`mysql-connector-j`)
- JoinFaces + PrimeFaces for JSF web UI
- FlatLaf for Swing desktop UI styling
- Lombok as a provided dependency for code generation

## Project features

- `gm.zona_fit.ZonaFitWeb`: Spring Boot web application using JSF + PrimeFaces
- `gm.zona_fit.ZonaFitSwing`: Desktop Swing application with FlatLaf look and feel
- `gm.zona_fit.ZonaFitApplication`: Command-line/console version for client management

## Main components

- `src/main/java/gm/zona_fit/model/Cliente.java`
  - JPA entity for gym clients
- `src/main/java/gm/zona_fit/repository/ClienteRepositorio.java`
  - Spring Data JPA repository for `Cliente`
- `src/main/java/gm/zona_fit/service/ClienteServicio.java`
  - Business logic for managing clients
- `src/main/java/gm/zona_fit/controler/IndexControlador.java`
  - JSF managed bean for the PrimeFaces web UI
- `src/main/resources/META-INF/resources/index.xhtml`
  - PrimeFaces web page for client management
- `src/main/java/gm/zona_fit/ui/ZonaFitForma.java`
  - Swing UI form for desktop mode

## Run instructions

### Web application

1. Configure your MySQL connection in `src/main/resources/application.properties`
2. Run the web app with Maven:

```bash
./mvnw spring-boot:run -Dspring-boot.run.main-class=gm.zona_fit.ZonaFitWeb
```

3. Open the browser at `http://localhost:8080/index.xhtml`

### Swing desktop application

```bash
./mvnw spring-boot:run -Dspring-boot.run.main-class=gm.zona_fit.ZonaFitSwing
```

### Console application

```bash
./mvnw spring-boot:run -Dspring-boot.run.main-class=gm.zona_fit.ZonaFitApplication
```

## Notes

- The project uses `spring-boot-starter-web` even though the Swing mode starts Spring in non-web mode.
- `lombok` is configured as provided and is excluded from the final Spring Boot package.
- JSF pages are served from `src/main/resources/META-INF/resources` using JoinFaces.

## Useful Maven commands

- Build: `./mvnw clean package`
- Test: `./mvnw test`
- Run with a specific main class: `./mvnw spring-boot:run -Dspring-boot.run.main-class=<main-class>`
