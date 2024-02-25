package a.b.producto.adaptador.repositorio;

import a.b.infraestructura.r2dbc.CustomRRDBCTemplate;
import a.b.infraestructura.r2dbc.sqlstatement.SqlStatement;
import a.b.producto.modelo.entidad.Producto;
import a.b.producto.puerto.repositorio.RepositorioProducto;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Repository
class RepositorioProductoMysql implements RepositorioProducto {

    private final CustomRRDBCTemplate customRRDBCTemplate;

    @SqlStatement(namespace = "producto", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "producto", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "producto", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "producto", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "producto", value = "existeExcluyendoId")
    private static String sqlExisteExcluyendoId;

    public RepositorioProductoMysql(CustomRRDBCTemplate customRRDBCTemplate) {
        this.customRRDBCTemplate = customRRDBCTemplate;
    }

    @Override
    public Mono<Long> crear(Producto producto) {
        return this.customRRDBCTemplate.create(producto, sqlCrear);
    }

    @Override
    public Mono<Void> eliminar(Integer id) {
        Map<String, Object> paramSource = new HashMap<>();
        paramSource.put("id", id);
        return this.customRRDBCTemplate.query(paramSource, sqlEliminar);
    }

    @Override
    public Mono<Boolean> existe(String nombre) {
        Map<String, Object> paramSource = new HashMap<>();
        paramSource.put("nombre", nombre);

        Function<Row, Boolean> mapper = row -> {
            Long value = row.get("CNT", Long.class);
            return Objects.nonNull(value) && value > 0;
        };

        return this.customRRDBCTemplate.query(paramSource, mapper, sqlExiste);
    }

    @Override
    public Mono<Long> actualizar(Producto producto) {
        return this.customRRDBCTemplate.update(producto, sqlActualizar);
    }

    @Override
    public Mono<Boolean> existeExcluyendoId(Integer id, String nombre) {
        Map<String, Object> paramSource = new HashMap<>();
        paramSource.put("id", id);
        paramSource.put("nombre", nombre);

        Function<Row, Boolean> mapper = row -> {
            Long value = row.get("CNT", Long.class);
            return Objects.nonNull(value) && value > 0;
        };

        return this.customRRDBCTemplate.query(paramSource, mapper, sqlExisteExcluyendoId);
    }

}
