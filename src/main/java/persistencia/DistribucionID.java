package persistencia;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DistribucionID implements Serializable {

    private Integer donacionId;
    private Integer beneficiarioId;

    // Constructors, Getters, and Setters

    public DistribucionID() {
    }

    public DistribucionID(Integer donacionId, Integer beneficiarioId) {
        this.donacionId = donacionId;
        this.beneficiarioId = beneficiarioId;
    }

    public Integer getDonacionId() {
        return donacionId;
    }

    public void setDonacionId(Integer donacionId) {
        this.donacionId = donacionId;
    }

    public Integer getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Integer beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistribucionID that = (DistribucionID) o;

        if (!donacionId.equals(that.donacionId)) return false;
        return beneficiarioId.equals(that.beneficiarioId);
    }

    @Override
    public int hashCode() {
        int result = donacionId.hashCode();
        result = 31 * result + beneficiarioId.hashCode();
        return result;
    }
}

//Tiene que implementar los getters y setters
//    public int getClase() {
//        return clase;
//    }
//
//    public void setClase(int clase) {
//        this.clase = clase;
//    }
//
//    public String getSocio() {
//        return socio;
//    }
//
//    public void setSocio(String socio) {
//        this.socio = socio;
//    }

    //Tiene  que tener los métodos hashCode y equals
    /*@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + clase;
        result = prime * result + ((socio == null) ? 0 : socio.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DistribucionID other = (DistribucionID) obj;
        if (clase != other.clase)
            return false;
        if (socio == null) {
            if (other.socio != null)
                return false;
        } else if (!socio.equals(other.socio))
            return false;
        return true;
    }*/


