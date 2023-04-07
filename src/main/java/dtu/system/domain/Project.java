package dtu.system.domain;

import java.util.ArrayList;

public class Project {

    private String projectName;
    private Worker projectLeader;
    private int projectNumber;
    private int activityCounter = 0;
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

    public Activity addActivity(){
        //gee
        Activity act = new Activity(projectNumber+"-"+activityCounter);
        activities.add(act); //add to activities List
        activityCounter += 1; //increment id counter by 1
        return act;
    }

    public ArrayList<Activity> getActivityList(){
        return activities;
    }

    public void setProjectName(String newProjectName) {
        projectName = newProjectName;
    }

    public String getName() {
        return projectName;
    }
}


