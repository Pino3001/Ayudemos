package objects;

import interfaces.IAltaDonacion;
import datatypes.DTDonacion;
import datatypes.DTArticulo;
import datatypes.DTAlimento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class AltaDonacion implements IAltaDonacion {

    //Crea una nueva Donación
    @Override
    public boolean crearDonacion(DTDonacion dtDonacion){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        Donacion nuevaDonacion = null;

        if(dtDonacion instanceof DTArticulo){
           nuevaDonacion = new Articulo(((DTArticulo) dtDonacion).getDescripcion(), ((DTArticulo) dtDonacion).getPeso(), ((DTArticulo) dtDonacion).getDimensiones());
            manejadorDonacion.agregarDonacion(nuevaDonacion);
            return true;
        } else if(dtDonacion instanceof DTAlimento){
            nuevaDonacion = new Alimento(((DTAlimento) dtDonacion).getDescripcionProductos(), ((DTAlimento) dtDonacion).getCantElementos());
            manejadorDonacion.agregarDonacion(nuevaDonacion);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public DTDonacion buscarDonacionID(DTDonacion dtDonacion){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.buscarDonacionID(dtDonacion.getId());
    }

    @Override
    public void editarDonacion(DTDonacion dtDonacion){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        manejadorDonacion.modificarDonacion(dtDonacion);
    }

    @Override
    public  List<DTDonacion> listarDonaciones() {
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.obtenerDonaciones();
    }

    @Override
    public List<DTAlimento> listarAlimentos(){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.listarAlimentosManejador();
    }

    @Override
    public List<DTArticulo> listarArticulos(){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.listarArticulosManejador();
    }

}