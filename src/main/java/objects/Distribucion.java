package objects;

import persistencia.DistribucionID;
import types.DTFechaHora;
import types.EstadoDistribucion;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@IdClass(DistribucionID.class)
public class Distribucion {

    @Temporal(TemporalType.DATE)
    private Date fechaPreparacion;

    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;

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
    public Distribucion(Date fechaPreparacion,
                        Date fechaEntrega,
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
    public DTFechaHora getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(DTFechaHora fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public DTFechaHora getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(DTFechaHora fechaEntrega) {
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