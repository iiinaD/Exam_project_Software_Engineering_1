package dtu.system.domain;

import java.util.ArrayList;

import java.util.List;


public class Activity {

    private String id;
    private String description;
    private HalfHours budgetTime;
    private int startWeek;
    private int endWeek;
    private int startYear;
    private int endYear;
    private Project parentProject;
    private ArrayList<Worker> workerList = new ArrayList<>();
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

    public void calculateBudgetWeek() {
        //Jonas
        this.budgetWeeks = calculateWeeksToStartWeek(endWeek, startYear);
    }

    private int calculateWeeksToStartWeek(int endWeek, int startYear){
        // Jonas
        int weeksInYear = 52;
        int years = (endYear - startYear)*weeksInYear;
        return endWeek - startWeek + years;
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
        workerList.add(worker);
    }

    public List<Worker> getWorkerList() {
        // Daniel
        return workerList;
    }

    public Boolean isWorkerAssigned(String initials) {
        //Jonas
        for (Worker worker : workerList){
            if (worker.getInitials().equals(initials)){
                return true;
            }
        }
        return false;
    }

    public boolean isInGivenWeekAndYear(int week, int year) {
        // Jonas
        int weeks = calculateWeeksToStartWeek(week, year);
        if (weeks >= 0 && weeks <= budgetWeeks){
            return true;
        }
        return false;
    }

    public String getStartDate() {
        // Jonas
        return stringOfDate(startWeek, endYear);
    }

    public String getEndDate() {
        // Jonas
        return stringOfDate(endWeek, endYear);
    }

    private String stringOfDate(int week, int year){
        // Jonas
        return "Week " + week + " of " + year;
    }

    public HalfHours getBudgetTime() {
        // Jonas
        return budgetTime;
    }

    public String getDescription() {
        // Jonas
        return description;
    }
}
