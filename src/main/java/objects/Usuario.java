package objects;

public abstract class Usuario {
    private String nombre;
    private String mail;

    // Constructor
    public Usuario(String nombre, String mail){
        this.nombre=nombre;
        this.mail= mail;
    }

    // Getters y Setters
    public String getNombre(){
        return nombre;//
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail(){
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
