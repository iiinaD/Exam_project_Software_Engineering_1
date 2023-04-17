package dtu.system.domain;

import java.util.ArrayList;

public class Project {

    private String projectName;
    private Worker projectLeader;
    private int projectNumber;
    private boolean isFinished = false;
    private int activityCounter = 1;
    private ArrayList<Activity> activities = new ArrayList<Activity>(); // an arraylist containing all the activities in this project


    public Project(String projectName, Worker projectLeader, int projectNumber) {
        // Daniel
        this.projectName = projectName;
        this.projectNumber = projectNumber;
        this.projectLeader = projectLeader;
    }

    public Project(String projectName,int projectNumber) {
        // Daniel
        this.projectName = projectName;
        this.projectNumber = projectNumber;
    }

    public void setProjectLeader(Worker newProjectLeader) {
        // Daniel
        projectLeader = newProjectLeader;
    }

    public int getProjectNumber() {
        // Daniel
        return projectNumber;
    }

    public String getProjectName() {
        // Gee
        return projectName;
    }

    public Activity addActivity(){
        // Gee
        Activity act = new Activity(projectNumber + "-" + String.valueOf(1000 + activityCounter).substring(1), this);
        activities.add(act); //add to activities List
        activityCounter += 1; //increment id counter by 1
        return act;
    }

    public ArrayList<Activity> getActivityList(){
        // Gee
        return activities;
    }

    public void setProjectName(String newProjectName) {
        // Daniel
        projectName = newProjectName;
    }

    public String getName() {
        // Daniel
        return projectName;
    }

    public Worker getProjectLeader() {
        // Daniel
        return projectLeader;
    }

    public void finishProject() {
        // Daniel
        isFinished = true;
    }

    public boolean getIsFinished() {
        // Daniel
        return isFinished;
    }

    public Activity getActivity(String activityId) {
        //Jonas
        for (Activity i : activities){
            if (i.id.equals(activityId)){
                return i;
            }
        }
        return null; //mangler test
    }

    public boolean hasProjectLeader() {
        // Daniel
        return projectLeader != null;
    }
}


