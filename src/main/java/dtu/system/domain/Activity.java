package dtu.system.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Activity {

    public String id;
    public String description;
    public HalfHours budgetTime;
    public int startWeek;
    public int endWeek;
    public int sartyear;
    public int endYear;
    public Project parentProject;
    // This is worng need to be a worker List, its the worker class that should have this list.
    //public ArrayList<WorkerActivity> workerActivityList = new ArrayList<WorkerActivity>(); //Stores WorkerActivity Objects
    private ArrayList<Worker> WorkerList = new ArrayList<>();

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

    public void setStartEndWeekAndYears(int startWeek, int endWeek, int sartyear, int endYear){
        // Todo
    }

    public String getActivityId() {
        // Danny
        return id;
    }

    public Project getParentProject(){
        return parentProject;
    }

    public void addWorker(Worker worker) {
        // Daniel
        WorkerList.add(worker);
    }

    public List<Worker> getWorkerList() {
        // Daniel
        return WorkerList;
    }

//    public void addWorkerActivity(WorkerActivity workerActivity){
//        workerActivityList.add(workerActivity);
//    }

}
