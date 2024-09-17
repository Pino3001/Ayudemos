package objects;

import excepciones.IngresoIncorrectoExeption;
import excepciones.NoEncontradoExeption;
import interfaces.IControladorDonacion;
import datatypes.DTDonacion;
import datatypes.DTArticulo;
import datatypes.DTAlimento;

import java.util.List;

public class ControladorDonacion implements IControladorDonacion {
    public ControladorDonacion() {
        super();
    }

    //Crea una nueva Donación
    @Override
    public boolean crearDonacion(DTDonacion dtDonacion) {
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        Donacion nuevaDonacion = null;

        if (dtDonacion instanceof DTArticulo) {
            nuevaDonacion = new Articulo(((DTArticulo) dtDonacion).getDescripcion(), ((DTArticulo) dtDonacion).getPeso(), ((DTArticulo) dtDonacion).getDimensiones());
            manejadorDonacion.agregarDonacion(nuevaDonacion);
            return true;
        } else if (dtDonacion instanceof DTAlimento) {
            nuevaDonacion = new Alimento(((DTAlimento) dtDonacion).getDescripcionProductos(), ((DTAlimento) dtDonacion).getCantElementos());
            manejadorDonacion.agregarDonacion(nuevaDonacion);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DTDonacion buscarDonacionID(Integer id) throws NoEncontradoExeption {
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.buscarDonacionID(id);
    }

    @Override
    public void editarDonacion(DTDonacion dtDonacion, Integer id) throws IngresoIncorrectoExeption {
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        manejadorDonacion.modificarDonacion(dtDonacion, id);
    }

    @Override
    public List<DTDonacion> listarDonaciones() {
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.obtenerDonaciones();
    }

    @Override
    public List<DTAlimento> listarAlimentos() {
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.listarAlimentosManejador();
    }

    @Override
    public List<DTArticulo> listarArticulos() {
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.listarArticulosManejador();
    }

}