package dtu.system.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Worker {
    private String initials;
    private List<WorkerActivity> workerActivityList = new ArrayList<>();

    public Worker(String initials) { // the error handling should be handled by the app
        // Danny
        if (initials.length() < 2 || initials.length() > 4) {
             throw new IllegalArgumentException("Worker initials can't contain less than 2 or more than 4 characters."); // missing test
        }
        else if(!initials.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Worker initials can't contain numbers or special characters."); // missing test
        }

        this.initials = initials;
    }

    public WorkerActivity addWorkerActivity(Activity activity) {
        // Danny
        WorkerActivity workerActivity = new WorkerActivity(this, activity);

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

    public WorkerActivity getWorkerActivity(String activity) {
        //Jonas
        for (WorkerActivity wa : workerActivityList){
            if (wa.getActivity().getActivityId().equals(activity)){
                return wa;
            }
        }
        return null;
    }
}