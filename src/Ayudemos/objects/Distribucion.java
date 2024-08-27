package Ayudemos.objects;

import Ayudemos.types.DTFechaHora;
import Ayudemos.types.EstadoDistribucion;

import java.util.List;

public class Distribucion {
    private DTFechaHora fechaPreparacion;
    private DTFechaHora fechaEntrega;
    private EstadoDistribucion estado;
    //TODO: Hay que crear la dependencia a la Donacion, el Repartidor Y el Beneficiario!

    // Dependencias
    // Lista de donaciones de una distribución.
    private Donacion donacion;//
    // Una distribución está destinada a un beneficiario específico.
    private Beneficiario beneficiario;

    //Constructor
    public Distribucion(DTFechaHora fechaPreparacion,
                        DTFechaHora fechaEntrega,
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