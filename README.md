# Emazon Stock Microservice

This is a stock microservice for an e-commerce platform developed in pragma bootcamp. It is built using SpringBoot, and MySQL.

You can create a docker container with a MySQL database using the following command:

docker run --name pragma_bootcamp_database -e MYSQL_ROOT_PASSWORD=rootpass -e MYSQL_DATABASE=stock -e MYSQL_USER=user -e MYSQL_PASSWORD=userpass -p 3306:3306 -d mysql:8.0

and run the next sql script to create the schema and table:

src/main/resources/sql/schema.ddl