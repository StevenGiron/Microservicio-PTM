import { DatoInutil } from "../../../../feature/DatoInutil/models/DatoInutil"
import { axiosIntance } from "../../../config/AxiosConfig"
import { FAIL_REQUEST, OBTENER_DATO_INUTIL } from "./TipoAcciones"

export const obtenerDatoInutil = (datoInutil: DatoInutil) => {
    return {
        type: OBTENER_DATO_INUTIL,
        payload: datoInutil
    }
}

export const failRequest = (err: Error) => {
    return {
        type: FAIL_REQUEST,
        payload: err
    }
}

export const obtenerDatoInutilAsync = () => {
    return (dispacth: any) => {
        axiosIntance.get('https://uselessfacts.jsph.pl/api/v2/facts/today')
            .then(res => {
                const datoInutil: DatoInutil = res.data;
                dispacth(obtenerDatoInutil(datoInutil));
            })
            .catch(err => {
                dispacth(failRequest(err.message));
            })
    }
}
