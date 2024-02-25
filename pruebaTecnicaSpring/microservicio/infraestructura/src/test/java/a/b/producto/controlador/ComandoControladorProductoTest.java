package a.b.producto.controlador;

import a.b.ApplicationMock;
import a.b.ComandoRespuesta;
import a.b.producto.comando.ComandoProducto;
import a.b.producto.servicio.builder.ComandoProductoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.MediaType;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {ComandoControladorProducto.class})
@ContextConfiguration(classes = {ApplicationMock.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorProductoTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    @Test
    @DisplayName("Deberia crear un producto")
    void deberiaCrearUnProducto() {
        // Arrange
        ComandoProducto producto = new ComandoProductoTestDataBuilder().build();

        // Act - Assert
        webTestClient.post().uri("/productos/crear")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(producto)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.valor").isEqualTo(2);
    }

    @Test
    @DisplayName("Deberia actualizar un producto")
    void deberiaActualizarUnProducto() {
        // Arrange
        Long id = 1L;
        ComandoProducto producto = new ComandoProductoTestDataBuilder()
                .conNombre("Tenis de Running")
                .conDescripcion("Tenis de running para hombre 10k")
                .conPrecio(new BigDecimal(105))
                .conStock(8)
                .build();

        // Act - Assert
        webTestClient.patch().uri("/productos/editar/{id}", id)
                .bodyValue(producto)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ComandoRespuesta.class)
                .consumeWith(response -> {
                    ComandoRespuesta<?> comandoRespuesta = response.getResponseBody();
                    Assertions.assertNotNull(comandoRespuesta);
                    assertThat((Integer) comandoRespuesta.getValor(), greaterThan(0));
                });
    }

    @Test
    @DisplayName("Deberia eliminar un producto")
    void deberiaEliminarUnProducto() {
        // Arrange
        Long id = 1L;
        // Act - Assert
        webTestClient.delete().uri("/productos/eliminar/{id}", id)
                .exchange()
                .expectStatus().isOk();

        Mono<Map<String, Object>> monoProducto = databaseClient.sql("SELECT * FROM productos WHERE id = " + id).fetch().first();
        StepVerifier.create(monoProducto).verifyComplete();
    }

}
