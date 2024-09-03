package datatypes;

public class DtUsuario {
    private final Integer id;
    private final String nombre;
    private final String mail;

    public DtUsuario(Integer id, String nombre, String mail) {
        this.id = id;
        this.nombre = nombre;//
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public Integer getId() { return id; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DtUsuario that = (DtUsuario) obj;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
