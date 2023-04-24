package dtu.system.domain;

import java.util.List;
import java.util.ArrayList;

public class Worker {
    private String initials;
    private List<WorkerActivity> workerActivityList = new ArrayList<>();

    public Worker(String initials) {
        // Danny
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