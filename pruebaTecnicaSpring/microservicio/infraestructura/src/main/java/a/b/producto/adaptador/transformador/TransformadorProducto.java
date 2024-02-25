package a.b.producto.adaptador.transformador;

import a.b.infraestructura.r2dbc.MapperResult;
import a.b.producto.modelo.dto.DtoProducto;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Component
public class TransformadorProducto implements MapperResult<DtoProducto> {

    @Override
    public BiFunction<Row, RowMetadata, DtoProducto> execute() {
        return (row, rowMetaData) -> DtoProducto.builder()
                .id(row.get("id", Integer.class))
                .nombre(row.get("nombre", String.class))
                .descripcion(row.get("descripcion", String.class))
                .precio(row.get("precio", BigDecimal.class))
                .stock(row.get("stock", Integer.class))
                .build();
    }
}
