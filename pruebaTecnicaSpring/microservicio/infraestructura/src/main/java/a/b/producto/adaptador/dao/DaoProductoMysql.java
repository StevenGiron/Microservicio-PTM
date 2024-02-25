package a.b.producto.adaptador.dao;

import a.b.infraestructura.r2dbc.CustomRRDBCTemplate;
import a.b.infraestructura.r2dbc.sqlstatement.SqlStatement;
import a.b.producto.adaptador.transformador.TransformadorInventario;
import a.b.producto.adaptador.transformador.TransformadorProducto;
import a.b.producto.modelo.dto.DtoProducto;
import a.b.producto.modelo.dto.DtoTotalInventario;
import a.b.producto.puerto.dao.DaoProducto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DaoProductoMysql implements DaoProducto {

    private final CustomRRDBCTemplate customRRDBCTemplate;
    private final TransformadorProducto transformadorProducto;
    private final TransformadorInventario transformadorInventario;

    public DaoProductoMysql(CustomRRDBCTemplate customRRDBCTemplate,
                            TransformadorProducto transformadorProducto,
                            TransformadorInventario transformadorInventario) {
        this.customRRDBCTemplate = customRRDBCTemplate;
        this.transformadorProducto = transformadorProducto;
        this.transformadorInventario = transformadorInventario;
    }

    @SqlStatement(namespace = "producto", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "producto", value = "totalInventario")
    private static String sqlTotalInventario;

    @Override
    public Flux<DtoProducto> listar() {
        return customRRDBCTemplate.getDatabaseClient().sql(sqlListar)
                .map(transformadorProducto.execute()).all();
    }

    @Override
    public Mono<DtoTotalInventario> totalInventario() {
        return customRRDBCTemplate.getDatabaseClient().sql(sqlTotalInventario)
                .map(transformadorInventario.execute()).one();
    }

    @Override
    public Flux<List<Object>> combinacionesProducto(Flux<DtoProducto> productosFlux, BigDecimal limite) {
        return productosFlux.collectList().flatMapMany(productos -> {

            List<Tuple2<DtoProducto, DtoProducto>> combinacionesDos = generarCombinaciones(productos, 2);
            List<Tuple2<DtoProducto, DtoProducto>> combinacionesTres = generarCombinaciones(productos, 3);
            List<Tuple2<DtoProducto, DtoProducto>> todasLasCombinaciones = new ArrayList<>(combinacionesDos);
            todasLasCombinaciones.addAll(combinacionesTres);
            return Flux.fromIterable(todasLasCombinaciones)
                    .filter(combinacion -> {
                        BigDecimal sumaPrecio = BigDecimal.ZERO;
                        for (Object producto : combinacion) {
                            sumaPrecio = sumaPrecio.add(((DtoProducto) producto).getPrecio());
                        }
                        return sumaPrecio.compareTo(limite) <= 0;
                    })
                    .map(combinacion -> {
                        List<Object> resultado = new ArrayList<>();
                        BigDecimal sumaPrecio = BigDecimal.ZERO;
                        for (Object producto : combinacion) {
                            resultado.add(((DtoProducto) producto).getNombre());
                            sumaPrecio = sumaPrecio.add(((DtoProducto) producto).getPrecio());
                        }
                        resultado.add(sumaPrecio);
                        return resultado;
                    })
                    .sort(new Comparator<List<Object>>() {
                        @Override
                        public int compare(List<Object> o1, List<Object> o2) {
                            BigDecimal sumaPrecio1 = (BigDecimal) o1.get(o1.size() - 1);
                            BigDecimal sumaPrecio2 = (BigDecimal) o2.get(o2.size() - 1);
                            return sumaPrecio2.compareTo(sumaPrecio1); // Orden descendente
                        }
                    }).take(5);
        });
    }


    private List<Tuple2<DtoProducto, DtoProducto>> generarCombinaciones(List<DtoProducto> productos, int cantidadProductos) {
        List<Tuple2<DtoProducto, DtoProducto>> combinaciones = new ArrayList<>();
        IntStream.range(0, productos.size()).forEach(i -> {
            IntStream.range(i + 1, productos.size()).forEach(j -> {
                if (cantidadProductos == 2) {
                    combinaciones.add(Tuples.of(productos.get(i), productos.get(j)));
                } else if (cantidadProductos == 3) {
                    IntStream.range(j + 1, productos.size()).forEach(k -> {
                        combinaciones.add(Tuples.of(productos.get(i), productos.get(j), productos.get(k)));
                    });
                }
            });
        });
        return combinaciones;
    }



}
