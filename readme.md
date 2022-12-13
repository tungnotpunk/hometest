# HomeTest For AnyMind


A gRPC server by java, a home test for AnyMind, designed two apis for a POS integrated e-commerce platform. 

  - Required to validate the input and throw error if not correct
  - One api for save point, and the other one is a report
  - Considered to test it easily, so added db dockerfile into this repo 
  - A miscoservice Grpc base by Protobuf

### Tech

Dillinger uses a number of open source projects to work properly:

* [Grpc](https://grpc.io/) - Api Server base tech!
* [Protobuf](https://developers.google.com/protocol-buffers) - Google's data interchange format
* [markdown-it](https://github.com/markdown-it/markdown-it) - Markdown parser done right. Fast and easy to extend.
* [java](https://www.java.com/) - evented I/O for the backend
* [maven](https://maven.apache.org/) - a software project management and comprehension tool
* [Spring Boot](https://spring.io/projects/spring-boot) - Rest Framework, ORM
* [docker](https://www.docker.com/) - Accelerated, Containerized Application Development
* [docker-compose](https://github.com/docker/compose) - run multi-container applications with Docker
* [MySQL](https://www.mysql.com/) - database

### Database

If you want to use your own database, you might need to update the config and create the database yourself, here is the schema for creating it.

```sh
CREATE SCHEMA `anymind` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
CREATE TABLE `anymind`.`charge_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL,
  `price_modifier` DOUBLE NULL,
  `payment_method` VARCHAR(20) NULL,
  `final_price` DOUBLE NULL,
  `points` DOUBLE NULL,
  `datetime` DATETIME NULL,
  `datetime_in_hour` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`datetime` ASC, `datetime_in_hour` ASC));

```

### Installation

This home test requires [docker](https://www.docker.com/) And [docker-compose](https://github.com/docker/compose)

After installing docker, and run the command, the environment will be setup also.

```sh
$ cd hometest
$ docker-compose up -d --build
```

### Development

Since this is a home test only, so didn't include any development information.
If it is a real project, suggest Kubernetes + docker container with CI at GIT tag or comment.

#### Configuration

The server config file is at
```sh
server\src\main\resources\application.properties
```
Just control the output server port and db config.

### Feedback

 - The format at the Report Api seems has syntax problem `"sales" "2000.00",`
 - Payment Api request is `"MASTERCARD"`, but response points is `5`, if followed the rules, that should be `price * 0.03` = 3 
 - The Report Api didnt descript about sales, so didn't sure it requires price or the final price

License
----

MIT
