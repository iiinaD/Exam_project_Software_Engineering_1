package dtu.system.domain;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity {

    public String id;
    public String description;
    public HalfHours budgetTime;
    public Calendar startWeek;
    public Calendar endWeek;
    public Project parentProject;
    public ArrayList<WorkerActivity> workerActivityList = new ArrayList<WorkerActivity>(); //Stores WorkerActivity Objects

    public Activity(String id, Project parentProject){
        this.id = id;
        this.parentProject = parentProject;
    }

    public void setID(String id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBudgetTime(HalfHours budgetTime){
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

    public Project getParentProject(){
        return parentProject;
    }

    public void addWorkerActivity(WorkerActivity workerActivity){
        workerActivityList.add(workerActivity);
    }

}
