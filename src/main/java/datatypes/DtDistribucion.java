package datatypes;

import datatypes.soap.DtDistribucionSOAP;
import types.EstadoDistribucion;
import utils.DateConverterSOAP;

import java.time.LocalDateTime;
import java.util.Objects;

public class DtDistribucion {
    private Integer id;
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EstadoDistribucion estado;
    private int idDonacion;
    private int idUsuario;

    // Constructor
    public DtDistribucion(){
        this.idDonacion = 0;
        this.idUsuario = 0;
        this.fechaPreparacion = LocalDateTime.now();
        this.fechaEntrega = LocalDateTime.now();
        this.estado=EstadoDistribucion.PENDIENTE;
        this.id = 0;
    }
    public DtDistribucion(Integer id,
                          LocalDateTime fechaPreparacion,
                          LocalDateTime fechaEntrega,
                          EstadoDistribucion estado,
                          int idDonacion,
                          int idUsuario) {
        this.id = id;
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idDonacion = idDonacion;
        this.idUsuario = idUsuario;
    }

    // Constructor para instanciar un DtDistribucion a partir de un DtDistribucionSOAP.
    public DtDistribucion(DtDistribucionSOAP dt) {
        this.id = dt.getId();
        this.fechaPreparacion = DateConverterSOAP.toLocalDateTime(dt.getFechaPreparacion());
        this.fechaEntrega = DateConverterSOAP.toLocalDateTime(dt.getFechaEntrega());
        this.estado = dt.getEstado();
        this.idDonacion = dt.getIdDonacion();
        this.idUsuario = dt.getIdUsuario();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public LocalDateTime getFechaPreparacion() {
        return fechaPreparacion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public EstadoDistribucion getEstado() {
        return estado;
    }

    public int getIdDonacion() {
        return idDonacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Sobrescribir el método toString para mostrar los datos correctos en el JComboBox
    @Override
    public String toString() {
        return "ID Donación: " + idDonacion + ", Beneficiario: " + idUsuario + ", Estado: " + estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaPreparacion, fechaEntrega, estado, idDonacion, idUsuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Si es el mismo objeto
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  // Si el objeto es null o no es de la misma clase
        }

        DtDistribucion that = (DtDistribucion) obj;

        // Comparar idDonacion, idUsuario, fechaPreparacion, fechaEntrega y estado
        return  id.equals(that.id) &&
                idDonacion == that.idDonacion &&
                idUsuario == that.idUsuario &&
                Objects.equals(fechaPreparacion, that.fechaPreparacion) &&
                Objects.equals(fechaEntrega, that.fechaEntrega) &&
                estado == that.estado;
    }

}