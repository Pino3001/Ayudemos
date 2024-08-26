package Ayudemos.objects;

import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DateTime;
import Ayudemos.types.EstadoDistribucion;

public class Distribucion {
    private DateTime fechaPreparacion;
    private DateTime fechaEntrega;
    private EstadoDistribucion estado;

    // Dependencias
    // Lista de donaciones de una distribución.
    private Donacion donacion;
    // Una distribución está destinada a un beneficiario específico.
    private Beneficiario beneficiario;

    //Constructor
    public Distribucion(DateTime fechaPreparacion,
                        DateTime fechaEntrega,
                        EstadoDistribucion estado,
                        Donacion donacion,
                        Beneficiario beneficiario) {
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.donacion = donacion;
        this.beneficiario = beneficiario;
    }

    //Getters y Setters
    public DateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(DateTime fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public DateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(DateTime fechaEntrega) {
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

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
}