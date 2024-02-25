package a.b.producto.puerto.dao;

import a.b.producto.modelo.dto.DtoProducto;
import a.b.producto.modelo.dto.DtoTotalInventario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;


public interface DaoProducto {

    /**
     * Permite listar productos
     * @return los productos
     */
    Flux<DtoProducto> listar();

    /**
     * Permite listar productos
     * @return los productos
     */
    Mono<DtoTotalInventario> totalInventario();

    Flux<List<Object>> combinacionesProducto(Flux<DtoProducto> productosFlux, BigDecimal limite);


}
