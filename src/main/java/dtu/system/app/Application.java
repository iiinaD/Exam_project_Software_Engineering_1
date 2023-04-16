package dtu.system.app;

import dtu.system.domain.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Application {

    private ArrayList<Worker> workerList = new ArrayList<>();
    private Worker loggedInWorker;
    private Boolean loggedIn = false;
    private List<Project> projectList = new ArrayList<>();
    private DateServer dateServer = new DateServer();


    public void addNewWorker(Worker worker) throws OperationNotAllowedException {
        //Jonas
        if(!isWorkerInWorkerList(worker.getInitials())) {
            workerList.add(worker);
        } else {
            throw new OperationNotAllowedException("A worker with these initials already exists in the system.");
        }
    }

    public boolean isWorkerInWorkerList(String initials) {
        //Jonas
        for (Worker i : workerList){
            if (i.getInitials().equals(initials)){
                return true;
            }
        }
        return false;
    }

    public void logIn(String initials) {
        //Jonas
        for (Worker i : workerList){
            if (i.getInitials().equals(initials)){
                this.loggedInWorker = i;
                this.loggedIn = true;
            }
        }
        // else cast error (user not found)
    }
    public void logOut() {
        //Jonas
        this.loggedIn = false;
        this.loggedInWorker = null;
    }

    public boolean getLoggedInStatus() {
        // Jonas
        return loggedIn;
    }

    public Worker getLoggedInWorker(){
        //Jonas
        return loggedInWorker;
    }

    public Project createProject(String projectName, Worker projectLeader) throws OperationNotAllowedException {
        //Daniel
        if (loggedIn){
            int projectNumber = getNextProjectNumber();
            Project project = new Project(projectName,projectLeader,projectNumber);
            projectList.add(project);
            return project;
        } else {
            throw new OperationNotAllowedException("no worker is logged in");
        }
    }

    public Project createProject(String projectName) throws OperationNotAllowedException{
        //Daniel
        if (loggedIn){ //Disable the login check
            int projectNumber = getNextProjectNumber();
            Project project = new Project(projectName,projectNumber);
            projectList.add(project);
            return project;
        } else {
            throw new OperationNotAllowedException("no worker is logged in");
        }
    }

    public int getNextProjectNumber() {
        //Daniel
        String year = String.valueOf(dateServer.getDate().get(Calendar.YEAR));
        int twoDigitYear = Integer.parseInt(year.substring(2));
        return twoDigitYear*1000 + projectList.size() + 1;
    }

    public List<Project> getProjectList() {
        //Daniel
        return projectList;
    }

    public Project getProjectWithNumber(int projectNumber) throws OperationNotAllowedException {
        //Jonas
        for (Project p : projectList){
            if (p.getProjectNumber() == projectNumber) {
                return p;
            }
        }

        throw new OperationNotAllowedException("project " + projectNumber + " doesn't exist");
    }

    public Activity addActivityToProject(Project project) throws OperationNotAllowedException {
        //Jonas
        if (loggedIn){ //Disable the login check
            return project.addActivity();
        } else {
            throw new OperationNotAllowedException("Need to login a worker before adding an activity to the project");
        }

    }
    public void incrementWorkTime(Worker worker, Activity activity, int hours, int minutes) throws OperationNotAllowedException {
        // Gee,
        //I don't do access control here for now, we might want to rethink the login check.
        // Response: Cant we just est if worker == loggedInWorker?
        if(!worker.getWorkerActivityList().isEmpty()) {
            List<WorkerActivity> workerActivityList = worker.getWorkerActivityList();

            for(WorkerActivity workerActivity :workerActivityList){
                if(Objects.equals(workerActivity.getActivity(), activity)){
                    workerActivity.incrementWorkTime(hours, minutes);
                }
            }
        }
        else {
            throw new OperationNotAllowedException("This worker doesn't have any activities yet.");
        }
    }

    public WorkerActivity addActivityToWorker(Worker worker, Activity activity){
        // Gee
        return worker.addWorkerActivity(activity);
    }

    public String hoursOverview(Worker worker, Activity activity){
        // Gee
        return worker.accessHoursOverview(activity);
    }
    public String hoursOverview(Worker worker, String activity){
        // Gee
        return worker.accessHoursOverview(activity);
    }

    public List<Worker> getWorkerList() {
        // Danny
        return workerList;
    }
    public void setActivityBudgetTime(Activity activity, HalfHours halfHours){
        activity.setBudgetTime(halfHours);
    }
    public HalfHours getActivityBudgetTime(Activity activity){
        return activity.budgetTime;
    }
    public void setActivityDescription(Activity activity, String description){
        activity.setDescription(description);
    }
    public String getActivityDescription(Activity activity) {
        return activity.description;
    }
    public boolean hasProjectWithNumber(int projectNumber) {
        // Daniel
        try {
            getProjectWithNumber(projectNumber);
        } catch (OperationNotAllowedException e) {
            return false;
        }
        return true;
    }

    public void changeProjectName(int projectNumber, String newProjectName) throws OperationNotAllowedException {
        // Daniel
        Project project = getProjectWithNumber(projectNumber);
        project.setProjectName(newProjectName);
    }

    public void setProjectLeader(int projectNumber, String workerInitials) throws OperationNotAllowedException {
        // Daniel
        Project project = getProjectWithNumber(projectNumber);
        Worker worker = getWorkerWithInitials(workerInitials);
        project.setProjectLeader(worker);
    }
    public Worker getWorkerWithInitials(String workerInitials) {
        // Daniel
        for (Worker worker : workerList) {
            if (workerInitials.equals(worker.getInitials())) {
                return worker;
            }
        }
        return null;
    }
}