package Ayudemos.objects;

import Ayudemos.interfaces.IAltaDonacion;
import Ayudemos.datatypes.DTDonacion;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.datatypes.DTAlimento;

public class AltaDonacion implements IAltaDonacion {

    public AltaDonacion() {
        super();
    }

    //Crea una nueva Donación
    @Override
    public boolean crearDonacion(DTDonacion dtDonacion){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        Donacion nuevaDonacion = null;
        if(dtDonacion instanceof DTArticulo){
           nuevaDonacion = new Articulo(dtDonacion.getId(), ((DTArticulo) dtDonacion).getDescripcion(), ((DTArticulo) dtDonacion).getPeso(), ((DTArticulo) dtDonacion).getDimensiones());
        } else {
           nuevaDonacion = new Alimento(dtDonacion.getId(), ((DTAlimento) dtDonacion).getDescripcionProductos(), ((DTAlimento) dtDonacion).getCantElementos());
        }
            manejadorDonacion.agregarDonacion(nuevaDonacion);
            return true;
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
    }

}