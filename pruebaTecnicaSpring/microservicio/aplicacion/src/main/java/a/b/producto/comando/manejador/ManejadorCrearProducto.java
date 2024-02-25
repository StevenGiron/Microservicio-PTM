package a.b.producto.comando.manejador;

import a.b.ComandoRespuesta;
import a.b.manejador.ManejadorComandoRespuesta;
import a.b.producto.comando.ComandoProducto;
import a.b.producto.comando.fabrica.FabricaProducto;
import a.b.producto.modelo.entidad.Producto;
import a.b.producto.servicio.ServicioCrearProducto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ManejadorCrearProducto implements ManejadorComandoRespuesta<ComandoProducto, Mono<ComandoRespuesta<Long>>> {

    private final FabricaProducto fabricaProducto;
    private final ServicioCrearProducto servicioCrearProducto;

    public ManejadorCrearProducto(FabricaProducto fabricaProducto, ServicioCrearProducto servicioCrearProducto) {
        this.fabricaProducto = fabricaProducto;
        this.servicioCrearProducto = servicioCrearProducto;
    }

    @Override
    public Mono<ComandoRespuesta<Long>> ejecutar(ComandoProducto comandoProducto) {
        Producto producto = this.fabricaProducto.crear(comandoProducto);
        return this.servicioCrearProducto.ejecutar(producto).map(ComandoRespuesta::new);
    }

}
