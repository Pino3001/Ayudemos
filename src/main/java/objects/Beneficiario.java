package objects;

import datatypes.DTArticulo;
import datatypes.DtBeneficiario;
import jakarta.persistence.*;
import types.Barrio;
import types.EstadoBeneficiario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("B")
public class Beneficiario extends Usuario {

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoBeneficiario estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Barrio barrio;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Distribucion> distribuciones = new ArrayList<>();

    // Constructor principal
    public Beneficiario(String nombre, String mail, String direccion, LocalDate fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
        super(nombre, mail);
        setDireccion(direccion);  // Usar setter para validación
        setFechaNacimiento(fechaNacimiento);  // Usar setter para validación
        this.estado = estado;
        this.barrio = barrio;
    }

    // Constructor protegido por defecto
    protected Beneficiario() {
        super();
    }

    // Métodos Getter y Setter
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public EstadoBeneficiario getEstado() {
        return estado;
    }

    public void setEstado(EstadoBeneficiario estado) {
        this.estado = estado;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public List<Distribucion> getDistribuciones() {
        return distribuciones;
    }

    public void setDistribuciones(List<Distribucion> distribuciones) {
        this.distribuciones = distribuciones;
    }

    // Método para añadir una distribución
    public void addDistribucion(Distribucion distribucion) {
        distribuciones.add(distribucion);
        distribucion.setBeneficiario(this);  // Mantener la consistencia bidireccional
    }

    // Remueve una distribucion de la lista
    public void removeDistribucion(Distribucion distribucion) {
        distribuciones.remove(distribucion);
        distribucion.setBeneficiario(null); // Mantener la relación bidireccional
    }

    //Devuelvo un DtBeneficiario con mis datos.
    @Override
    public DtBeneficiario getDtUsuario() {
        // Conversión correcta de la fecha
        return new DtBeneficiario(
                this.getId(),
                this.getNombre(),
                this.getMail(),
                this.getDireccion(),
                this.getFechaNacimiento(),
                this.getEstado(),
                this.getBarrio()
        );
    }
}
