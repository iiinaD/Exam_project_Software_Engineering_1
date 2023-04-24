package dtu.system.domain;

import java.util.ArrayList;

public class Project {

    private String projectName;
    private Worker projectLeader;
    private int projectNumber;
    private boolean isFinished = false;
    private int activityCounter = 1; // should it not start at 0?
    private ArrayList<Activity> activityList = new ArrayList<Activity>();


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

    public boolean isProjectLeader(Worker worker) {
        if (projectLeader == null) {
            return false;
        }
        return projectLeader.getInitials().equals(worker.getInitials());
    }

    public String getProjectName() {
        // Gee
        return projectName;
    }

    public Activity addActivity(){
        // Gee
        Activity act = new Activity(projectNumber + "-" + String.valueOf(1000 + activityCounter).substring(1), this);
        activityList.add(act); //add to activities List
        activityCounter += 1; //increment id counter by 1
        return act;
    }

    public Activity addActivityWithNameAndDescription(String activityName, String activityDescription){
        // Danny
        Activity activity = new Activity(projectNumber + "-" + String.valueOf(1000 + activityCounter).substring(1), activityName, activityDescription, this);

        activityList.add(activity);
        activityCounter++;

        return activity;
    }

    public ArrayList<Activity> getActivityList(){
        // Gee
        return activityList;
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
        for (Activity activity : activityList){
            if (activity.getActivityId().equals(activityId)){
                return activity;
            }
        }
        return null;
    }

    public boolean hasProjectLeader() {
        // Daniel
        return projectLeader != null;
    }

    public String overview(int numberOfTabs){
        // Jonas
        String tabs = "";
        for (int i = 0; i < numberOfTabs; i++){
            tabs += "\t";
        }
        String print = "";

        print += tabs + "----- Project Overview: "+ infoString(0) +" -----\n";

        if (hasProjectLeader()){
            print += tabs + "\t Project Leader: \n";
            print += tabs + "\t\t " + projectLeader.getInitials() + "\n";
        }

        print += tabs + "\t Project status:\n";
        if (isFinished){
            print += tabs + "\t\t  completed \n";
        } else {
            print += tabs + "\t\t  incomplete \n";
        }


        print += tabs + "\t Activity List: \n";
        for (Activity activity : activityList){
            print += tabs + activity.overview(3, true, true, true);
        }
        if (activityList.isEmpty()){
            print += tabs + "\t\t <empty> \n";
        }
        print += "\n";

        return print;
    }

    public String infoString(int numberOfTaps){
        // Jonas
        String taps = "";
        for (int i = 0; i < numberOfTaps; i++){
            taps += "\t";
        }
        return taps + projectName + " (" + projectNumber + ")";
    }
}


