package datatypes;

import datatypes.soap.DtDistribucionSOAP;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import types.Barrio;
import types.EstadoDistribucion;
import utils.DateConverterSOAP;

import java.time.LocalDateTime;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)

public class DtDistribucion {
    private Integer id;
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaEntrega;
    private EstadoDistribucion estado;
    private int idDonacion;
    private String descripcionDonacion;
    private int idUsuario;
    private String nombreUsuario;
    private String emailUsuario;
    private String direccionUsuario;
    private Barrio barrio;

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDescripcionDonacion() {
        return descripcionDonacion;
    }

    public void setDescripcionDonacion(String descripcionDonacion) {
        this.descripcionDonacion = descripcionDonacion;
    }

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
        this.descripcionDonacion = dt.getDescripcionDonacion();
        this.idUsuario = dt.getIdUsuario();
        this.nombreUsuario = dt.getNombreUsuario();
        this.emailUsuario = dt.getEmailUsuario();
        this.direccionUsuario = dt.getDireccionUsuario();
        this.barrio = dt.getBarrio();
    }

    public DtDistribucion(Integer id, LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega, EstadoDistribucion estado, int idDonacion, String descripcionDonacion, int idUsuario, String nombreUsuario, String emailUsuario, String direccionUsuario, Barrio barrio) {
        this.id = id;
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idDonacion = idDonacion;
        this.descripcionDonacion = descripcionDonacion;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.direccionUsuario = direccionUsuario;
        this.barrio = barrio;
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
        return "ID Donación: " + idDonacion + ", Beneficiario: " + idUsuario + ", Estado: " + estado + ", Nombreuser, " + nombreUsuario;
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