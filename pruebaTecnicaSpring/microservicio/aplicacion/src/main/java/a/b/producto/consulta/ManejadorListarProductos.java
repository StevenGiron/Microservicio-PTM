package a.b.producto.consulta;

import a.b.producto.modelo.dto.DtoProducto;
import a.b.producto.puerto.dao.DaoProducto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ManejadorListarProductos {

    private final DaoProducto daoProducto;

    public ManejadorListarProductos(DaoProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public Flux<DtoProducto> ejecutar() {
        return this.daoProducto.listar();
    }
}
