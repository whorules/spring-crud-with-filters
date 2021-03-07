# Spring CRUD

> Spring application with opportunity to make CRUD (Create, Read, Update, Delete) operations and filter results using Java Criteria API

### Installing

* Clone repository
* Set up JDK 11 
* Project uses lombok. You have to install lombok plugin and enable annotation processing: Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> click "Enable annotation processing"
* Create a database "crud" in PostgreSQL and set up login and password in src -> main -> resources -> "application.yml" file. At the same path you can configure server port in which server will work

### Building and Starting
* Make `clean` and then `package` Maven commands to build project
* Right click on main `CrudApplication` class > Run
* To see REST API of an application please go <host>:<port>/swagger-ui.html
* When you will start application, test data will already be in database

### Examples
This is the basic model of search request. No one filed is required
```
{
  "color": "BLUE",
  "endManufactureDate": "2015-12-31",
  "isAutomatic": true,
  "maxSpeed": 300,
  "maxWeight": 1900,
  "models": [
    "BMW",
    "AUDI"
  ],
  "startManufactureDate": "2012-03-08"
}
```
If you want to get cars with weight less than or equal 1900, you can send request like this:
```
{
  "maxWeight": 1900
}
```
If you also want to get cars with automatic transmission, you can add a new parameter to JSON:
```
{
  "maxWeight": 1900, 
  "isAutomatic": false
}
```