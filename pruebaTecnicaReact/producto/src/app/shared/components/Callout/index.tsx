import { useState, useEffect } from 'react';
import { Card } from '@tremor/react';
import { connect } from 'react-redux';
import { obtenerDatosGatoAsync } from '../../../core/redux/acciones/gatosDato/Acciones';
import { List, ListItem } from '@tremor/react';

const CalloutHero = (props: any) => {
  const [showCallout, setShowCallout] = useState(false);

  useEffect(() => {
    props.loadDatosGato();
    setShowCallout(true);

    const timeout = setTimeout(() => {
      setShowCallout(false);
    }, 5000);

    return () => clearTimeout(timeout);
  }, []);

  return (
    <div className="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-10">
      {showCallout && (
        <Card className="mx-auto max-w-md bg-sky-100">
          <h3 className="text-tremor-content-strong dark:text-dark-tremor-content-strong font-medium">Sabias que...</h3>
          <List>
            {props.datosGato.datosGato.data?.map((dato: [], index: number) => (
              <ListItem key={index}>
                {dato}
              </ListItem>
            ))}
          </List>
        </Card>
      )}
    </div>
  );
};

const mapStateToProps = (state: any) => {
  return {
    datosGato: state.datosGato
  }
}

const mapDispatchToProps = (dispatch: any) => {
  return {
    loadDatosGato: () => dispatch(obtenerDatosGatoAsync()),
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(CalloutHero); 
