package datatypes;

import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.List;

public class DtDistribucion {
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EstadoDistribucion estado;
    private Integer idDonacion;
    private Integer idBeneficiario;

    // Constructor
    public DtDistribucion(LocalDateTime fechaPreparacion,
                          LocalDateTime fechaEntrega,
                          EstadoDistribucion estado,
                          Integer idDonacion,
                          String nombreBeneficiario,
                          Integer emailBeneficiario) {
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idDonacion = idDonacion;
        this.idBeneficiario = emailBeneficiario;
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

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    @Override
    public String toString() {
        return this.fechaPreparacion + " " + this.fechaEntrega + " " + this.estado + " ";
    }
}