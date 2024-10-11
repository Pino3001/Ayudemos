package utils;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class DateConverterSOAP {

    public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate date) {
        try {
            GregorianCalendar gCal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convertir XMLGregorianCalendar a LocalDate
    public static LocalDate toLocalDate(XMLGregorianCalendar xmlCal) {
        try {
            return xmlCal.toGregorianCalendar().toZonedDateTime().toLocalDate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convertir XMLGregorianCalendar a LocalDateTime
    public static LocalDateTime toLocalDateTime(XMLGregorianCalendar xmlCal) {
        try {
            return xmlCal.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
