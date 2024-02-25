package a.b.producto.modelo.entidad;


import lombok.Data;

import java.math.BigDecimal;

import static a.b.dominio.ValidadorArgumento.validarLongitud;
import static a.b.dominio.ValidadorArgumento.validarObligatorio;

@Data
public class Producto {

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_PRODUCTO = "Se debe ingresar el nombre del producto";
    private static final String SE_DEBE_INGRESAR_LA_DESCRIPCION = "Se debe ingresar la descripcion del producto";
    private static final String LA_DESCRIPCION_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La descripcion debe tener una longitud mayor o igual a %s";
    private static final String SE_DEBE_INGRESAR_EL_PRECIO_DEL_PRODUCTO = "Se debe ingresar el precio del producto";
    private static final String SE_DEBE_INGRESAR_EL_STOCK_DEL_PRODUCTO = "Se debe ingresar el stock del producto";

    private static final int LONGITUD_MINIMA_DESCRIPCION = 5;

    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;

    public Producto(Integer id, String nombre, String descripcion, BigDecimal precio, Integer stock) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_PRODUCTO);
        validarObligatorio(descripcion, SE_DEBE_INGRESAR_LA_DESCRIPCION);
        validarLongitud(descripcion, LONGITUD_MINIMA_DESCRIPCION, String.format(LA_DESCRIPCION_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A, LONGITUD_MINIMA_DESCRIPCION));
        validarObligatorio(precio, SE_DEBE_INGRESAR_EL_PRECIO_DEL_PRODUCTO);
        validarObligatorio(stock, SE_DEBE_INGRESAR_EL_STOCK_DEL_PRODUCTO);

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
}
