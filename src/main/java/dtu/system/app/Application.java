package dtu.system.app;

import dtu.system.domain.Project;
import dtu.system.domain.Worker;

import java.util.ArrayList;
import java.util.List;

public class Application {

    ArrayList<Worker> workerList = new ArrayList<>();
    private Worker loggedInWorker;
    private Boolean loggedIn = false;
    private List<Project> projectList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public void addNewWorker(Worker worker) throws OperationNotAllowedException {
        //Jonas
        if(!isWorkerInWorkerList(worker)) {
            workerList.add(worker);
        } else {
             throw new OperationNotAllowedException("A worker with this name already exists.");
        }
    }

    public boolean isWorkerInWorkerList(Worker worker) {
        //Jonas
        for (Worker i : workerList){
            if (i.getInitials().equals(worker.getInitials())){
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

    public boolean getLoggedInStatus() {
        // Jonas
        return loggedIn;
    }

    public Worker getLoggedInWorker() {
        // Jonas
        return loggedInWorker;
    }

    public void createProject(String projectName, Worker projectLeader) {
        int projectNumber = getNextProjectNumber();
        Project project = new Project(projectName,projectLeader,projectNumber);
        projectList.add(project);
    }

    public void createProject(String projectName) {
        int projectNumber = getNextProjectNumber();
        Project project = new Project(projectName,projectNumber);
        projectList.add(project);
    }

    public int getNextProjectNumber() {
        return 23000 + projectList.size() + 1;
    }

    public List<Project> getProjectList() {
        return projectList;
    }
}
