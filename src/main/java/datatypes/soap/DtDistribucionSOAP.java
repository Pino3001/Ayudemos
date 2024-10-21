package datatypes.soap;

import datatypes.DtDistribucion;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import types.Barrio;
import types.EstadoDistribucion;
import utils.DateConverterSOAP;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtDistribucionSOAP {
    private Integer id;
    private XMLGregorianCalendar fechaPreparacionXML;
    private XMLGregorianCalendar fechaEntregaXML;
    private EstadoDistribucion estado;
    private int idDonacion;
    private String descripcionDonacion;
    private int idUsuario;
    private String nombreUsuario;
    private String emailUsuario;
    private String direccionUsuario;
    private Barrio barrio;
    private String fechaEntregaString;
    private String fechaPreparacionString;
    // Constructor
    public DtDistribucionSOAP() {}

    // Constructor para instanciar un DtDistribucionSOAP a partir de un DtDistribucion.
    public DtDistribucionSOAP(DtDistribucion dt) {
        this.id = dt.getId();
        this.fechaPreparacionXML = DateConverterSOAP.toXMLGregorianCalendarTime(dt.getFechaPreparacion());
        if (dt.getFechaEntrega() != null)
            this.fechaEntregaXML = DateConverterSOAP.toXMLGregorianCalendarTime(dt.getFechaEntrega());
        this.estado = dt.getEstado();
        this.idDonacion = dt.getIdDonacion();
        this.idUsuario = dt.getIdUsuario();
        this.estado = dt.getEstado();
        this.idDonacion = dt.getIdDonacion();
        this.descripcionDonacion = dt.getDescripcionDonacion();
        this.idUsuario = dt.getIdUsuario();
        this.nombreUsuario = dt.getNombreUsuario();
        this.emailUsuario = dt.getEmailUsuario();
        this.direccionUsuario = dt.getDireccionUsuario();
        this.barrio = dt.getBarrio();
        this.fechaEntregaString = getFechaEntregaStr();
        this.fechaPreparacionString = getFechaPreparacionStr();
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public String getFechaPreparacionString() {
        return fechaPreparacionString;
    }

    public void setFechaPreparacionString(String fechaPreparacionString) {
        this.fechaPreparacionString = fechaPreparacionString;
    }

    public String getFechaEntregaString() {
        return fechaEntregaString;
    }

    public void setFechaEntregaString(String fechaEntregaString) {
        this.fechaEntregaString = fechaEntregaString;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }


    public String getEmailUsuario() {
        return emailUsuario;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getDescripcionDonacion() {
        return descripcionDonacion;
    }


    // Getters
    public Integer getId() {
        return id;
    }

    public XMLGregorianCalendar getFechaPreparacion() {
        return fechaPreparacionXML;
    }

    public XMLGregorianCalendar getFechaEntrega() {
        return fechaEntregaXML;
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

    public String getFechaPreparacionStr() {
        if (fechaPreparacionXML != null) {
            LocalDateTime fechaPreparacion = fechaPreparacionXML.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            return fechaPreparacion.format(formatter);
        }
        return "-";
    }

    public String getFechaEntregaStr() {
        if (fechaEntregaXML != null) {
            LocalDateTime fechaEntrega = fechaEntregaXML.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            return fechaEntrega.format(formatter);
        }
        return "-";
    }
}
