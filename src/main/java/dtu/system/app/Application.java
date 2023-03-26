package dtu.system.app;

import dtu.system.domain.Worker;

import java.util.ArrayList;

public class Application {

    ArrayList<Worker> workerList = new ArrayList<>();
    private Worker loggedInWorker;
    private Boolean loggedIn = false;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public void addNewWorker(Worker worker) throws WorkerAlreadyExistsException
    {
        //Jonas
        if(isWorkerInWorkerList(worker) == false)
        {
            workerList.add(worker);
        }
        else
        {
             throw new WorkerAlreadyExistsException("A worker with this name already exists.");
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
}
