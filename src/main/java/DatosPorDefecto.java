import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import objects.Alimento;
import types.DTFecha;
import types.DTFechaHora;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatosPorDefecto {

    private List<DTDonacion> donacionesDT;

    public DatosPorDefecto() {
        // Crear la lista de DTAlimento
        donacionesDT = new ArrayList<>();

        // Agregar elementos a la lista

        DTFecha dtFecha1 = new DTFecha(15, 8, 2021);
        Date dtFechaHora1 = new Date();
        donacionesDT.add(new DTAlimento(1, dtFechaHora1, "Manzanas", 10));

        DTFecha dtFecha2 = new DTFecha(22, 11, 2022);
        Date dtFechaHora2 = new Date();
        donacionesDT.add(new DTAlimento(2, dtFechaHora2, "Pan", 20));

        DTFecha dtFecha3 = new DTFecha(3, 1, 2023);
        Date dtFechaHora3 = new Date();
        donacionesDT.add(new DTAlimento(3, dtFechaHora3, "Leche", 15));

        DTFecha dtFecha4 = new DTFecha(10, 5, 2020);
        Date dtFechaHora4 = new Date();
        DTArticulo dtArticulo1 = new DTArticulo(0, dtFechaHora1, "Mesa", 10, "120x60x75");
        donacionesDT.add(dtArticulo1);

        DTFecha dtFecha5 = new DTFecha(15, 8, 2021);
        Date dtFechaHora5 = new Date();
        DTArticulo dtArticulo2 = new DTArticulo(1, dtFechaHora2, "Silla", 5, "45x45x90");
        donacionesDT.add(dtArticulo2);

        DTFecha dtFecha6 = new DTFecha(22, 11, 2022);
        Date dtFechaHora6 = new Date();
        DTArticulo dtArticulo3 = new DTArticulo(2, dtFechaHora3, "Lámpara", 1, "20x20x40");
        donacionesDT.add(dtArticulo3);

        DTFecha dtFecha7 = new DTFecha(3, 1, 2023);
        Date dtFechaHora7 = new Date();
        DTArticulo dtArticulo4 = new DTArticulo(3, dtFechaHora4, "Espejo", 7, "80x60x5");
        donacionesDT.add(dtArticulo4);

        for (int i = 1; i <= 100; i++) {
            // Generar una fecha y hora distintas para cada donación
            int dia = i % 28 + 1;  // Generar días entre 1 y 28
            int mes = i % 12 + 1;  // Generar meses entre 1 y 12
            int anio = 2020 + (i % 5);  // Generar años entre 2020 y 2024
            int hora = i % 24;  // Generar horas entre 0 y 23
            int minuto = (i * 5) % 60;  // Generar minutos

            DTFecha dtFecha = new DTFecha(dia, mes, anio);
            Date dtFechaHora = new Date();

            // Crear un nombre distinto para la descripción de cada donación
            String descripcion = "Donación " + i;

            // Generar una cantidad aleatoria
            int cantidad = i % 10 + 1;  // Generar cantidades entre 1 y 10

            // Agregar la nueva donación a la lista
            donacionesDT.add(new DTAlimento(i, dtFechaHora, descripcion, cantidad));
        }
        for (int i = 1; i <= 100; i++) {
            // Generar una fecha y hora distintas para cada donación
            int dia = i % 28 + 1;  // Generar días entre 1 y 28
            int mes = i % 12 + 1;  // Generar meses entre 1 y 12
            int anio = 2020 + (i % 5);  // Generar años entre 2020 y 2024
            int hora = i % 24;  // Generar horas entre 0 y 23
            int minuto = (i * 5) % 60;  // Generar minutos

            DTFecha dtFecha = new DTFecha(dia, mes, anio);
            Date dtFechaHora = new Date();

            // Crear un nombre distinto para la descripción de cada artículo
            String descripcion = "Artículo " + i;

            // Generar una cantidad aleatoria
            int cantidad = i % 10 + 1;  // Generar cantidades entre 1 y 10

            // Generar dimensiones aleatorias
            String dimensiones = (50 + i % 50) + "x" + (40 + i % 30) + "x" + (10 + i % 10);

            // Crear y agregar la nueva instancia de DTArticulo a la lista
            donacionesDT.add(new DTArticulo(i, dtFechaHora, descripcion, cantidad, dimensiones));
        }
    }

    public List<DTDonacion> getAlimentosDT() {
        return donacionesDT;
    }

}