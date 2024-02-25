
import { configureStore } from '@reduxjs/toolkit';
import rootReducer from './reductores';


const store = configureStore(
    {
        reducer: rootReducer
    }
)

export default store;