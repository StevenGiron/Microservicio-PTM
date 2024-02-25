import { toast } from "react-toastify"
import { Producto } from "../../../../feature/Producto/models/Producto"
import { axiosIntance } from "../../../config/AxiosConfig"
import { AGREGAR_PRODUCTO, ELIMINAR_PRODUCTO, FAIL_REQUEST, LISTAR_COMBINACIONES_PRODUCTOS, LISTAR_PRODUCTOS, MAKE_REQUEST, OBTENER_PRODUCTO, OBTENER_TOTAL_INVENTARIO } from "./TiposAcciones"
import { TotalInventario } from "../../../../feature/Producto/models/TotalInventario"

export const makeRequest = () => {
    return {
        type: MAKE_REQUEST
    }
}

export const failRequest = (err: Error) => {
    return {
        type: FAIL_REQUEST,
        payload: err
    }
}

export const listarProductos = (productos: Producto[]) => {
    
    return {
        type: LISTAR_PRODUCTOS,
        payload: productos
    }
}

export const eliminarProducto = () => {
    return {
        type: ELIMINAR_PRODUCTO
    }
}

export const agregarProducto = () => {
    return {
        type: AGREGAR_PRODUCTO
    }
}
export const editarProducto = () => {
    return {
        type: AGREGAR_PRODUCTO
    }
}

export const obtenerProducto = (producto: Producto) => {
    return {
        type: OBTENER_PRODUCTO,
        payload: producto
    }
}

export const obtenerTotalInvetario = (totalInventario: TotalInventario) => {
    return {
        type: OBTENER_TOTAL_INVENTARIO,
        payload: totalInventario
    }
}

export const listarCombinacionesProductos = (combinacionesProductos: []) => {
    
    return {
        type: LISTAR_COMBINACIONES_PRODUCTOS,
        payload: combinacionesProductos
    }
}

export const listarProductosAsync = () => {
    return (dispacth: any) => {
        dispacth(makeRequest());
        axiosIntance.get('http://localhost:9000/spring-reactive/productos/listar')
            .then(res => {
                const productos: Producto[] = res.data;
                dispacth(listarProductos(productos));
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}

export const eliminarProductoAsync = (id: number) => {
    return (dispacth: any) => {
        dispacth(makeRequest());
        axiosIntance.delete(`http://localhost:9000/spring-reactive/productos/eliminar/${id}`)
            .then(res => {
                dispacth(eliminarProducto());
                dispacth(listarProductosAsync());
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}


export const agregarProductoAsync = (producto: Producto):any => {
    return (dispacth: any) => {
        dispacth(makeRequest());
        axiosIntance.post('http://localhost:9000/spring-reactive/productos/crear', producto)
            .then(res => {
                dispacth(agregarProducto());
                dispacth(listarProductosAsync());

                toast.success('Producto agregado con exito');
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}

export const editarProductoAsync = (producto: Producto, id: number):any => {
    return (dispacth: any) => {
        dispacth(makeRequest());
        axiosIntance.patch(`http://localhost:9000/spring-reactive/productos/editar/${id}`, producto)
            .then(res => {
                dispacth(editarProducto());
                dispacth(listarProductosAsync());
                toast.success('Producto editado con exito');
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}

export const obtenerTotalInventaioAsync = () => {
    return (dispacth: any) => {
        dispacth(makeRequest());
        axiosIntance.get('http://localhost:9000/spring-reactive/productos/totalInventario')
            .then(res => {
                const totalInventario: TotalInventario = res.data;
                dispacth(obtenerTotalInvetario(totalInventario));
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}

export const listarCombinacionesProductosAsync = (limite: number) => {
    return (dispacth: any) => {
        dispacth(makeRequest());
        axiosIntance.get(`http://localhost:9000/spring-reactive/productos/combinaciones?limite=${limite}`)
            .then(res => {
                const combinacionesProductos: [] = res.data;
                dispacth(listarCombinacionesProductos(combinacionesProductos));
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}

