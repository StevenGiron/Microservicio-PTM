package a.b.producto.adaptador.transformador;

import a.b.infraestructura.r2dbc.MapperResult;
import a.b.producto.modelo.dto.DtoTotalInventario;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Component
public class TransformadorInventario implements MapperResult<DtoTotalInventario> {
    @Override
    public BiFunction<Row, RowMetadata, DtoTotalInventario> execute() {
        return ((row, rowMetadata) -> DtoTotalInventario.builder()
                .totalInventario(row.get("total_inventario", BigDecimal.class))
                .build());
    }
}
