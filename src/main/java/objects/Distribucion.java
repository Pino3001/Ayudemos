package objects;

import persistencia.DistribucionID;
import types.DTFechaHora;
import types.EstadoDistribucion;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(DistribucionID.class)
public class Distribucion {

    @Temporal(TemporalType.DATE)
    private LocalDateTime fechaPreparacion;

    @Temporal(TemporalType.DATE)
    private LocalDateTime fechaEntrega;

    private EstadoDistribucion estado;
    //TODO: Hay que crear la dependencia a la Donacion, el Repartidor Y el Beneficiario!

    // Dependencias
    // Lista de donaciones de una distribución.
    @Id
    @ManyToOne
    @JoinColumn(
            insertable = false,
            updatable = false
    )
    private Donacion donacion;//

    // Una distribución está destinada a un beneficiario específico.
    @Id
    @ManyToOne
    @JoinColumn(
            insertable = false,
            updatable = false
    )
    private Beneficiario beneficiario;

    //Constructor
    public Distribucion(LocalDateTime fechaPreparacion,
                        LocalDateTime fechaEntrega,
                        EstadoDistribucion estado,
                        Donacion donacion,
                        Beneficiario beneficiario) {
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.donacion = donacion;
        this.beneficiario = beneficiario;
    }

    public Distribucion() {

    }

    //Getters y Setters
    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(LocalDateTime fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public EstadoDistribucion getEstado() {
        return estado;
    }

    public void setEstado(EstadoDistribucion estado) {
        this.estado = estado;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }
}