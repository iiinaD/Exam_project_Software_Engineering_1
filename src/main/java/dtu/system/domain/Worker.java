package dtu.system.domain;

import dtu.system.app.OperationNotAllowedException;

import java.util.List;
import java.util.ArrayList;

public class Worker
{
    private String initials;
    private List<WorkerActivity> workerActivityList = new ArrayList<>();

    public Worker(String initials) { // the error handling should be handled by the app
        // Danny
        if (initials.length() < 2 || initials.length() > 4) {
             throw new IllegalArgumentException("Worker initials can't contain less than 2 or more than 4 characters.");
        }
        else if(!initials.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Worker initials can't contain numbers or special characters.");
        }

        this.initials = initials;
    }

    public WorkerActivity addWorkerActivity(Activity activity) {
        // Danny
        WorkerActivity workerActivity = new WorkerActivity(activity);

        workerActivityList.add(workerActivity);

        return workerActivity;
    }

    public String getInitials() {
        // Jonas
        return initials;
    }

    public List<WorkerActivity> getWorkerActivityList() {
        // Danny
        return workerActivityList;
    }
}