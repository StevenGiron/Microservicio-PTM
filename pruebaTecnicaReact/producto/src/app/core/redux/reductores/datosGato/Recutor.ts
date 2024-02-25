import { FAIL_REQUEST, OBTENER_DATOS_GATO } from "../../acciones/gatosDato/TipoAcciones"

const initialState = {
    loading: false,
    datosGato: {},
    errMessage: ''
}
export const ReductorDatosGato = (state = initialState, action: any) => {
    switch (action.type) {
        case OBTENER_DATOS_GATO:
            return {
                ...state,
                loading: false,
                errMesaage: '',
                datosGato: action.payload
            }
        case FAIL_REQUEST:
            return {
                ...state,
                loading: false,
                errMessage: action.payload
            }
        default:
            return state
    }
}