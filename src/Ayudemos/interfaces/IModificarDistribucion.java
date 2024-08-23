//    Cada distribución incluye una fecha de preparación y una fecha de entrega, además de un
//    estado que puede ser "PENDIENTE", "EN CAMINO" o "ENTREGADO". Una distribución puede
//    involucrar una o más donaciones, y está destinada a un beneficiario específico.

package Ayudemos.interfaces;

import Ayudemos.datatypes.DTDonacion;
import Ayudemos.objects.Beneficiario;
import Ayudemos.objects.Donacion;
import Ayudemos.datatypes.DTDistribucion;
import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DtBeneficiario;
import Ayudemos.types.EstadoDistribucion;

import java.util.List;


public interface IModificarDistribucion {

    void modificarDistribucion(DTDistribucion dtDistribucion);

    // Retornar lista de beneficiarios para cargar el combobox.
    List<DtBeneficiario> obtenerListaDtBeneficiarios();

    // Retornar lista de donaciones para cargar el combobox.
    List<DTDonacion> obtenerListaDtDonaciones();

}
