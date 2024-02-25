package a.b.producto.servicio;

import a.b.core.BasePrueba;
import a.b.dominio.excepcion.ExcepcionDuplicidad;
import a.b.producto.modelo.entidad.Producto;
import a.b.producto.puerto.repositorio.RepositorioProducto;
import a.b.producto.servicio.builder.ProductoTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicioActualizarProductoTest {

    private static final Long CANTIDAD_DE_REGISTROS_AFECTADOS = 1L;
    private static final String EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA = "El producto ya existe en el sistema";

    private RepositorioProducto repositorioProducto;
    private ServicioActualizarProducto servicioActualizarProducto;

    @BeforeEach
    void setUp(){
        repositorioProducto = mock(RepositorioProducto.class);
        servicioActualizarProducto = new ServicioActualizarProducto(repositorioProducto);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa de un producto")
    void deberiaValidarProductoExistenciaPrevia() {
        // Arrange
        Producto producto = new ProductoTestDataBuilder().conId(1).build();
        when(repositorioProducto.existeExcluyendoId(anyInt(), anyString())).thenReturn(Mono.just(true));
        // Act - Assert
        BasePrueba.assertReactiveThrows(
                () -> servicioActualizarProducto.ejecutar(producto),
                ExcepcionDuplicidad.class,
                EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA
        );
    }

    @Test
    @DisplayName("Deberia actualizar un producto y retornar la cantidad de registros afectados en la tabla")
    void deberiaActualizarUnProductoYRetornarLaCantidadDeRegistrosAfectadosTest() {
        // Arrange
        Producto producto = new ProductoTestDataBuilder().conId(1).build();
        when(repositorioProducto.existeExcluyendoId(Mockito.anyInt(), Mockito.anyString())).thenReturn(Mono.just(false));
        when(repositorioProducto.actualizar(producto)).thenReturn(Mono.just(CANTIDAD_DE_REGISTROS_AFECTADOS));
        // Act
        Mono<Long> filasAfectadas = servicioActualizarProducto.ejecutar(producto);
        // Assert
        StepVerifier.create(filasAfectadas)
                .expectNext(CANTIDAD_DE_REGISTROS_AFECTADOS)
                .verifyComplete();
    }

}
