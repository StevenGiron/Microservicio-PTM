// 'use client';
import {
    Badge,
    Card,
    Divider,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeaderCell,
    TableRow,
    Title,

} from '@tremor/react';
import { connect } from "react-redux";
import { StyledCard } from './styles';

import { eliminarProductoAsync, listarProductosAsync, obtenerTotalInventaioAsync } from '../../../../core/redux/acciones/productos/Acciones';
import { useEffect, useState } from 'react';
import { Producto } from '../../models/Producto';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import CombinacionesProductos from '../CombinacionesProductos';


const ListaProductos = (props: any) => {
    const [ordenNombre, setOrdenNombre] = useState<'asc' | 'desc'>('asc');
    const [productoMasCaro, setProductoMasCaro] = useState<Producto | null>(null);

    useEffect(() => {
        props.loadProducto();
    }, [])


    useEffect(() => {
        const productoMasCaro = encontrarProductoMasCaro(props.producto.listaProductos);
        setProductoMasCaro(productoMasCaro);
        props.loadTotalInventario();
    }, [props.producto.listaProductos]);

    const encontrarProductoMasCaro = (productos: Producto[]): Producto | null => {
        if (productos.length === 0) return null;

        return productos.reduce((prev, current) => {
            return (prev.precio > current.precio) ? prev : current;
        });
    };

    const handleDelete = (id: number) => {
        if (window.confirm('¿Estas seguro de eliminar el producto?')) {
            props.removeProducto(id);
            props.loadProducto();
            toast.success('Producto eliminado con exito');
        }
    }

    const handleSort = () => {
        const nextOrden = ordenNombre === 'asc' ? 'desc' : 'asc';
        setOrdenNombre(nextOrden);
    };

    const sortedProductos = props.producto.listaProductos.slice().sort((a: Producto, b: Producto) => {
        if (ordenNombre === 'asc') {
            return a.nombre.localeCompare(b.nombre);
        } else {
            return b.nombre.localeCompare(a.nombre);
        }
    });

    return (
        <>
            <Card>
                <StyledCard>
                    <Title className='mr-2'>
                        Valor Total del Inventario
                    </Title>
                    <Badge className='mr-2'>
                        {props.producto.totalInventario ? `$${props.producto.totalInventario.totalInventario}` : 'No hay productos'}
                    </Badge>
                    <Title className='mr-2'>
                        Producto Más Costoso
                    </Title>
                    <Badge>
                        {productoMasCaro ? `${productoMasCaro.nombre} - $${productoMasCaro.precio}` : 'No hay productos'}
                    </Badge>
                </StyledCard>
                <Table className="mt-8">
                    <TableHead>
                        <TableRow className="border-b border-tremor-border dark:border-dark-tremor-border">
                            <TableHeaderCell className="text-tremor-content-strong dark:text-dark-tremor-content-strong">
                                Codigo
                            </TableHeaderCell>
                            <TableHeaderCell className="text-tremor-content-strong dark:text-dark-tremor-content-strong" onClick={handleSort}>
                                Nombre
                                {ordenNombre === 'asc' ? ' ▲' : ' ▼'}
                            </TableHeaderCell>
                            <TableHeaderCell className="text-tremor-content-strong dark:text-dark-tremor-content-strong">
                                Descripcion
                            </TableHeaderCell>
                            <TableHeaderCell className="text-tremor-content-strong dark:text-dark-tremor-content-strong">
                                Precio
                            </TableHeaderCell>
                            <TableHeaderCell className="text-tremor-content-strong dark:text-dark-tremor-content-strong">
                                Stock
                            </TableHeaderCell>
                            <TableHeaderCell className="text-tremor-content-strong dark:text-dark-tremor-content-strong">
                                Acciones
                            </TableHeaderCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {sortedProductos.map((producto: Producto) => (
                            <TableRow
                                key={producto.id}
                                className="even:bg-tremor-background-muted even:dark:bg-dark-tremor-background-muted"
                            >
                                <TableCell className="font-medium text-tremor-content-strong dark:text-dark-tremor-content-strong">
                                    {producto.id}
                                </TableCell>
                                <TableCell>{producto.nombre}</TableCell>
                                <TableCell>{producto.descripcion}</TableCell>
                                <TableCell>{producto.precio}</TableCell>
                                <TableCell>{producto.stock}</TableCell>
                                <TableCell>

                                    <button type='button' onClick={() => { handleDelete(producto.id!) }}>
                                        <svg aria-label='eliminar producto' xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                                            <path strokeLinecap="round" strokeLinejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                                        </svg>
                                    </button>

                                    <Link to={`/editar/${producto.id}`}>
                                        <button type='button'>
                                            <svg aria-label='editar producto' xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                                                <path strokeLinecap="round" strokeLinejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                                            </svg>
                                        </button>
                                    </Link>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </Card>
            <Divider className='font-bold'>Combinaciones de Productos</Divider>
            <CombinacionesProductos />

        </>
    );
}

const mapStateToProps = (state: any) => {
    return {
        producto: state.producto
    }
}

const mapDispatchToProps = (dispatch: any) => {
    return {
        loadProducto: () => dispatch(listarProductosAsync()),
        removeProducto: (id: number) => dispatch(eliminarProductoAsync(id)),
        loadTotalInventario: () => dispatch(obtenerTotalInventaioAsync())
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ListaProductos);
