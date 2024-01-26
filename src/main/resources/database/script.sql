
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
                                                                          ('Sergio', 'Jiménez', 'Alonso', 'Bilbao', 2),
                                                                          ('Elena', 'Ruiz', 'Fernández', 'Madrid', 1),
                                                                          ('Luis', 'Fernández', 'Gómez', 'Barcelona', 3),
                                                                          ('María', 'Fernández', 'Gómez', 'Madrid', 1),
                                                                          ('Javier', 'Ruiz', 'Santos', 'Barcelona', 2),
                                                                          ('Luisa', 'García', 'Fernández', 'Sevilla', 1),
                                                                          ('Pedro', 'Gómez', 'Martínez', 'Valencia', 3),
                                                                          ('Carmen', 'Martínez', 'López', 'Granada', 3),
                                                                          ('Pablo', 'Sánchez', 'Gómez', 'Barcelona', 2),
                                                                          ('Elena', 'Gómez', 'Martín', 'Sevilla', 2),
                                                                          ('Raúl', 'López', 'Martínez', 'Bilbao', 2),
                                                                          ('Isabel', 'González', 'Fernández', 'Madrid', 1);


-- Inserciones para la tabla comercial
INSERT INTO comercial (nombre, apellido1, apellido2, comision) VALUES
                                                                   ('Marta', 'Rodríguez', 'Díaz', 5.0),
                                                                   ('David', 'Hernández', 'Santos', 7.5),
                                                                   ('Sofía', 'Romero', 'Vázquez', 6.0),
                                                                   ('Antonio', 'Torres', 'Iglesias', 4.5),
                                                                   ('Maria', 'Gavilan', 'Vázquez', 6.0),
                                                                   ('Rafael', 'Campos', 'Iglesias', 4.5),
                                                                   ('Carolina', 'Negro', 'Castillo', 5.5),
                                                                   ('Javier', 'González', 'Martínez', 6.5),
                                                                   ('Isabel', 'Sánchez', 'García', 5.0),
                                                                   ('Alberto', 'Martínez', 'López', 7.0);


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
                                                                (320.20, '2024-01-14', 5, 5),
                                                                (520.75, '2024-01-15', 6, 6),
                                                                (420.30, '2024-01-16', 7, 7),
                                                                (710.50, '2024-01-17', 8, 8),
                                                                (280.90, '2024-01-18', 9, 9),
                                                                (390.80, '2024-01-27', 1, 1),
                                                                (160.45, '2024-01-28', 2, 2),
                                                                (520.20, '2024-01-29', 3, 3),
                                                                (175.75, '2024-01-30', 4, 4),
                                                                (450.60, '2024-01-31', 5, 5),
                                                                (310.90, '2024-02-01', 6, 6),
                                                                (600.30, '2024-02-02', 7, 7),
                                                                (225.10, '2024-02-03', 8, 8),
                                                                (520.80, '2024-02-04', 9, 9),
                                                                (225.10, '2024-02-03', 10,10),
                                                                (520.80, '2024-02-04', 10, 10),
                                                                (300.00, '2024-01-10', 1, 1),
                                                                (450.50, '2024-01-11', 3, 2),
                                                                (125.75, '2024-01-12', 4, 3),
                                                                (600.00, '2024-01-13', 4, 4),
                                                                (185.55, '2024-01-12', 4, 10),
                                                                (650.00, '2024-01-13', 10, 5),
                                                                (325.20, '2024-01-14', 5, 5),
                                                                (521.75, '2024-01-15', 8, 6),
                                                                (422.30, '2024-01-16', 7, 7),
                                                                (713.50, '2024-01-17', 8, 8),
                                                                (284.90, '2024-01-18', 1, 9),
                                                                (395.80, '2024-01-27', 1, 2),
                                                                (166.45, '2024-01-28', 2, 2),
                                                                (527.20, '2024-01-29', 5, 3),
                                                                (178.75, '2024-01-30', 7, 4),
                                                                (459.60, '2024-01-31', 5, 7),
                                                                (311.90, '2024-02-01', 6, 6),
                                                                (602.30, '2024-02-02', 9, 7),
                                                                (223.10, '2024-02-03', 10, 8),
                                                                (524.80, '2024-02-04', 9, 9),
                                                                (220.10, '2024-02-03', 10,3),
                                                                (522.80, '2024-02-04', 1, 10);

