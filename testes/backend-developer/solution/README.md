# Places CRUD API

This is a REST API model that provides operations for managing places using some fields for that.

## Requirements

This project was built to run with a associated MySQL database, but also can run with an H2 database. To configure the MySQL connection details, you can use the environment variables:
* **MYSQL_HOST:** The location of MySQL server (defaults to *localhost*)
* **MYSQL_PORT:** The port used by MySQL server (defaults to *3306*)
* **MYSQL_DATABASE:** The database name (defaults to *places*)
* **MYSQL_USERNAME:** The username to use (defaults to *springuser*)
* **MYSQL_PASSWORD:** The password to use (defaults to *Spring123.*)

Or, if you want, you can set it directly via line command
* **spring.datasource.url** - to full jdbc string
* **spring.datasource.username** - for username
* **spring.datasource.password** - for password
* **spring.datasource.driverClassName** - to change the driver type (use *org.h2.Driver* for H2 DB)

An example of h2 configuration values can be found at test's `application.yml` file

You also can use any other line command features supported by SpringBoot.

## API endpoints information

After launching the application, the full documentation will be available at `/swagger-ui.html` URI path.