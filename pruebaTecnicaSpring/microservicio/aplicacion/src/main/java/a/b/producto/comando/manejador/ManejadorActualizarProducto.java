package a.b.producto.comando.manejador;

import a.b.ComandoRespuesta;
import a.b.manejador.ManejadorComandoRespuesta;
import a.b.producto.comando.ComandoProducto;
import a.b.producto.comando.fabrica.FabricaProducto;
import a.b.producto.modelo.entidad.Producto;
import a.b.producto.servicio.ServicioActualizarProducto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ManejadorActualizarProducto implements ManejadorComandoRespuesta<ComandoProducto, Mono<ComandoRespuesta<Long>>> {

    private final FabricaProducto fabricaProducto;
    private final ServicioActualizarProducto servicioActualizarProducto;

    public ManejadorActualizarProducto(FabricaProducto fabricaProducto, ServicioActualizarProducto servicioActualizarProducto) {
        this.fabricaProducto = fabricaProducto;
        this.servicioActualizarProducto = servicioActualizarProducto;
    }

    @Override
    public Mono<ComandoRespuesta<Long>> ejecutar(ComandoProducto comandoProducto) {
        Producto producto = this.fabricaProducto.crear(comandoProducto);
        return this.servicioActualizarProducto.ejecutar(producto).map(ComandoRespuesta::new);
    }
}
