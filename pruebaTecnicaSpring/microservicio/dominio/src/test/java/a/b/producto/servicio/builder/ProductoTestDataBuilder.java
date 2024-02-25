package a.b.producto.servicio.builder;

import a.b.producto.modelo.entidad.Producto;

import java.math.BigDecimal;

public class ProductoTestDataBuilder {

    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;

    public ProductoTestDataBuilder() {
        nombre = "Tennis";
        descripcion = "Tennis para correr";
        precio = new BigDecimal(150000);
        stock = 10;

    }

    public ProductoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoTestDataBuilder conId(Integer id) {
        this.id = id;
        return this;
    }

    public Producto build() {
        return new Producto(id, nombre, descripcion, precio, stock);
    }

}
