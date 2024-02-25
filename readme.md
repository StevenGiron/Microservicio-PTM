Instrucciones para ejecutar el backend

Tener instalado:

- Java 17.0.10
- Gradle 8.5

Instrucciones crear una instancia de MySQL

Tener instalado 

- Docker
- mysql-server

Ejecutar

- docker pull mysql:latest
- docker run --name mi-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 13306:3306 mysql:latest
- docker exec -it mi-mysql mysql -uroot -p
  - Enter password: root
- create user 'steven' identified by 'root';
- grant all privileges on *.* to 'steven'@'%';

Instrucciones para ejecutar el frontend

Tener instalado:

- Node 20.9.0

Ejecutar

- npm install
- npm run dev

Comparto una documentacion en Postman del backend

- https://documenter.getpostman.com/view/18898913/2sA2rDvLK9#46411cbc-5237-4f38-a2a3-3e3331a9613b

Comparto un video demostrativo de la aplicacion

- https://youtu.be/o62bAQ6KcME