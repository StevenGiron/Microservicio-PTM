import { Card, Divider, TextInput, NumberInput } from '@tremor/react';
import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Producto } from '../../models/Producto';
import { useDispatch, useSelector } from 'react-redux';
import { agregarProductoAsync, editarProductoAsync, listarProductosAsync } from '../../../../core/redux/acciones/productos/Acciones';

const AgregarEditarProducto = () => {
    const { id } = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [nombre, nombrechange] = useState('');
    const [descripcion, descripcionchange] = useState('');
    const [precio, preciochange] = useState(0);
    const [stock, stockchange] = useState(0);
    const [editMode, editmodechange] = useState(false);
    const productos = useSelector((state: any) => state.producto.listaProductos);

    useEffect(() => {
        if (id) {
            const productoEditar = productos.find((producto: Producto) => producto.id === parseInt(id));
            
            if (productoEditar) {
                nombrechange(productoEditar.nombre);
                descripcionchange(productoEditar.descripcion);
                preciochange(productoEditar.precio);
                stockchange(productoEditar.stock);
                editmodechange(true);
            }
        }
    }, [id, productos]);

    const handleSumit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const productoObject: Producto = { nombre, descripcion, precio, stock };
        if (editMode) {
            dispatch(editarProductoAsync(productoObject, parseInt(id!)));
            navigate('/');
        } else {
            dispatch(agregarProductoAsync(productoObject));
            (dispatch as any)(listarProductosAsync());
            navigate('/');
        }
    }


    return (
        <>
            <div className="sm:mx-auto sm:max-w-2x flex justify-startl">
                <Card>
                    <form action="#" method="post" className="mt-8" onSubmit={handleSumit}>
                        <div className="grid grid-cols-1 gap-x-4 gap-y-6 sm:grid-cols-6">
                            <div className="col-span-full sm:col-span-2">
                                <label
                                    htmlFor="first-name"
                                    className="text-tremor-default font-medium text-tremor-content-strong dark:text-dark-tremor-content-strong"
                                >
                                    Nombre
                                    <span className="text-red-500">*</span>
                                </label>
                                <TextInput
                                    value={nombre}
                                    onChange={e => nombrechange(e.target.value)}
                                    type="text"
                                    id="nombre"
                                    name="nombre"
                                    autoComplete="nombre"
                                    placeholder="Nombre"
                                    className="mt-2"
                                    required
                                />
                            </div>

                            <div className="col-span-3">
                                <label
                                    htmlFor="email"
                                    className="text-tremor-default font-medium text-tremor-content-strong dark:text-dark-tremor-content-strong"
                                >
                                    Descripcion
                                    <span className="text-red-500">*</span>
                                </label>
                                <TextInput
                                    value={descripcion}
                                    onChange={e => descripcionchange(e.target.value)}
                                    type="text"
                                    id="descripcion"
                                    name="descripcion"
                                    autoComplete="descripcion"
                                    placeholder="Descripcion"
                                    className="mt-2"
                                    required
                                />
                            </div>
                            <div className="col-span-2">
                                <label
                                    htmlFor="address"
                                    className="text-tremor-default font-medium text-tremor-content-strong dark:text-dark-tremor-content-strong"
                                >

                                    Precio
                                </label>
                                <NumberInput
                                    value={precio}
                                    onChange={e => preciochange(parseFloat(e.target.value))}
                                    id="precio"
                                    name="precio"
                                    autoComplete="precio"
                                    placeholder="Precio"
                                    className="mt-2"
                                />
                            </div>
                            <div className="col-span-full sm:col-span-2">
                                <label
                                    htmlFor="city"
                                    className="text-tremor-default font-medium text-tremor-content-strong dark:text-dark-tremor-content-strong"
                                >
                                    Stock
                                </label>
                                <NumberInput
                                    value={stock}
                                    onChange={e => stockchange(parseInt(e.target.value))}
                                    id="stock"
                                    name="stock"
                                    autoComplete="stock"
                                    placeholder="Stock"
                                    className="mt-2"
                                />
                            </div>

                        </div>
                        <Divider />
                        <div className="flex items-center justify-end space-x-4">
                            <Link to={'/'}>
                                <button
                                    type="button"
                                    className="whitespace-nowrap rounded-tremor-small px-4 py-2.5 text-tremor-default font-medium text-tremor-content-strong dark:text-dark-tremor-content-strong"
                                >
                                    Cancel
                                </button>
                            </Link>
                            <button
                                type="submit"
                                className="whitespace-nowrap rounded-tremor-default bg-tremor-brand px-4 py-2.5 text-tremor-default font-medium text-tremor-brand-inverted shadow-tremor-input hover:bg-tremor-brand-emphasis dark:bg-dark-tremor-brand dark:text-dark-tremor-brand-inverted dark:shadow-dark-tremor-input dark:hover:bg-dark-tremor-brand-emphasis"
                            >
                                {editMode ? 'Editar' : 'Agregar'}
                            </button>
                        </div>
                    </form>
                </Card>
            </div>

        </>
    );
}

export default AgregarEditarProducto;