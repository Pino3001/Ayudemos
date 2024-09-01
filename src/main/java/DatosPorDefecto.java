import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import objects.Alimento;
import types.DTFecha;
import types.DTFechaHora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatosPorDefecto {

    private List<DTDonacion> donacionesDT;

    public DatosPorDefecto() {
        // Crear la lista de DTAlimento
        donacionesDT = new ArrayList<>();

        // Agregar elementos a la lista

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
            // Generar una fecha y hora distintas para cada donación
            int dia = i % 28 + 1;  // Generar días entre 1 y 28
            int mes = i % 12 + 1;  // Generar meses entre 1 y 12
            int anio = 2020 + (i % 5);  // Generar años entre 2020 y 2024
            int hora = i % 24;  // Generar horas entre 0 y 23
            int minuto = (i * 5) % 60;  // Generar minutos

            // Crear un nombre distinto para la descripción de cada donación
            String descripcion = "Donación " + i;

            // Generar una cantidad aleatoria
            int cantidad = i % 10 + 1;  // Generar cantidades entre 1 y 10

            // Agregar la nueva donación a la lista
            donacionesDT.add(new DTAlimento(i, LocalDateTime.now(), descripcion, cantidad));
        }
        for (int i = 1; i <= 100; i++) {
            // Generar una fecha y hora distintas para cada donación
            int dia = i % 28 + 1;  // Generar días entre 1 y 28
            int mes = i % 12 + 1;  // Generar meses entre 1 y 12
            int anio = 2020 + (i % 5);  // Generar años entre 2020 y 2024
            int hora = i % 24;  // Generar horas entre 0 y 23
            int minuto = (i * 5) % 60;  // Generar minutos

            // Crear un nombre distinto para la descripción de cada artículo
            String descripcion = "Artículo " + i;

            // Generar una cantidad aleatoria
            float peso = i % 10 + 1;  // Generar cantidades entre 1 y 10

            // Generar dimensiones aleatorias
            String dimensiones = (50 + i % 50) + "x" + (40 + i % 30) + "x" + (10 + i % 10);

            // Crear y agregar la nueva instancia de DTArticulo a la lista
            donacionesDT.add(new DTArticulo(i, LocalDateTime.now(), descripcion, peso, dimensiones));
        }
    }

    public List<DTDonacion> getAlimentosDT() {
        return donacionesDT;
    }

}