package a.b.producto.puerto.repositorio;

import a.b.producto.modelo.entidad.Producto;
import reactor.core.publisher.Mono;

public interface RepositorioProducto {
    /**
     * Permite crear un producto
     *
     * @param producto
     * @return el id generado
     */
    Mono<Long> crear(Producto producto);

    /**
     * Permite actualizar un producto
     *
     * @param producto
     */
    Mono<Long> actualizar(Producto producto);

    /**
     * Permite eliminar un producto
     *
     * @param id
     */
    Mono<Void> eliminar(Integer id);

    /**
     * Permite validar si existe un producto con un nombre
     *
     * @param nombre
     * @return si existe o no
     */
    Mono<Boolean> existe(String nombre);

    /**
     * Permite validar si existe un producto con un nombre excluyendo un id
     *
     * @param nombre
     * @return si existe o no
     */
    Mono<Boolean> existeExcluyendoId(Integer id, String nombre);

}
