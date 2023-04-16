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
        /*
        https://stackoverflow.com/questions/8723862/calculate-number-of-weeks-in-a-given-year
         */

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 31);

        return cal.get(Calendar.WEEK_OF_YEAR);
    }

}