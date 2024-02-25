import { FAIL_REQUEST, OBTENER_DATO_INUTIL } from "../../acciones/datoInutil/TipoAcciones"

const initialState = {
    loading: false,
    datoInutil: {},
    errMessage: ''
}
export const ReductorDatoInutil = (state = initialState, action: any) => {
    switch (action.type) {
        case OBTENER_DATO_INUTIL:
            return {
                ...state,
                loading: false,
                errMesaage: '',
                datoInutil: action.payload
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