package a.b.producto.consulta;

import a.b.producto.modelo.dto.DtoCombinacion;
import a.b.producto.modelo.dto.DtoProducto;
import a.b.producto.puerto.dao.DaoProducto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ManejadorCombinacionesProducto {
    private final DaoProducto daoProducto;

    public ManejadorCombinacionesProducto(DaoProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public Flux<List<Object>> ejecutar(Flux<DtoProducto> productosFlux, BigDecimal limite) {
        return this.daoProducto.combinacionesProducto(productosFlux, limite);
    }
}
