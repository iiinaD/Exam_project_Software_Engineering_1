package dtu.system.domain;

public class Date {
    private int week;
    private int year;
    private int weeksInYear = 52;

    public Date(int week,int year){
        // Jonas
        this.week = week;
        this.year = year;
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
