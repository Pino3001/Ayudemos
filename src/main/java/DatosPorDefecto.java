import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import types.Barrio;
import types.EstadoBeneficiario;
import types.EstadoDistribucion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatosPorDefecto {

    private List<DTDonacion> donacionesDT;
    private List<DtBeneficiario> beneficiariosDT;
    private List<DtDistribucion> distribucionesDT;

    public DatosPorDefecto() {
        // Inicializar las listas
        donacionesDT = new ArrayList<>();
        beneficiariosDT = new ArrayList<>();
        distribucionesDT = new ArrayList<>();

        // Cargar donaciones, beneficiarios y distribuciones de prueba
        cargarDonaciones();
        cargarBeneficiarios();
        cargarDistribuciones();
    }

    private void cargarDonaciones() {
        // Agregar elementos a la lista de donaciones

        donacionesDT.add(new DTAlimento(1, LocalDateTime.now(), "Manzanas", 10));
        donacionesDT.add(new DTAlimento(2, LocalDateTime.now(), "Pan", 20));
        donacionesDT.add(new DTAlimento(3, LocalDateTime.now(), "Leche", 15));

        DTArticulo dtArticulo1 = new DTArticulo(0, LocalDateTime.now(), "Mesa", 10, "120x60x75");
        donacionesDT.add(dtArticulo1);

        DTArticulo dtArticulo2 = new DTArticulo(1, LocalDateTime.now(), "Silla", 5, "45x45x90");
        donacionesDT.add(dtArticulo2);

        DTArticulo dtArticulo3 = new DTArticulo(2, LocalDateTime.now(), "Lámpara", 1, "20x20x40");
        donacionesDT.add(dtArticulo3);

        DTArticulo dtArticulo4 = new DTArticulo(3, LocalDateTime.now(), "Espejo", 7, "80x60x5");
        donacionesDT.add(dtArticulo4);

        for (int i = 1; i <= 100; i++) {
            String descripcion = "Donación " + i;
            int cantidad = i % 10 + 1;  // Generar cantidades entre 1 y 10
            donacionesDT.add(new DTAlimento(i, LocalDateTime.now(), descripcion, cantidad));
        }

        for (int i = 1; i <= 100; i++) {
            String descripcion = "Artículo " + i;
            float peso = i % 10 + 1;  // Generar pesos entre 1 y 10
            String dimensiones = (50 + i % 50) + "x" + (40 + i % 30) + "x" + (10 + i % 10);
            donacionesDT.add(new DTArticulo(i, LocalDateTime.now(), descripcion, peso, dimensiones));
        }
    }

    private void cargarBeneficiarios() {
        // Crear beneficiarios para cada barrio usando DtBeneficiario
        for (Barrio barrio : Barrio.values()) {
            for (int i = 1; i <= 5; i++) { // Generar 5 beneficiarios por barrio
                String nombre = barrio + " " + i;
                String email = "email" + barrio + i + "@example.com";
                String direccion = "Calle Falsa " + (100 + i);
                LocalDate fechaNacimiento = LocalDate.of(1990 + i % 10, i % 12 + 1, i % 28 + 1);
                EstadoBeneficiario estado = i % 2 == 0 ? EstadoBeneficiario.ACTIVO : EstadoBeneficiario.SUSPENDIDO;
                String contrasenia = "123";
                // ID ficticio
                Integer id = i + (barrio.ordinal() * 100);

                // Crear y agregar DtBeneficiario a la lista
                DtBeneficiario dtBeneficiario = new DtBeneficiario(id, nombre, email, direccion, fechaNacimiento, estado, barrio, contrasenia);
                beneficiariosDT.add(dtBeneficiario);
            }
        }
    }

    private void cargarDistribuciones() {
        // Crear distribuciones de prueba asociadas a beneficiarios y donaciones
        for (int i = 0; i < 8; i++) {
            DtBeneficiario beneficiario = beneficiariosDT.get(i);
            DTDonacion donacion = donacionesDT.get(i);

            distribucionesDT.add(new DtDistribucion(
                    null,
                    LocalDateTime.now().minusDays(i + 3),
                    LocalDateTime.now().minusDays(i + 1),
                    EstadoDistribucion.ENTREGADO,
                    donacion.getId(),
                    beneficiario.getId()
            ));
        }

        // Agregar más distribuciones si es necesario
        for (int i = 9; i <= 15; i++) {
            DtBeneficiario beneficiario = beneficiariosDT.get(i % beneficiariosDT.size());
            DTDonacion donacion = donacionesDT.get(i % donacionesDT.size());

            distribucionesDT.add(new DtDistribucion(
                    null,
                    LocalDateTime.now().minusDays(i),
                    LocalDateTime.now().minusDays(i - 1),
                    EstadoDistribucion.EN_CAMINO,
                    donacion.getId(),
                    beneficiario.getId()
            ));
        }
    }

    public List<DTDonacion> getDonacionesDT() {
        return donacionesDT;
    }

    public List<DtBeneficiario> getBeneficiariosDT() {
        return beneficiariosDT;
    }

    public List<DtDistribucion> getDistribucionesDT() {
        return distribucionesDT;
    }
}
