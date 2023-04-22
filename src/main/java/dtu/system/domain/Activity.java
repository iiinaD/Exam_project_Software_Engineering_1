package dtu.system.domain;

import java.util.ArrayList;

import java.util.List;


public class Activity {

    private String id;
    private String description;
    private HalfHours budgetTime;
    private Date startDate;
    private Date endDate;
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
        this.startDate = new Date(startWeek, startYear);
        this.endDate = new Date(endWeek, endYear);
        calculateBudgetWeek();
    }

    public void calculateBudgetWeek() {
        //Jonas
        this.budgetWeeks = startDate.calculateWeeksToStartWeek(endDate);
    }

    public int getBudgetWeeks() {
        // Jonas
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
        int weeks = startDate.calculateWeeksToStartWeek(new Date(week, year));
        if (weeks >= 0 && weeks <= budgetWeeks){
            return true;
        }
        return false;
    }

    public String getStartDate() {
        // Jonas
        return startDate.stringOfDate();
    }

    public String getEndDate() {
        // Jonas
        return endDate.stringOfDate();
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
