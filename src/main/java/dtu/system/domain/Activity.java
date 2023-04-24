package dtu.system.domain;

import java.util.ArrayList;

import java.util.List;

public class Activity {

    private String id;
    private String name;
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

    public Activity(String id, String name, String description, Project parentProject) {
        // Danny
        this.id = id;
        this.name = name;
        this.description = description;
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

    public void addWorker(Worker worker){
        // Daniel
        workerList.add(worker);
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
    
    public String getActivityName()
    {
        // Danny
        return name;
    }

    public String getDescription() {
        // Jonas
        return description;
    }

    public String overview(int numberOfTaps, Boolean includeWorkerList, Boolean includeBudgetTime, Boolean includeName){
        // Jonas
        String taps = "";
        for (int i = 0; i < numberOfTaps; i++){
            taps += "\t";
        }

        String print = "";
        print += taps + " Activity: " + id + "\n";
        if (includeName && name != null){
            print += taps + "\t Activity name: \n";
            print += taps + "\t\t " + name + "\n";
        }
        if (startDate != null && endDate != null) {
            print += taps + "\t Scheduled: \n";
            print += taps + "\t\t Start date: " + getStartDate() + "\n";
            print += taps + "\t\t End date  : " + getEndDate() + "\n";
            print += taps + "\t\t Total number of weeks: " + budgetWeeks + "\n";
        }
        if (includeBudgetTime && budgetTime != null){
            print += taps + "\t Budget Time: \n";
            print += taps + "\t\t " + budgetTime.getTime() + " Hours \n";
        }
        if (includeWorkerList){
            print += taps + "\t Worker assigned to activity: \n";
            for (Worker worker : workerList){
                print += taps + "\t\t " + worker.getInitials() + "\n";
            }
            if (workerList.isEmpty()){
                print += taps + "\t\t <empty> \n";
            }
        }

        return print;
    }
}
