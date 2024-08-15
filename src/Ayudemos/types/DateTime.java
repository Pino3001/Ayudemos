package Ayudemos.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private LocalDateTime dateTime;

    // Constructor que inicializa dateTime con la fecha y hora actuales
    public DateTime() {
        this.dateTime = LocalDateTime.now();
    }

    // Método para obtener la fecha y hora en formato string
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    // Getter
    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
