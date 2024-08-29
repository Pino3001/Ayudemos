package types;

import java.util.Date;

public class DtDistribucion {
    private DTFechaHora preparacion;
    private DTFechaHora entrega;
    private EstadoDistribucion estado;

    public DtDistribucion(DTFechaHora preparacion, DTFechaHora entrega, EstadoDistribucion estado) {
        super();
        this.preparacion = preparacion;
        this.entrega = entrega;
        this.estado = estado;
    }

    public DtDistribucion(Date fechaPreparacion, Date fechaEntrega, EstadoDistribucion estado) {
    }

    //setter y getters
    public DTFechaHora getPreparacion() {
        return preparacion;
    }
    public void setPreparacion(DTFechaHora preparacion) {
        this.preparacion = preparacion;
    }
    public DTFechaHora getEntrega() {
        return entrega;
    }
    public void setEntrega(DTFechaHora entrega) {
        this.entrega = entrega;
    }
    public EstadoDistribucion getEstado() {
        return estado;
    }
    public void setEstado(EstadoDistribucion estado) {
        this.estado = estado;
    }


}
