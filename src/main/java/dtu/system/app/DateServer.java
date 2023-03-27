package dtu.system.app;
//code modified from class example library:DateServer
import java.util.Calendar;
import java.util.GregorianCalendar;
public class DateServer {

    public Calendar getDate() {
        Calendar calendar = new GregorianCalendar();
        return new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
    }
}