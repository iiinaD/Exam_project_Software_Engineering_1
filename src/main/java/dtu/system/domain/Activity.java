package dtu.system.domain;

import java.util.Calendar;

public class Activity {

    public int id;
    public String description;
    public Calendar budgetTime;
    public Calendar startWeek;
    public Calendar endWeek;

    public int getID(){
        return id;
    }

}
