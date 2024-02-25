DROP TABLE IF EXISTS productos;
DROP VIEW IF EXISTS vista_inventario;

CREATE TABLE productos (
   id INT AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR(255),
   descripcion TEXT,
   precio DECIMAL(10, 2),
   stock INT
);

CREATE VIEW vista_inventario AS
SELECT SUM(stock * precio) AS total_inventario
FROM productos;