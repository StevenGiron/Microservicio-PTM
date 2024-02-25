package a.b.producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class DtoTotalInventario {
    private BigDecimal totalInventario;
}
