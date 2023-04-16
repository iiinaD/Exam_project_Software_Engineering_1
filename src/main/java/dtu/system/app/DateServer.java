package dtu.system.app;
//code modified from class example library:DateServer
import java.util.Calendar;
import java.util.GregorianCalendar;
public class DateServer {

    public Calendar calendar = new GregorianCalendar();

    public Calendar getDate() {
        return new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
    }

    public int getCurrentWeek(){
        //Jonas
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getCurrentYear(){
        //Jonas
        return calendar.get(Calendar.YEAR);
    }

    public int getNumberOfWeeksInYear(int year){
        return 52;
    }

}