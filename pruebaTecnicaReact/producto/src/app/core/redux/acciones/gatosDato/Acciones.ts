import { DatosGato } from "../../../../feature/DatosGato/models/DatosGato"
import { axiosIntance } from "../../../config/AxiosConfig"
import { FAIL_REQUEST, OBTENER_DATOS_GATO } from "./TipoAcciones"

export const obtenerDatosGato = (datosGato: DatosGato) => {
    return {
        type: OBTENER_DATOS_GATO,
        payload: datosGato
    }
}

export const failRequest = (err: Error) => {
    return {
        type: FAIL_REQUEST,
        payload: err
    }
}

export const obtenerDatosGatoAsync = () => {
    return (dispacth: any) => {
        axiosIntance.get('https://meowfacts.herokuapp.com/?count=2')
            .then(res => {
                const datosGato: DatosGato = res.data;
                dispacth(obtenerDatosGato(datosGato));
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}
