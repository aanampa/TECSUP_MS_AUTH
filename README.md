# TECSUP_MS_AUTH - Microservicio de Autenticación y Usuarios
Este proyecto es un microservicio RESTful desarrollado con Java Spring Boot encargado de la gestión de usuarios (CRUD) y autenticación. Está diseñado para ser desplegado fácilmente mediante Docker y utiliza PostgreSQL como motor de base de datos.

## Tecnologías
El proyecto utiliza el siguiente stack tecnológico:
* **Lenguaje:** Java 17+
* **Framework:** Spring Boot (Web, JPA, Validation)
* **Base de Datos:** PostgreSQL
* **Herramientas:** Maven, Docker & Docker Compose

## Instalación con Docker

1. Asegúrate de tener Docker instalado.
2. Clonar el repositorio
   ```bash
   git clone https://github.com/aanampa/TECSUP_MS_AUTH.git

   # ingresar a carpeta del proyecto
   cd TECSUP_MS_AUTH
   ```

4.  Ejecuta el siguiente comando en la raíz del proyecto:
    ```bash
    docker-compose up --build -d
    ```
5.  Verifica que los contenedores estén corriendo:
    ```bash
    docker ps
    ```
6.  Compilar aplicacion java
    ```bash
    ./mvnw clean install
    ```
7.  Ejecutar aplicacion java
    ```bash
    ./mvnw spring-boot:run
    ```
## Ejecución

* Listar usuarios:
GET http://localhost:8055/api/usuarios

* Registrar usuario:
POST http://localhost:8055/api/usuarios
```bash
{ 
  "login": "adminportal", 
  "password": "mipassword123",
  "nombreUsuario": "Administrador del Portal"
}
```
* Autenticar usuario:
POST http://localhost:8055/api/usuarios/login
```bash
{ 
  "login": "adminportal", 
  "password": "mipassword123"
}
```

