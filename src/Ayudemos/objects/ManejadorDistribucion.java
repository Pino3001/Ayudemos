package Ayudemos.objects;

import Ayudemos.datatypes.DTDistribucion;
import Ayudemos.objects.Distribucion;
import Ayudemos.objects.Beneficiario;
import Ayudemos.objects.Donacion;
import Ayudemos.types.DTFechaHora;
import Ayudemos.types.EstadoDistribucion;

import java.util.ArrayList;
import java.util.List;

public class ManejadorDistribucion {
    private static ManejadorDistribucion instance = null;
    private List<Distribucion> distribuciones = new ArrayList<>();

    private ManejadorDistribucion() {}

    public static ManejadorDistribucion getInstance() {
        if (instance == null) {
            instance = new ManejadorDistribucion();
        }
        return instance;
    }

    public void agregarDistribucion(Distribucion distribucion) {
        distribuciones.add(distribucion);
    }

    // Busca una distribución por emailBeneficiario e idDonacion
    public DTDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion) {
        for (Distribucion distribucion : distribuciones) {
            if (distribucion.getBeneficiario().getMail().equals(emailBeneficiario)
                    && distribucion.getDonacion().getId() == idDonacion) {
                return new DTDistribucion(distribucion.getFechaPreparacion(),distribucion.getFechaEntrega(),distribucion.getEstado(),distribucion.getDonacion().getId(),distribucion.getBeneficiario().getNombre(), distribucion.getBeneficiario().getMail());
            }
        }
        return null;
    }

    // Modifica una distribución existente
    public void modificarDistribucion(DTDistribucion dtDistribucion) {
        for (Distribucion distribucion : distribuciones) {
            if (distribucion.getBeneficiario().getMail().equals(dtDistribucion.getEmailBeneficiario()) && distribucion.getDonacion().getId() == dtDistribucion.getIdDonacion()) {
                distribucion.setFechaPreparacion(dtDistribucion.getFechaPreparacion());
                distribucion.setFechaEntrega(dtDistribucion.getFechaEntrega());
                distribucion.setEstado(dtDistribucion.getEstado());
            }
        }
    }
}