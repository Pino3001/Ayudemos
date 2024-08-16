//    Cada distribución incluye una fecha de preparación y una fecha de entrega, además de un
//    Universidad de la República | Dirección General de Educación Técnico Profesional
//    estado que puede ser "PENDIENTE", "EN CAMINO" o "ENTREGADO". Una distribución puede
//    involucrar una o más donaciones, y está destinada a un beneficiario específico.

package Ayudemos.interfaces;

import Ayudemos.objects.Beneficiario;
import Ayudemos.objects.Donacion;
import Ayudemos.types.DTFechaHora;
import Ayudemos.types.EstadoDistribucion;

import java.util.List;


public interface IAltaDistribucion {

    // Crea una nueva distribución y llama al manejador de las distribuciones para agregarla.
    public void crearDistribucion(Beneficiario beneficiario, List<Donacion> donaciones, DTFechaHora fechaPreparacion, DTFechaHora fechaEntrega, EstadoDistribucion estado);
    
}
