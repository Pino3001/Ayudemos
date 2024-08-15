package Ayudemos.objects;

import Ayudemos.types.DTFecha;
import Ayudemos.types.EstadoBeneficiario;
import Ayudemos.types.Barrio;

public class Beneficiario extends Usuario {
    private String direccion;
    private DTFecha fechaNacimiento;
    private EstadoBeneficiario estado;
    private Barrio barrio;
    // Crear dependencias

    // Constructor
    public Beneficiario(String nombre, String mail, String direccion, DTFecha fechaNacimiento, EstadoBeneficiario estado, Barrio barrio){
        super(nombre, mail);
        this.direccion=direccion;
        this.fechaNacimiento= fechaNacimiento;
        this.estado= estado;
        this.barrio= barrio;
    }

    // Getters Y Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public DTFecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(DTFecha fechaNacimiento) {
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
}
