package a.b.producto.servicio;

import a.b.core.BasePrueba;
import a.b.dominio.excepcion.ExcepcionDuplicidad;
import a.b.dominio.excepcion.ExcepcionLongitudValor;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicioCrearProductoTest {

    private static final Long ID_DEL_PRODUCTO_CREADO = 1L;
    private static final String EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA = "El producto ya existe en el sistema";
    private static final String LA_DESCRIPCION_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La clave debe tener una longitud mayor o igual a 5";

    private RepositorioProducto repositorioProducto;
    private ServicioCrearProducto servicioCrearProducto;

    @BeforeEach
    void setUp() {
        repositorioProducto = mock(RepositorioProducto.class);
        servicioCrearProducto = new ServicioCrearProducto(repositorioProducto);
    }

    @Test
    @DisplayName("Deberia validar que la longitud de la descripcion no sea menor a 5 caracteres")
    void deberiValidarDescripcionLongitudMenor4() {
        // Arrange
        ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder().conNombre("124");
        // Act - Assert
        BasePrueba.assertThrows(
                productoTestDataBuilder::build,
                ExcepcionLongitudValor.class,
                LA_DESCRIPCION_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa de un producto")
    void deberiaValidarProductoExistenciaPrevia() {
        // Arrange
        Producto producto = new ProductoTestDataBuilder().build();
        when(repositorioProducto.existe(Mockito.anyString())).thenReturn(Mono.just(true));
        // Act - Assert
        BasePrueba.assertReactiveThrows(() -> servicioCrearProducto.ejecutar(producto),
                ExcepcionDuplicidad.class,
                EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA
        );
    }

    @Test
    @DisplayName("Deberia crear un producto y obtener su ID")
    void deberiaCrearUnProductoYObtenerSuIdTest() {
        // Arrange
        Producto producto = new ProductoTestDataBuilder().build();
        when(repositorioProducto.existe(Mockito.anyString())).thenReturn(Mono.just(false));
        when(repositorioProducto.crear(producto)).thenReturn(Mono.just(ID_DEL_PRODUCTO_CREADO));
        // Act
        Mono<Long> idGenerado = servicioCrearProducto.ejecutar(producto);
        // Assert
        StepVerifier.create(idGenerado)
                .expectNext(ID_DEL_PRODUCTO_CREADO)
                .verifyComplete();
    }

}
