package dtu.system.domain;

import java.util.Calendar;

public class Activity {

    public String id;
    public String description;
    public int budgetTime;
    public Calendar startWeek;
    public Calendar endWeek;
    //public Project parentProject;

    public Activity(String id){
        this.id = id;
    }

    public void setID(String id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBudgetTime(int budgetTime){
        this.budgetTime = budgetTime;
    }

    public void setStartWeek(Calendar startWeek){
        this.startWeek = startWeek;
    }

    public void setEndWeek(Calendar endWeek){
        this.endWeek = endWeek;
    }

    public String getActivityId() {
        // Danny
        return id;
    }

}
