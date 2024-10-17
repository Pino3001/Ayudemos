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
    // Constructor
    public DtDistribucionSOAP() {}


//    public DtDistribucionSOAP(Integer id,
//                          LocalDateTime fechaPreparacion,
//                          LocalDateTime fechaEntrega,
//                          EstadoDistribucion estado,
//                          int idDonacion,
//                          int idUsuario) {
//        this.id = id;
//        this.fechaPreparacionXML = DateConverterSOAP.toXMLGregorianCalendar(fechaPreparacion.toLocalDate());
//        this.fechaEntregaXML = DateConverterSOAP.toXMLGregorianCalendar(fechaEntrega.toLocalDate());
//        this.estado = estado;
//        this.idDonacion = idDonacion;
//        this.idUsuario = idUsuario;
//    }

    // Constructor para instanciar un DtDistribucionSOAP a partir de un DtDistribucion.
    public DtDistribucionSOAP(DtDistribucion dt) {
        this.id = dt.getId();
        this.fechaPreparacionXML = DateConverterSOAP.toXMLGregorianCalendar(dt.getFechaPreparacion().toLocalDate());
        if (dt.getFechaEntrega() != null)
            this.fechaEntregaXML = DateConverterSOAP.toXMLGregorianCalendar(dt.getFechaEntrega().toLocalDate());
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
    }

    public Barrio getBarrio() {
        return barrio;
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

}
