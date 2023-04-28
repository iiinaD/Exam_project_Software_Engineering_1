package dtu.system.domain;

import dtu.system.app.DateServer;

public class Date {
    private int week;
    private int year;
    private int weeksInYear;

    public Date(int week,int year){
        // Jonas
        this.week = week;
        this.year = year;
        this.weeksInYear =  new DateServer().getNumberOfWeeksInYear(year);
    }

    public String stringOfDate(){
        // Jonas
        return "Week " + week + " of " + year;
    }

    public int calculateWeeksToStartWeek(Date compareDate){
        // Jonas
        int years = (compareDate.year - year)*weeksInYear;
        return compareDate.week - week + years;
    }

}
