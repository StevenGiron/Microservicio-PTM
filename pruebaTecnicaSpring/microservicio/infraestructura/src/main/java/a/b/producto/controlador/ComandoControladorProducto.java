package a.b.producto.controlador;

import a.b.ComandoRespuesta;
import a.b.producto.comando.ComandoProducto;
import a.b.producto.comando.manejador.ManejadorActualizarProducto;
import a.b.producto.comando.manejador.ManejadorCrearProducto;
import a.b.producto.comando.manejador.ManejadorEliminarProducto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
@Api(tags = { "Controlador comando producto"})
@CrossOrigin(origins = "*")
public class ComandoControladorProducto {

    private final ManejadorCrearProducto manejadorCrearProducto;
	private final ManejadorEliminarProducto manejadorEliminarProducto;
	private final ManejadorActualizarProducto manejadorActualizarProducto;

    public ComandoControladorProducto(ManejadorCrearProducto manejadorCrearProducto,
									  ManejadorEliminarProducto manejadorEliminarProducto,
									  ManejadorActualizarProducto manejadorActualizarProducto) {
        this.manejadorCrearProducto = manejadorCrearProducto;
		this.manejadorEliminarProducto = manejadorEliminarProducto;
		this.manejadorActualizarProducto = manejadorActualizarProducto;
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Crear Producto")
	@ResponseStatus(HttpStatus.CREATED)
    public Mono<ComandoRespuesta<Long>> crear(@RequestBody ComandoProducto comandoProducto) {
        return manejadorCrearProducto.ejecutar(comandoProducto);
    }

    @DeleteMapping(value="/eliminar/{id}")
	@ApiOperation("Eliminar Producto")
	public Mono<Void> eliminar(@PathVariable (value = "id") Integer id) {
		return manejadorEliminarProducto.ejecutar(id);
	}

	@PatchMapping(value="/editar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Actualizar producto")
	public Mono<ComandoRespuesta<Long>> actualizar(@RequestBody ComandoProducto comandoProducto, @PathVariable(value = "id") Integer id) {
		comandoProducto.setId(id);
		return manejadorActualizarProducto.ejecutar(comandoProducto);
	}

}