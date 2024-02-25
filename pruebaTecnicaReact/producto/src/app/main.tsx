import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import 'react-toastify/dist/ReactToastify.css'
import { Provider } from 'react-redux'
import store from './core/redux/store.ts'
import Footer from './shared/components/Footer/index.tsx'


ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
      <Footer />
    </Provider>
  </React.StrictMode>,
)
