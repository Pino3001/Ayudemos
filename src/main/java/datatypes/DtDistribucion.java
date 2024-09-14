package datatypes;

import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class DtDistribucion {
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EstadoDistribucion estado;
    private int idDonacion;
    private int idUsuario;

    // Constructor
    public DtDistribucion(LocalDateTime fechaPreparacion,
                          LocalDateTime fechaEntrega,
                          EstadoDistribucion estado,
                          int idDonacion,
                          int idUsuario) {
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idDonacion = idDonacion;
        this.idUsuario = idUsuario;
    }

    // Getters
    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public EstadoDistribucion getEstado() {
        return estado;
    }

    public int getIdDonacion() {
        return idDonacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Sobrescribir el método toString para mostrar los datos correctos en el JComboBox
    @Override
    public String toString() {
        return "ID Donación: " + idDonacion + ", Beneficiario: " + idUsuario + ", Estado: " + estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaPreparacion, fechaEntrega, estado, idDonacion, idUsuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Si es el mismo objeto
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  // Si el objeto es null o no es de la misma clase
        }

        DtDistribucion that = (DtDistribucion) obj;

        // Comparar idDonacion, idUsuario, fechaPreparacion, fechaEntrega y estado
        return idDonacion == that.idDonacion &&
                idUsuario == that.idUsuario &&
                Objects.equals(fechaPreparacion, that.fechaPreparacion) &&
                Objects.equals(fechaEntrega, that.fechaEntrega) &&
                estado == that.estado;
    }

}