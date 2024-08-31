package objects;

import datatypes.DtDistribucion;
import types.Barrio;
import types.EstadoDistribucion;

import java.util.ArrayList;
import java.util.List;


public class ManejadorDistribucion {

    private static ManejadorDistribucion instacia = null;
    private List<Distribucion> distribuciones = new ArrayList<>();

    private ManejadorDistribucion() {
    }

    public static ManejadorDistribucion getInstance() {
        if (instacia == null)
            instacia = new ManejadorDistribucion();
        return instacia;

    }

    public List<DtDistribucion> buscarDistribucionesPorEstado(EstadoDistribucion estado) {
        List<DtDistribucion> lista = new ArrayList<DtDistribucion>();
        for (Distribucion d : distribuciones) {
            // creamos el dt y lo añadimos a la lista que retornaremos al terminar.
            DtDistribucion dt = new DtDistribucion(d.getFechaPreparacion(), d.getFechaEntrega(), d.getEstado(), d.getDonacion().getId(), d.getBeneficiario().getNombre(), d.getBeneficiario().getMail());

            if (estado == null || d.getEstado().equals(estado)) {
                lista.add(dt);
            }
        }

        return lista;
    }

    // Retorna una lista de datatypes de todas las distribuciones del sistema.
    public List<DtDistribucion> obtenerListaDistribuciones() {
        List<DtDistribucion> lista = new ArrayList<DtDistribucion>();
        for (Distribucion d : distribuciones) {
            // creamos el dt y lo añadimos a la lista que retornaremos al terminar.
            DtDistribucion dt = new DtDistribucion(d.getFechaPreparacion(), d.getFechaEntrega(), d.getEstado(), d.getDonacion().getId(), d.getBeneficiario().getNombre(), d.getBeneficiario().getMail());
            lista.add(dt);
        }

        return lista;
    }

    // Retorna una lista de datatypes de todas las distribuciones del sistema filtradas por zona.
    public List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio) {
        List<DtDistribucion> lista = new ArrayList<DtDistribucion>();
        for (Distribucion d : distribuciones) {
            // creamos el dt y lo añadimos a la lista que retornaremos al terminar.
            if (d.getBeneficiario().getBarrio() == barrio) {
                DtDistribucion dt = new DtDistribucion(d.getFechaPreparacion(), d.getFechaEntrega(), d.getEstado(), d.getDonacion().getId(), d.getBeneficiario().getNombre(), d.getBeneficiario().getMail());
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




