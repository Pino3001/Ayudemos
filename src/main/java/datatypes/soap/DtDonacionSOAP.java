package datatypes.soap;

import datatypes.DTDonacion;
import utils.DateConverterSOAP;

import javax.xml.datatype.XMLGregorianCalendar;

public class DtDonacionSOAP {
    private Integer id;
    private XMLGregorianCalendar fechaIngresada;

    //Constructor
    public DtDonacionSOAP() {
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