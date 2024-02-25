import { combineReducers } from '@reduxjs/toolkit';
import { Reductor, Reductor as productos } from './productos/Reductor';
import { ReductorDatoInutil } from './datoInutil/Reductor';
import { ReductorDatosGato } from './datosGato/Recutor';

const rootReducer = combineReducers({producto:Reductor, datoInutil: ReductorDatoInutil, datosGato: ReductorDatosGato})
  
export default rootReducer;
