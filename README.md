# proxy-service
Java application Vmedia
- Write a "proxy" that will return the modified text of https://quarkus.io (ideally it must work for any domain);
- To each word, which consists of six letters, you must add a symbol "™";
- For the task you must use Quarkus framework;
- The functionality of the original site must not be altered (JS, including 3rd party JS; CSS; Images and any additional content must be present and in working order);
- All internal navigation links of the site must be replaced by the address of the proxy-server;
- Application must have unit and integration tests included;
- Application container must be available in the repository;

That is, site navigation must be handled by a Proxy without taking the client back to the original site.

Example. A request to, say, {proxy address}/quarkus3/ should show the content of the page
https://quarkus.io/quarkus3/ with changed words that were 6 characters long.
And all the site navigation to sections of the site should go through Proxy.

Publishing the test task
The project must be published in a private repository on github.com or bitbucket.org.
The project must include a read-me file that describes how to run the application.

## Running the application

### 1. Before running don't forget to build the application:

```shell script
./build.sh
```

### 2. Run the application:

```shell script
./run.sh
```

### 3. Don't forget to stop the application:

```shell script
./stop.sh
```

## To check the service call:
    http://localhost:8080/
