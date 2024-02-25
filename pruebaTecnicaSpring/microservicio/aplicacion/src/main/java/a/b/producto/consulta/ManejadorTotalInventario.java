package a.b.producto.consulta;

import a.b.producto.modelo.dto.DtoTotalInventario;
import a.b.producto.puerto.dao.DaoProducto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ManejadorTotalInventario {

    private final DaoProducto daoProducto;

    public ManejadorTotalInventario(DaoProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public Mono<DtoTotalInventario> ejecutar(){
        return this.daoProducto.totalInventario();
    }
}
