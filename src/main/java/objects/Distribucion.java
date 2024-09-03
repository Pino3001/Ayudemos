package objects;

import datatypes.DTDonacion;
import datatypes.DtDistribucion;
import jakarta.persistence.*;
import objects.Beneficiario;
import objects.Donacion;
import types.EstadoDistribucion;

import java.time.LocalDateTime;

@Entity
public class Distribucion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  // Estrategia de generación de ID automático
    private Integer id;

    private LocalDateTime fechaPreparacion;

    private LocalDateTime fechaEntrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDistribucion estado;

    @ManyToOne
    @JoinColumn(name = "donacion_id", nullable = false)
    private Donacion donacion;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;

    // Constructor
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

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public DtDistribucion getDtDistribucion(){
        return new DtDistribucion(this.getFechaPreparacion(), this.getFechaEntrega(), this.getEstado(), this.getDonacion().getId(), this.getBeneficiario().getNombre(), this.getBeneficiario().getId());
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
}
