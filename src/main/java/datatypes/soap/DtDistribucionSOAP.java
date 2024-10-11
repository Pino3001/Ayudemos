package datatypes.soap;

import datatypes.DtDistribucion;
import types.EstadoDistribucion;
import utils.DateConverterSOAP;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

public class DtDistribucionSOAP {
    private Integer id;
    private XMLGregorianCalendar fechaPreparacionXML;
    private XMLGregorianCalendar fechaEntregaXML;
    private EstadoDistribucion estado;
    private int idDonacion;
    private int idUsuario;

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
        this.fechaEntregaXML = DateConverterSOAP.toXMLGregorianCalendar(dt.getFechaEntrega().toLocalDate());
        this.estado = dt.getEstado();
        this.idDonacion = dt.getIdDonacion();
        this.idUsuario = dt.getIdUsuario();
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
