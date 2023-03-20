package dtu.system.app;

import dtu.system.domain.Worker;

import java.util.ArrayList;

public class Application {

    ArrayList<Worker> workerList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public void addNewWorker(Worker worker) {
        workerList.add(worker);
    }

    public boolean isWorkerInWorkerList(Worker worker) {

        for (Worker i : workerList){
            if (i.getInitials().equals(worker.getInitials())){
                return true;
            }
        }
        return false;
    }
}
