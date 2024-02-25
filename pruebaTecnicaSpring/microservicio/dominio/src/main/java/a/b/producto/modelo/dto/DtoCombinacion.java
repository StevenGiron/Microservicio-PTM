package a.b.producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class DtoCombinacion {
    private String producto1;
    private String producto2;
    private BigDecimal sumaPrecio;
}
