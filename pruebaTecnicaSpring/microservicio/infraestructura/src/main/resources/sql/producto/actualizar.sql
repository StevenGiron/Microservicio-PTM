UPDATE productos
SET nombre = :nombre,
	descripcion = :descripcion,
	precio = :precio,
	stock = :stock
WHERE id = :id