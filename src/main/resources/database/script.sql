
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
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE,
                        FOREIGN KEY (id_comercial) REFERENCES comercial(id) ON DELETE CASCADE
);
/*BORRARLA DESPUES DEL EJERCICIO*/
CREATE TABLE fabricante (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100)

);

INSERT INTO fabricante (nombre) VALUES
                                      ('Juan'),
                                      ('Ana'),
                                      ('Carlos'),
                                      ('Laura'),
                                      ('Sergio');
/********************/

-- Inserciones para la tabla cliente
INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoria) VALUES
                                                                          ('Juan', 'Pérez', 'García', 'Madrid', 1),
                                                                          ('Ana', 'López', 'Fernández', 'Barcelona', 2),
                                                                          ('Carlos', 'Martín', 'Ruiz', 'Sevilla', 1),
                                                                          ('Laura', 'Gómez', 'Moreno', 'Valencia', 3),
                                                                          ('Paco', 'Lozano', 'Moreno', 'Granada', 3),
                                                                          ('Laura', 'López', 'Ruiz', 'Barcelona', 2),
                                                                          ('Carlos', 'García', 'Martín', 'Sevilla', 2),
                                                                          ('Sergio', 'Jiménez', 'Alonso', 'Bilbao', 2);

-- Inserciones para la tabla comercial
INSERT INTO comercial (nombre, apellido1, apellido2, comision) VALUES
                                                                   ('Marta', 'Rodríguez', 'Díaz', 5.0),
                                                                   ('David', 'Hernández', 'Santos', 7.5),
                                                                   ('Sofía', 'Romero', 'Vázquez', 6.0),
                                                                   ('Antonio', 'Torres', 'Iglesias', 4.5),
                                                                   ('Maria', 'Gavilan', 'Vázquez', 6.0),
                                                                   ('Rafael', 'Campos', 'Iglesias', 4.5),
                                                                   ('Carolina', 'Negro', 'Castillo', 5.5);

-- Inserciones para la tabla pedido
-- Nota: Se asume que los ids de cliente y comercial existen.
-- Ajusta los ids según los datos reales de tus tablas.
INSERT INTO pedido (total, fecha, id_cliente, id_comercial) VALUES
                                                                (300.00, '2024-01-10', 1, 1),
                                                                (450.50, '2024-01-11', 2, 2),
                                                                (125.75, '2024-01-12', 3, 3),
                                                                (600.00, '2024-01-13', 4, 4),
                                                                (185.55, '2024-01-12', 4, 3),
                                                                (650.00, '2024-01-13', 3, 4),
                                                                (320.20, '2024-01-14', 5, 5);

