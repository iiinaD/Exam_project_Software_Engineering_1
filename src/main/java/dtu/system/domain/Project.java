package dtu.system.domain;

import dtu.system.app.OperationNotAllowedException;

import java.util.ArrayList;

public class Project {

    private String projectName;
    private Worker projectLeader;
    private int projectNumber;
    private boolean isFinished = false;
    private int activityCounter = 0;
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
        // Daniel
        if (projectLeader == null) {
            return false;
        }
        return projectLeader.getInitials().equals(worker.getInitials());
    }

    public String getProjectName() {
        //Gee
        return projectName;
    }

    public Activity addActivity() throws OperationNotAllowedException {
        //Gee
        activityCounter += 1; //increment id counter by 1
        Activity act = new Activity(projectNumber + "-" + String.valueOf(1000 + activityCounter).substring(1), this);
        activityList.add(act); //add to activities List
        return act;
    }

    public Activity addActivity(String activityName, String activityDescription) throws OperationNotAllowedException {
        // Danny
        activityCounter++;

        Activity activity = new Activity(projectNumber + "-" + String.valueOf(1000 + activityCounter).substring(1), activityName, activityDescription, this);

        activityList.add(activity);

        return activity;
    }

    public ArrayList<Activity> getActivityList(){
        //Gee
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
        for(Activity activity: activityList){
            activity.lock();
        }
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
        print += tabs + "----- Project Overview: "+ info(0) +" -----\n";
        print += tabs + "\t Project Leader: \n";
        if (hasProjectLeader()){
            print += tabs + "\t\t " + projectLeader.getInitials() + "\n";
        } else {
            print += tabs + "\t\t <empty>\n";
        }
        print += tabs + "\t Project status:\n";
        if (isFinished){
            print += tabs + "\t\t completed \n";
        } else {
            print += tabs + "\t\t incomplete \n";
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

    public String info(int numberOfTaps){
        // Jonas
        String taps = "";
        for (int i = 0; i < numberOfTaps; i++){
            taps += "\t";
        }
        return taps + projectName + " (" + projectNumber + ")";
    }
}


