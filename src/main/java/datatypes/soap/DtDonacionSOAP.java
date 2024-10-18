package datatypes.soap;

import datatypes.DTDonacion;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import utils.DateConverterSOAP;

import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)

public class DtDonacionSOAP {
    private Integer id;
    private XMLGregorianCalendar fechaIngresada;


    public DtDonacionSOAP(Integer id, XMLGregorianCalendar fechaIngresada){
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }
    public DtDonacionSOAP(DTDonacion dt) {
        this.id = dt.getId();
        this.fechaIngresada = DateConverterSOAP.toXMLGregorianCalendar(dt.getFechaIngresada().toLocalDate());
    }

    //Getters Y Setters
    public Integer getId() {
        return id;
    }

    public XMLGregorianCalendar getFechaIngresada() {
        return fechaIngresada;
    }
}