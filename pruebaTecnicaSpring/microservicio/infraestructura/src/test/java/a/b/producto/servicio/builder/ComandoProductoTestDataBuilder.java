package a.b.producto.servicio.builder;

import a.b.producto.comando.ComandoProducto;

import java.math.BigDecimal;

public class ComandoProductoTestDataBuilder {

    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;

    public ComandoProductoTestDataBuilder() {
        id = 1;
        nombre = "Tenis de Running";
        descripcion = "Tenis de running para hombre 10k";
        precio = new BigDecimal(105);
        stock = 8;
    }

    public ComandoProductoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoProductoTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }
    public ComandoProductoTestDataBuilder conPrecio(BigDecimal precio) {
        this.precio = precio;
        return this;
    }
    public ComandoProductoTestDataBuilder conStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public ComandoProducto build() {
        return new ComandoProducto(id, nombre, descripcion, precio, stock);
    }

}
