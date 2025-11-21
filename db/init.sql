-- Crear la tabla
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nombre_usuario VARCHAR(100),
    estado VARCHAR(1),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Datos de prueba
INSERT INTO usuario (login, password, nombre_usuario, estado) 
VALUES ('admin', '123456', 'Administrador del Sistema', 'A');

INSERT INTO usuario (login, password, nombre_usuario, estado) 
VALUES ('jdoe', 'secret', 'John Doe', 'A');