package a.b.producto.servicio;


import a.b.producto.puerto.repositorio.RepositorioProducto;
import reactor.core.publisher.Mono;

public class ServicioEliminarProducto {

    private final RepositorioProducto repositorioProducto;

    public ServicioEliminarProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    public Mono<Void> ejecutar(Integer id) {
        return this.repositorioProducto.eliminar(id);
    }
}
