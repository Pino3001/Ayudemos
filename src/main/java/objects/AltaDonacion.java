package objects;

import interfaces.IAltaDonacion;
import datatypes.DTDonacion;
import datatypes.DTArticulo;
import datatypes.DTAlimento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AltaDonacion implements IAltaDonacion {

    public AltaDonacion() {
        super();//
    }

    //Crea una nueva Donación
    @Override
    public boolean crearDonacion(DTDonacion dtDonacion){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        Donacion nuevaDonacion = null;
        Integer id = manejadorDonacion.generarID();

        if(dtDonacion instanceof DTArticulo){
           nuevaDonacion = new Articulo(id, ((DTArticulo) dtDonacion).getDescripcion(), ((DTArticulo) dtDonacion).getPeso(), ((DTArticulo) dtDonacion).getDimensiones());
            manejadorDonacion.agregarDonacion(nuevaDonacion);
            return true;
        } else if(dtDonacion instanceof DTAlimento){
            nuevaDonacion = new Alimento(id, ((DTAlimento) dtDonacion).getDescripcionProductos(), ((DTAlimento) dtDonacion).getCantElementos());
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
    public void editarDonacion(DTDonacion dtDonacion, Integer id){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        manejadorDonacion.modificarDonacion(dtDonacion, id);
        DTDonacion dtDonacion1 = manejadorDonacion.buscarDonacionID(id);
        if(dtDonacion1 instanceof DTAlimento){
            System.out.println( "Se Edito? " + ((DTAlimento) dtDonacion1).getCantElementos());
        } else if (dtDonacion instanceof DTArticulo) {
            System.out.println( "Se Edito? " + ((DTArticulo) dtDonacion1).getDescripcion());
        }

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