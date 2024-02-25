package a.b.producto.controlador;


import a.b.producto.consulta.ManejadorCombinacionesProducto;
import a.b.producto.consulta.ManejadorListarProductos;
import a.b.producto.consulta.ManejadorTotalInventario;
import a.b.producto.modelo.dto.DtoCombinacion;
import a.b.producto.modelo.dto.DtoProducto;
import a.b.producto.modelo.dto.DtoTotalInventario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/productos")
@Api(tags = {"Controlador consulta producto"})
@CrossOrigin(origins = "*")
public class ConsultaControladorProducto {

    private final ManejadorListarProductos manejadorListarProductos;
    private final ManejadorTotalInventario manejadorTotalInventario;
    private final ManejadorCombinacionesProducto manejadorCombinacionesProducto;



    public ConsultaControladorProducto(ManejadorListarProductos manejadorListarProductos,
                                       ManejadorTotalInventario manejadorTotalInventario,
                                       ManejadorCombinacionesProducto manejadorCombinacionesProducto) {
        this.manejadorListarProductos = manejadorListarProductos;
        this.manejadorTotalInventario = manejadorTotalInventario;
        this.manejadorCombinacionesProducto = manejadorCombinacionesProducto;
    }

    @GetMapping(value = "/listar")
    @ApiOperation("Listar productos")
    public Flux<DtoProducto> listar() {
        return this.manejadorListarProductos.ejecutar();
    }

    @GetMapping(value = "/totalInventario")
    @ApiOperation("Obtiene Total Inventario")
    public Mono<DtoTotalInventario> totalInventario() {
        return this.manejadorTotalInventario.ejecutar();
    }

    @GetMapping(value = "/combinaciones")
    @ApiOperation("Obtiene Total Inventario")
    public Flux<List<Object>> listarCombinaciones(@RequestParam(value = "limite", defaultValue = "0") BigDecimal limite) {
        Flux<DtoProducto> productosFlux = this.manejadorListarProductos.ejecutar();
        return this.manejadorCombinacionesProducto.ejecutar(productosFlux, limite);
    }
}
