package Ayudemos.datatypes;

import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DateTime;
import Ayudemos.types.EstadoDistribucion;

import java.util.List;

public class DTDistribucion {
    private DateTime fechaPreparacion;
    private DateTime fechaEntrega;
    private EstadoDistribucion estado;
    private int idDonacion;
    private String emailBeneficiario;

    // Constructor
    public DTDistribucion(DateTime fechaPreparacion,
                          DateTime fechaEntrega,
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
    public DateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public DateTime getFechaEntrega() {
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