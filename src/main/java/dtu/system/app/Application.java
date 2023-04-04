package dtu.system.app;

import dtu.system.domain.Activity;
import dtu.system.domain.Project;
import dtu.system.domain.Worker;

import java.util.ArrayList;
import java.util.List;

public class Application {

    ArrayList<Worker> workerList = new ArrayList<>();
    private Worker loggedInWorker;
    private Boolean loggedIn = false;
    private List<Project> projectList = new ArrayList<>();

    public void addNewWorker(Worker worker) throws OperationNotAllowedException {
        //Jonas
        if(!isWorkerInWorkerList(worker.getInitials())) {
            workerList.add(worker);
        } else {
            throw new OperationNotAllowedException("A worker with these initials already exists.");
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
        this.loggedIn = false;
        this.loggedInWorker = null;
    }

    public boolean getLoggedInStatus() {
        // Jonas
        return loggedIn;
    }

    public Worker getLoggedInWorker() throws OperationNotAllowedException {
        if (loggedInWorker == null){
            throw new OperationNotAllowedException("no worker is logged in");
        }
        return loggedInWorker;
    }

    public Project createProject(String projectName, Worker projectLeader){
        // mangler error handling

        int projectNumber = getNextProjectNumber();
        Project project = new Project(projectName,projectLeader,projectNumber);
        projectList.add(project);
        return project;

    }

    public Project createProject(String projectName) throws OperationNotAllowedException{

        if (loggedIn){
            int projectNumber = getNextProjectNumber();
            Project project = new Project(projectName,projectNumber);
            projectList.add(project);
            return project;
        } else {
            throw new OperationNotAllowedException("no worker is logged in");
        }
    }

    public int getNextProjectNumber() {
        return 23000 + projectList.size() + 1;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public Project getProjectWithNumber(int projectNumber) throws OperationNotAllowedException {
        //Jonas
        for (Project p : projectList){
            if (p.getProjectNumber() == projectNumber) {
                return p;
            }
        }

        throw new OperationNotAllowedException("project " + projectNumber + " dont exist");

    }

    public Activity addActivityToProject(int projectNumber) throws OperationNotAllowedException {
        //Jonas
        if (loggedIn){
            return getProjectWithNumber(projectNumber).addActivity();
        } else {
            throw new OperationNotAllowedException("Need to login a worker before adding an activity to the project");
        }

    }
}