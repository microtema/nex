# URL Shortening Service
URL Shortening Service (Server)

### REST API
URL

@GET    /rest/url/:short-url will redirect to mapped URL

@POST   /rest/url/ @BODY{URL} will convert to tiny URL and persist to DB
@GET    /rest/url/long/:short-url will return mapped URL

#URL Shortening Service

### Install

* **Java**  ( SDK 1.8)
* **Apache Maven**  ( 3.2.3 )
* **SQL Server**  ( MS SQL Express 2012)
* **GIT**  ( 2.7.1)
* **SourceTree**  ( 1.7.0)
* **IDE**  ( 15.0.3)


### Development

* **mvn clean test**  ( run JUnit test)
* **mvn clean verify**  ( run JUnit and IT test)
* **mvn clean install**  ( compile, test, build and install to maven repository)


We'll be building a simple but realistic URL Shortening Service that you can convert URL to tiny hash offered by REST API.

We'll provide:

* A ORM of URLEntry
* A form to convert URL to six Character's
* JPA Hooks to set timestamp
* Junit Test and IT
* CDD and TDD

It'll also have a few neat features:

* **Live redirect:** Tiny Url with be converted and redirect to real URL.
* **Data converting, validating and persisting:** Convert URL to tiny Hash
* **Data reconverting** Convert tiny Hash to real URL


### Running a server

In order to start this tutorial, we're going to require a running Wildfly server. This will serve purely as an WebService (REST API) endpoint which we'll use for converting and persisting Urls's through REST endpoint.
