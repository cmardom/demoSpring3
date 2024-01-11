
DROP DATABASE IF EXISTS ventas;
CREATE DATABASE ventas;
USE ventas;

-- Creación de la tabla cliente
CREATE TABLE cliente (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100),
                         apellido1 VARCHAR(100),
                         apellido2 VARCHAR(100),
                         ciudad VARCHAR(100),
                         categoria INT
);

-- Creación de la tabla comercial
CREATE TABLE comercial (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100),
                           apellido1 VARCHAR(100),
                           apellido2 VARCHAR(100),
                           comision FLOAT
);

-- Creación de la tabla pedido
CREATE TABLE pedido (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        total DOUBLE,
                        fecha DATE,
                        id_cliente INT,
                        id_comercial INT,
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id),
                        FOREIGN KEY (id_comercial) REFERENCES comercial(id)
);
/*BORRARLA DESPUES DEL EJERCICIO*/
CREATE TABLE fabricante (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100)

);