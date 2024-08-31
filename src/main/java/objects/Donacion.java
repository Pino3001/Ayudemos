package objects;


import datatypes.DTDonacion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // SINGLE_TABLE mete tanto articulos como alimentos en la misma tabla.
public abstract class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  // Estrategia de generación de ID automatico
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate fechaIngresada; // cambiado de DateTime a LocalDateTime.

    @OneToMany(mappedBy = "distribuciones", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Distribucion> distribuciones = new ArrayList<>();

    //Constructor
    public Donacion() {
        this.fechaIngresada = LocalDate.now();
    }

    // Getters Y Setters
    public Integer getId() {
        return id;
    }


    public LocalDate getFechaIngresada() {
        return fechaIngresada;
    }

    public void setFechaIngresada(LocalDate fechaIngresada) {
        this.fechaIngresada = fechaIngresada;
    }

    // Metodo abtracto para pasarse como datatype
    public abstract DTDonacion getDTDonacion();

    //Metodos de clase:
    //Inserta una distribucion a la lista de ditribuciones asociadas
    public void addDistribucion(Distribucion distribucion) {
        distribuciones.add(distribucion);
    }

    // Modifico para mostrar en los comboBox, Ver si quiero mostrar algun dato mas en el combo.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donacion donacion = (Donacion) o;
        return id != null && id.equals(donacion.id);
    }

    // Sobreescribo para poder usar el set del manejador y compararlo solo por ID.
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}