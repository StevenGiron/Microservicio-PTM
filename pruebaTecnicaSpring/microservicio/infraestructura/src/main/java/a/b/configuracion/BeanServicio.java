package a.b.configuracion;

import a.b.producto.puerto.repositorio.RepositorioProducto;
import a.b.producto.servicio.ServicioActualizarProducto;
import a.b.producto.servicio.ServicioCrearProducto;
import a.b.producto.servicio.ServicioEliminarProducto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearProducto servicioCrearProducto(RepositorioProducto repositorioProducto) {
        return new ServicioCrearProducto(repositorioProducto);
    }

    @Bean
    public ServicioEliminarProducto servicioEliminarProducto(RepositorioProducto repositorioProducto) {
        return new ServicioEliminarProducto(repositorioProducto);
    }

    @Bean
    public ServicioActualizarProducto servicioActualizarProducto(RepositorioProducto repositorioProducto) {
        return new ServicioActualizarProducto(repositorioProducto);
    }
}
