package datatypes;

import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.List;

public class DtDistribucion {
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EstadoDistribucion estado;
    private int idDonacion;
    private String emailBeneficiario;

    // Constructor
    public DtDistribucion(LocalDateTime fechaPreparacion,
                          LocalDateTime fechaEntrega,
                          EstadoDistribucion estado,
                          int idDonacion,
                          String nombreBeneficiario,
                          String emailBeneficiario) {
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idDonacion = idDonacion;
        this.emailBeneficiario = emailBeneficiario;
    }

    // Constructor
    public DtDistribucion(LocalDateTime fechaPreparacion,
                          LocalDateTime fechaEntrega,
                          EstadoDistribucion estado,
                          int idDonacion,
                          String emailBeneficiario) {
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idDonacion = idDonacion;
        this.emailBeneficiario = emailBeneficiario;
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

    public String getEmailBeneficiario() {
        return emailBeneficiario;
    }

    // Sobrescribir el método toString para mostrar los datos correctos en el JComboBox
    @Override
    public String toString() {
        return "ID Donación: " + idDonacion + ", Beneficiario: " + emailBeneficiario + ", Estado: " + estado;
    }
}