package types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private LocalDateTime dateTime;

    // Constructor que inicializa dateTime con la fecha y hora actuales
    public DateTime() {
        this.dateTime = LocalDateTime.now();
    }

    //transforma un tipo DateTime en un DTFechaHora
    public DTFechaHora convertir() {
        LocalDateTime ldt = this.dateTime;

        // Extraer los valores de fecha
        int dia = ldt.getDayOfMonth();
        int mes = ldt.getMonthValue();
        int anio = ldt.getYear();

        // Crear el objeto DTFecha
        DTFecha dtFecha = new DTFecha(dia, mes, anio);

        // Extraer la hora y los minutos
        int hora = ldt.getHour();
        int minuto = ldt.getMinute();

        // Crear y retornar el objeto DTFechaHora
        return new DTFechaHora(dtFecha, hora, minuto);
    }

    //metodo para obtener la fecha y hora en formato String
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
