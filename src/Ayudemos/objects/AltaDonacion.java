package Ayudemos.objects;

import Ayudemos.datatypes.IAltaDonacion;
import Ayudemos.datatypes.DTDonacion;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.datatypes.DTAlimento;

public class AltaDonacion implements IAltaDonacion {

    public AltaDonacion() {
        super();
    }

    //Crea una nueva donacion
    public boolean crearDonacion(DTDonacion dtDonacion){
        if(dtDonacion instanceof DTArticulo){
            nuevaDonacion = new Articulo(dtDonacion.ge);
        } else {
            nuevaDonacion = new Alimento();
        }

    }

}