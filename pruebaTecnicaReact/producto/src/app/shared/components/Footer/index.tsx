import { connect } from "react-redux";
import { obtenerDatoInutilAsync } from "../../../core/redux/acciones/datoInutil/Acciones";
import { useEffect } from "react";

const Footer = (props: any) => {

  useEffect(() => {
    props.loadDatoInutil();
  }, [])

  return (
    <footer className="fixed inset-x-0 bottom-0 bg-gray-800 text-white p-4 text-center">
      <p className="mb-2">© 2024 Steven Giron</p>
      <p>{props.datoInutil && props.datoInutil.datoInutil ? props.datoInutil.datoInutil.text : 'Hoy no hay dato inutil'}</p>
    </footer>
  )
};

const mapStateToProps = (state: any) => {
  return {
    datoInutil: state.datoInutil
  }
}

const mapDispatchToProps = (dispatch: any) => {
  return {
    loadDatoInutil: () => dispatch(obtenerDatoInutilAsync()),
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Footer); 