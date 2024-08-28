package objects;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// SINGLE_TABLE mete tanto articulos como alimentos en la misma tabla.
public abstract class Donacion {
    @Id
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date fechaIngresada; // cambiado de DateTime a LocalDateTime.

    @OneToMany(mappedBy = "distribuciones", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Distribucion> distribuciones;

    //Constructor
    public Donacion(Integer id) {
        this.id = id;//
        this.fechaIngresada = new Date(); // TODO: Arreglar
    }

    public Donacion() {

    }

    // Getters Y Setters
    public Integer getId() {
        return id;
    }

    // TODO: Ver si se quiere poder modificar el Id, si no hay que dejarlo como final sin setter.
    public void setId(int id) {
        this.id = id;
    }


    public Date getFechaIngresada() {
        return fechaIngresada;
    }

    public void setFechaIngresada(Date fechaIngresada) {
        this.fechaIngresada = fechaIngresada;
    }

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