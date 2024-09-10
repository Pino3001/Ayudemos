import datatypes.DtBeneficiario;
import datatypes.DTDonacion;
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

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Fábrica
        Fabrica fabrica = Fabrica.getInstancia();
        // Interfaces
        IControladorUsuario controladorUsuario = fabrica.getControladorUsuario();
        IAltaDonacion iAltaDonacion = fabrica.getAltaDonacion();
        IAltaDistribucion iAltaDistribucion = fabrica.getIAltaDistribucion();
        IModificarDistribucion iModificarDistribucion = fabrica.getModificarDistribucion();

        // Cargar beneficiarios de prueba
        DatosPorDefecto dpf = new DatosPorDefecto();

        // Crear donaciones de prueba
        List<DTDonacion> dt = dpf.getDonacionesDT(); // Cambio aquí de getAlimentosDT a getDonacionesDT
        for (DTDonacion d : dt) {
            if (iAltaDonacion.crearDonacion(d)) {
                System.out.println("Al parecer fue creado!");
            }
        }

        // Obtener la instancia del ManejadorUsuario
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();

        // Cargar los beneficiarios de prueba en la base de datos
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Una vez cargados comenté para evitar errores en consola
        /*try {
            tx.begin();
            for (DtBeneficiario dtBeneficiario : dpf.getBeneficiariosDT()) {
                Beneficiario beneficiario = new Beneficiario(
                        dtBeneficiario.getNombre(),
                        dtBeneficiario.getMail(),
                        dtBeneficiario.getDireccion(),
                        dtBeneficiario.getFechaNacimiento(),
                        dtBeneficiario.getEstado(),
                        dtBeneficiario.getBarrio()
                );
                manejadorUsuario.agregarUsuario(beneficiario);
            }
            tx.commit();
            System.out.println("Beneficiarios de prueba cargados correctamente.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }*/

        // Cargar distribuciones de prueba en la base de datos
        try {
            tx.begin();
            for (DtDistribucion dtDistribucion : dpf.getDistribucionesDT()) {
                // Buscar los beneficiarios y donaciones para asociarlos con la distribución
                Beneficiario beneficiario = manejadorUsuario.obtenerBeneficiarioEmail(dtDistribucion.getEmailBeneficiario());
                Donacion donacion = em.find(Donacion.class, dtDistribucion.getIdDonacion());

                if (beneficiario != null && donacion != null) {
                    Distribucion distribucion = new Distribucion(
                            dtDistribucion.getFechaPreparacion(),
                            dtDistribucion.getFechaEntrega(),
                            dtDistribucion.getEstado(),
                            donacion,
                            beneficiario
                    );
                    em.persist(distribucion);
                }
            }
            tx.commit();
            System.out.println("Distribuciones de prueba cargadas correctamente.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        // GUI
        PrincipalGUI principalGUI = new PrincipalGUI(controladorUsuario, iAltaDonacion, iAltaDistribucion, iModificarDistribucion);
        principalGUI.setVisible(true);
    }
}
