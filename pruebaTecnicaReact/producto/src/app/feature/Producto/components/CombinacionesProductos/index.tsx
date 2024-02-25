import { Accordion, AccordionBody, AccordionHeader, AccordionList, List, ListItem, NumberInput } from "@tremor/react"
import { listarCombinacionesProductosAsync } from "../../../../core/redux/acciones/productos/Acciones"
import { connect } from "react-redux"
import { useEffect, useState } from "react"

const CombinacionesProductos = (props: any) => {
    const [limite, setLimite] = useState<number | string>('');


    useEffect(() => {
        props.loadCombinacionesProductos(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               )
    }, [])

    const handleLimiteChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = event.target.value;
        if(inputValue === "") {
            setLimite('');
            return;
        }
        const newLimite = parseInt(inputValue);
        setLimite(newLimite);
        props.loadCombinacionesProductos(newLimite);
    };
    

    return (
        <>
            <NumberInput
                className="max-w-sm mb-3"
                placeholder='Ingrese el límite'
                value={limite} 
                onChange={handleLimiteChange}  />
            <AccordionList className="mb-20">
                {props.combinacionesProductos.combinacionesProductos && props.combinacionesProductos.combinacionesProductos.map((combinacion: [], index: number) => (
                    <Accordion key={index}>
                        <AccordionHeader className="text-sm font-medium text-tremor-content-strong dark:text-dark-tremor-content-strong">
                            {`Precio Total Combinación ${index + 1} - $${combinacion[combinacion.length - 1]}`}
                        </AccordionHeader>
                        <AccordionBody className="leading-6">
                            <List>
                                {combinacion.slice(0, -1).map((element, i) => (
                                    <ListItem key={i}>
                                        {element}
                                    </ListItem>
                                ))}
                            </List>
                        </AccordionBody>
                    </Accordion>
                ))}
            </AccordionList>
        </>
    )
}

const mapStateToProps = (state: any) => {
    return {
        combinacionesProductos: state.producto
    }
}

const mapDispatchToProps = (dispatch: any) => {
    return {
        loadCombinacionesProductos: (limite: number = 0) => dispatch(listarCombinacionesProductosAsync(limite))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(CombinacionesProductos);

