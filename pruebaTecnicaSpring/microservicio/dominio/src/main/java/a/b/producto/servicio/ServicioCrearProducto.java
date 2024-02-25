package a.b.producto.servicio;


import a.b.dominio.excepcion.ExcepcionDuplicidad;
import a.b.producto.modelo.entidad.Producto;
import a.b.producto.puerto.repositorio.RepositorioProducto;
import reactor.core.publisher.Mono;

public class ServicioCrearProducto {

    private static final String EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA = "El producto ya existe en el sistema";

    private final RepositorioProducto repositorioProducto;

    public ServicioCrearProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    public Mono<Long> ejecutar(Producto producto) {
        return this.validarExistenciaPrevia(producto)
                .flatMap(existe -> Boolean.TRUE.equals(existe) ?
                        Mono.error(new ExcepcionDuplicidad(EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA))
                        : Mono.just(producto))
                .flatMap(usr -> this.repositorioProducto.crear(producto));
    }

    private Mono<Boolean> validarExistenciaPrevia(Producto producto) {
        return this.repositorioProducto.existe(producto.getNombre());
    }

}
