package a.b.producto.controlador;

import a.b.ApplicationMock;
import a.b.producto.comando.ComandoProducto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {ConsultaControladorProducto.class})
@ContextConfiguration(classes = {ApplicationMock.class})
class ConsultaControladorProductoTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Deberia obtener todos los productos registrados")
    void deberiaObtenerTodosLosProductosRegistrados() {
        // Arrange
        // Act - Assert
        webTestClient.get().uri("/productos/listar")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ComandoProducto.class)
                .consumeWith(clientResponse -> {
                    List<ComandoProducto> comandoProductos = clientResponse.getResponseBody();
                    assertThat(comandoProductos, hasSize(4));
                    assertThat(comandoProductos, contains(hasProperty("nombre", is("Tenis de Running"))));
                    assertThat(comandoProductos, contains(hasProperty("descripcion", is("Tenis de running para hombre 10k"))));
                    assertThat(comandoProductos, contains(hasProperty("precio", is(100))));
                    assertThat(comandoProductos, contains(hasProperty("stock", is(8))));
                });
    }

}
