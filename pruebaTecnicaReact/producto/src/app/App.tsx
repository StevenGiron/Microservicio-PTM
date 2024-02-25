import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import { NavBar } from './shared/components/Header';
import AgregarEditarProducto from './feature/Producto/components/AgregarEditarProducto';
import ListaProductos from './feature/Producto/components/ListaProductos';
import Footer  from './shared/components/Footer';
import CalloutHero from './shared/components/Callout';

function App() {
  return (
    <BrowserRouter>
      <>
        <NavBar />
        <Routes>
          <Route path='/' element={<ListaProductos />} />
          <Route path='/agregar' element={<AgregarEditarProducto />} />
          <Route path='/editar/:id' element={<AgregarEditarProducto />} />
        </Routes>
        <ToastContainer className='toast-position' position='bottom-right' />
        <CalloutHero />
      </>
    </BrowserRouter>

  );
}

export default App;
