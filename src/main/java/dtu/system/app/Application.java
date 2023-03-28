package dtu.system.app;

import dtu.system.domain.Project;
import dtu.system.domain.Worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Application {

    private ArrayList<Worker> workerList = new ArrayList<>();
    private Worker loggedInWorker;
    private Boolean loggedIn = false;
    private List<Project> projectList = new ArrayList<>();
    private DateServer dateServer;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public void addNewWorker(Worker worker) throws OperationNotAllowedException {
        //Jonas
        if(!isWorkerInWorkerList(worker.getInitials())) {
            workerList.add(worker);
        } else {
             throw new OperationNotAllowedException("A worker with this name already exists.");
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

    public boolean getLoggedInStatus() {
        // Jonas
        return loggedIn;
    }

    public Worker getLoggedInWorker() {
        // Jonas
        return loggedInWorker;
    }

    public void createProject(String projectName, Worker projectLeader) throws OperationNotAllowedException {
        // Daniel
        if (loggedIn) {
            int projectNumber = getNextProjectNumber();
            Project project = new Project(projectName, projectLeader, projectNumber);
            projectList.add(project);
        } else {
            throw new OperationNotAllowedException("Login is required to create project");
        }

    }

    public void createProject(String projectName) throws OperationNotAllowedException {
        // Daniel
        if (loggedIn) {
            int projectNumber = getNextProjectNumber();
            Project project = new Project(projectName, projectNumber);
            projectList.add(project);
        } else {
            throw new OperationNotAllowedException("Login is required to create project");
        }
    }

    public int getNextProjectNumber() {
        // Daniel
        int year = dateServer.getDate().get(Calendar.YEAR) - 2000;
        return year + projectList.size() + 1;
    }

    public List<Project> getProjectList() {
        // Daniel
        return projectList;
    }
}
