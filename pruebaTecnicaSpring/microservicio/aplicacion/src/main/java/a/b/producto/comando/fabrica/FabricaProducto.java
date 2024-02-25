package a.b.producto.comando.fabrica;

import a.b.producto.comando.ComandoProducto;
import a.b.producto.modelo.entidad.Producto;
import org.springframework.stereotype.Component;

@Component
public class FabricaProducto {

    public Producto crear(ComandoProducto comandoProducto) {
        return new Producto(
                comandoProducto.getId(),
                comandoProducto.getNombre(),
                comandoProducto.getDescripcion(),
                comandoProducto.getPrecio(),
                comandoProducto.getStock()
        );
    }

}
