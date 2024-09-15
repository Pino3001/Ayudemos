import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import gui.PrincipalGUI;
import interfaces.*;
import objects.Beneficiario;
import objects.Donacion;
import objects.Distribucion;
import objects.ManejadorUsuario;
import persistencia.Conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        // Fábrica
        Fabrica fabrica = Fabrica.getInstancia();
        // Interfaces
        IControladorUsuario controladorUsuario = fabrica.getIControladorUsuario();
        IControladorDonacion iControladorDonacion = fabrica.getAltaDonacion();
        IControladorDistribucion iControladorDistribucion = fabrica.getIControladorDistribucion();

/*        // Cargar beneficiarios de prueba
        DatosPorDefecto dpf = new DatosPorDefecto();

        // Crear donaciones de prueba
        List<DTDonacion> dt = dpf.getDonacionesDT(); // Cambio aquí de getAlimentosDT a getDonacionesDT
        for (DTDonacion d : dt) {
            if (iControladorDonacion.crearDonacion(d)) {
                System.out.println("Al parecer fue creado!");
            }
        }

        for (DTDonacion d : dt) {
            if (iControladorDonacion.crearDonacion(d)) {
                System.out.println("Al parecer fue creado!");
            }
        }
        // Cargar Usuarios Beneficiarios de prueba en la base de datos
        try {
            for (DtUsuario dtUsuario : dpf.getBeneficiariosDT()) {
                // Buscar los beneficiarios y donaciones para asociarlos con la distribución
                controladorUsuario.agregarUsuario(dtUsuario);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Cargar distribuciones de prueba en la base de datos
        try {
            for (DtDistribucion dtDistribucion : dpf.getDistribucionesDT()) {
                // Buscar los beneficiarios y donaciones para asociarlos con la distribución
                DtUsuario beneficiario = controladorUsuario.obtenerUsuarioPorId(dtDistribucion.getIdUsuario());
                DTDonacion donacion = iControladorDonacion.buscarDonacionID(dtDistribucion.getIdDonacion());
                if (donacion != null && beneficiario instanceof DtBeneficiario ben) {
                    iControladorDistribucion.crearDistribucion(ben,
                            donacion,
                            dtDistribucion.getFechaPreparacion(),
                            dtDistribucion.getFechaEntrega(),
                            dtDistribucion.getEstado());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        // GUI
        PrincipalGUI principalGUI = new PrincipalGUI(controladorUsuario, iControladorDonacion, iControladorDistribucion);
        principalGUI.setVisible(true);
    }


}
