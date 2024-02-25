package a.b.producto.comando.manejador;

import a.b.producto.servicio.ServicioEliminarProducto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class ManejadorEliminarProducto {

    private final ServicioEliminarProducto servicioEliminarProducto;

    public ManejadorEliminarProducto(ServicioEliminarProducto servicioEliminarProducto) {
        this.servicioEliminarProducto = servicioEliminarProducto;
    }

    public Mono<Void> ejecutar(Integer idProducto) {
        return this.servicioEliminarProducto.ejecutar(idProducto);
    }

}
