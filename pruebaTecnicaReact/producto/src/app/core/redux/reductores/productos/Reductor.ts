import { AGREGAR_PRODUCTO, EDITAR_PRODUCTO, ELIMINAR_PRODUCTO, FAIL_REQUEST, LISTAR_COMBINACIONES_PRODUCTOS, LISTAR_PRODUCTOS, MAKE_REQUEST, OBTENER_PRODUCTO, OBTENER_TOTAL_INVENTARIO } from "../../acciones/productos/TiposAcciones"

const initialState = {
    loading: false,
    listaProductos: [],
    producto: {},
    errMessage: ''
}
export const Reductor = (state = initialState, action: any) => {
    switch (action.type) {
        case MAKE_REQUEST:
            return {
                ...state,
                loading: true
            }
        case FAIL_REQUEST:
            return {
                ...state,
                loading: false,
                errMessage: action.payload
            }
        case LISTAR_PRODUCTOS:
            return {
                ...state,
                loading: false,
                errMesaage: '',
                listaProductos: action.payload,
                producto: {}
            }
        case ELIMINAR_PRODUCTO:
            return {
                ...state,
                loading: false,
            }
        case AGREGAR_PRODUCTO:
            return {
                ...state,
                loading: false,
            }
        case EDITAR_PRODUCTO:
            return {
                ...state,
                loading: false,
            }
        case OBTENER_PRODUCTO:
            return {
                ...state,
                loading: false,
                producto: action.payload
            }
        case OBTENER_TOTAL_INVENTARIO:
            return {
                ...state,
                loading: false,
                errMesaage: '',
                totalInventario: action.payload,
                inventario: {}
            }
        case LISTAR_COMBINACIONES_PRODUCTOS:
            return {
                ...state,
                loading: false,
                errMesaage: '',
                combinacionesProductos: action.payload,
            }
        default:
            return state
    }
}