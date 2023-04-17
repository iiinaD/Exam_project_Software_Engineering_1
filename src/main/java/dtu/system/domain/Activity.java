package dtu.system.domain;

import dtu.system.app.DateServer;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;


public class Activity {

    public String id;
    public String description;
    public HalfHours budgetTime;
    public int startWeek;
    public int endWeek;
    public int startYear;
    public int endYear;
    public Project parentProject;
    private ArrayList<Worker> WorkerList = new ArrayList<>();
    private int budgetWeeks;

    public Activity(String id, Project parentProject){
        this.id = id;
        this.parentProject = parentProject;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBudgetTime(HalfHours budgetTime){
        this.budgetTime = budgetTime;
    }

    public void setStartEndWeekAndYears(int startWeek, int endWeek, int startYear, int endYear){
        //Jonas
        this.startWeek = startWeek;
        this.startYear = startYear;
        this.endWeek = endWeek;
        this.endYear = endYear;
        calculateBudgetWeek();
    }

    private void calculateBudgetWeek() {
        //Jonas
        int weeksInYear = 52;
        int years = (endYear - startYear)*weeksInYear;
        this.budgetWeeks = endWeek - startWeek + years;
    }

    public int getBudgetWeeks() {
        return budgetWeeks;
    }

    public String getActivityId() {
        // Danny
        return id;
    }

    public Project getParentProject(){
        // Gee
        return parentProject;
    }


    public void addWorker(Worker worker){
        // Daniel
        WorkerList.add(worker);
    }

    public List<Worker> getWorkerList() {
        // Daniel
        return WorkerList;
    }

}
