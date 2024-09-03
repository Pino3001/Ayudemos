package datatypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DTDonacion {

    private final Integer id;
    private final LocalDateTime fechaIngresada;

    //Consutructor
    public DTDonacion(Integer id, LocalDateTime fechaIngresada) {
        this.id = id;//
        this.fechaIngresada = fechaIngresada;
    }

    //Getters Y Setters
    public Integer getId() {
        return id;
    }

    public LocalDateTime getFechaIngresada() {
        return fechaIngresada;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DTDonacion that = (DTDonacion) obj;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}