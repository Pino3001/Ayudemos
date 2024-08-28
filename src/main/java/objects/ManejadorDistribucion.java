package objects;

import datatypes.DTDonacion;
import types.DTFechaHora;
import types.DtDistribucion;
import types.EstadoDistribucion;

import java.util.ArrayList;
import java.util.List;


public class ManejadorDistribucion {

    private static ManejadorDistribucion instacia = null;
    private List<Distribucion> distribuciones = new ArrayList<>();

    private ManejadorDistribucion() {}

    public static ManejadorDistribucion getInstance() {
        if (instacia == null)
            instacia = new ManejadorDistribucion();
        return instacia;

    }

    public List<DtDistribucion> buscarDistribucionesPorEstado(EstadoDistribucion estado) {
//        List<Distribucion> resultados = new ArrayList<>();
          List<DtDistribucion> lista = new ArrayList<>();
          for (Distribucion d : distribuciones) {
                  // creamos el dt y lo añadimos a la lista que retornaremos al terminar.
                  DtDistribucion dt = new DtDistribucion(d.getFechaPreparacion(), d.getFechaEntrega(), d.getEstado());

              if (estado == null || d.getEstado().equals(estado)) {
                  lista.add(dt);
              }
          }


        return lista;
    }
      // Crea una nueva distribución.
//    @Override
//    public void AltaDistribucion(Beneficiario beneficiario,
//                                  Donacion donacion,
//                                  DTFechaHora fechaPreparacion,
//                                  DTFechaHora fechaEntrega,
//                                  EstadoDistribucion estado) {
//        // Creamos la nueva distribución, al crearse ya apunta al beneficiario y a la donacion pasados por parámetro.
//        Distribucion nuevaDist = new Distribucion(fechaPreparacion, fechaEntrega, estado, donacion, beneficiario);
//        // Vinculamos la nueva distribución a la lista de distribuciones de la donación y el beneficario.
//        donacion.addDistribucion(nuevaDist);
//        beneficiario.addDistribucion(nuevaDist);
//        // !!!!! SI IMPLEMENTAMOS UN MANEJADOR DE DISTRIBUCIONES ACA TENDRIAMOS QUE HACER UN PUSH A ESE MANEJADOR.
//    }



}




