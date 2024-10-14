package datatypes;

public class DtReporteZona {

    private final String barrio;
    private final Integer totalDistribuciones;
    private final Integer totalBeneficiarios;

    public DtReporteZona(){
        this.barrio = "";
        this.totalDistribuciones = 0;
        this.totalBeneficiarios = 0;
    }
    public DtReporteZona(String barrio, Integer totalDistribuciones, Integer totalBeneficiarios) {
        this.barrio = barrio;
        this.totalDistribuciones = totalDistribuciones;
        this.totalBeneficiarios = totalBeneficiarios;
    }

    public String getBarrio() {
        return barrio;
    }

    public Integer getTotalDistribuciones() {
        return totalDistribuciones;
    }

    public Integer getTotalBeneficiarios() {
        return totalBeneficiarios;
    }

    @Override
    public String toString() {
        return barrio.toString().toUpperCase() + ", " + totalDistribuciones.toString() + " distrubiciones, " + totalBeneficiarios.toString() + ", beneficiarios";
    }
}
