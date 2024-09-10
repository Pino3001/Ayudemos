package datatypes;

import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.List;

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
}