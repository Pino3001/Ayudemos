package types;

public class DTFecha {
    private int dia;
    private int mes;
    private int anio;

    // Constructor
    public DTFecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    // Getters y Setters
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Método para validar la fecha
    public boolean esFechaValida() {
        if (anio < 0 || mes < 1 || mes > 12 || dia < 1 || dia > 31) {
            return false;
        }

        switch (mes) {
            case 4: case 6: case 9: case 11:
                return dia <= 30;
            case 2:
                return dia <= (esAnioBisiesto() ? 29 : 28);
            default:
                return true;
        }
    }

    // Método para verificar si es un año bisiesto
    private boolean esAnioBisiesto() {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }
}