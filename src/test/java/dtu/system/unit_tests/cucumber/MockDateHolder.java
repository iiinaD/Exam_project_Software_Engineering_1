package dtu.system.unit_tests.cucumber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;


import dtu.system.app.Application;
import dtu.system.app.DateServer;

public class MockDateHolder {

    DateServer dateServer = mock(DateServer.class);

    public MockDateHolder(Application app) {
        GregorianCalendar calendar = new GregorianCalendar();
        setDate(calendar);
        app.setDateServer(dateServer);
    }

    public void setDate(Calendar calendar) {
        Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        when(this.dateServer.getDate()).thenReturn(c);
    }


}