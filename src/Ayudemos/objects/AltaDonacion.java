package Ayudemos.objects;

import Ayudemos.interfaces.IAltaDonacion;
import Ayudemos.datatypes.DTDonacion;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.datatypes.DTAlimento;

public class AltaDonacion implements IAltaDonacion {

    public AltaDonacion() {
        super();
    }

    //Crea una nueva donacion
    @Override
    public boolean crearDonacion(DTDonacion dtDonacion){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        Donacion nuevaDonacion = null;
        if(dtDonacion instanceof DTArticulo){
           nuevaDonacion = new Articulo(dtDonacion.getId(), ((DTArticulo) dtDonacion).getDescripcion(), ((DTArticulo) dtDonacion).getPeso(), ((DTArticulo) dtDonacion).getDimenciones());
        } else {
           nuevaDonacion = new Alimento(dtDonacion.getId(), ((DTAlimento) dtDonacion).getDescripcionProductos(), ((DTAlimento) dtDonacion).getCantElementos());
        }
            manejadorDonacion.agregarDonacion(nuevaDonacion);
            return true;
    }

    @Override
    public DTDonacion BuscatDonacionID(DTDonacion dtDonacion){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        return manejadorDonacion.buscarDoncionID(dtDonacion.getId());
    }

    @Override
    public void editarDonacion(DTDonacion dtDonacion, Integer id){
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        manejadorDonacion.modificarDonacion(dtDonacion, id);
    }

}