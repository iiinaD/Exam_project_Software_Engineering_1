package dtu.system.domain;

import dtu.system.app.OperationNotAllowedException;

import java.util.List;
import java.util.ArrayList;

public class Worker
{
    private String initials;
    private List<WorkerActivity> workerActivityList = new ArrayList<>();

    public Worker(String initials) {
        // Danny
        if (initials.length() < 2 || initials.length() > 4) {
             throw new IllegalArgumentException("Worker initials can't contain less than 2 or more than 4 characters.");
        }
        else if(!initials.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Worker initials can't contain numbers or special characters.");
        }

        this.initials = initials;
    }

    public void addWorkerActivity(Activity activity, HalfHours workTime) {
        // Danny
        WorkerActivity workerActivity = new WorkerActivity(activity, workTime);

        workerActivityList.add(workerActivity);
    }

    public String getInitials() {
        // Jonas
        return initials;
    }

    public WorkerActivity getWorkerActivity(Activity activity) throws OperationNotAllowedException {
        // Danny
        if(workerActivityList.isEmpty()) {
            throw new OperationNotAllowedException("This worker doesn’t have any activities yet.");
        }

        for(WorkerActivity workerActivity : workerActivityList) {
            if(workerActivity.getWorkerActivity() == activity) {
                return workerActivity;
            }
        }

        return null;
    }


    public List<WorkerActivity> getWorkerActivityList() {
        // Danny
        return workerActivityList;
    }
}