package Ayudemos.datatypes;

import Ayudemos.types.DTFechaHora;
import Ayudemos.types.EstadoDistribucion;

import java.util.List;

public class DTDistribucion {
    private DTFechaHora fechaPreparacion;
    private DTFechaHora fechaEntrega;
    private EstadoDistribucion estado;
    private int idDonacion;
    private String emailBeneficiario;

    // Constructor
    public DTDistribucion(DTFechaHora fechaPreparacion,
                          DTFechaHora fechaEntrega,
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

    // Getters
    public DTFechaHora getFechaPreparacion() {
        return fechaPreparacion;
    }

    public DTFechaHora getFechaEntrega() {
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
}