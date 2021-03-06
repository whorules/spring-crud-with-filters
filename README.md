# Medical Clinic Backend

> Spring application with opportunity to make CRUD (Create, Read, Update, Delete) operation and filter results using Java Criteria API

### Installing

* Clone repository
* Set up JDK 11 
* Project uses lombok. You have to install lombok plugin and enable annotation processing: Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> click "Enable annotation processing"
* Create database "crud" in PostgreSQL and set up login and password in src -> main -> resources -> "application.yml" file

### Building and Starting
* Make `clean` and then `package` to build project
* Rigth click on main `CrudApplication` class
* To see REST API of an application please go <host>:<port>/swagger-ui.html

### Examples
