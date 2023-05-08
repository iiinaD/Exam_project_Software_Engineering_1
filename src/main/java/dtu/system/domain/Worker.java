package dtu.system.domain;

import java.util.List;
import java.util.ArrayList;

public class Worker {
    private String initials;
    private List<WorkerActivity> workerActivityList = new ArrayList<>();

    public Worker(String initials) {
        // Jonas
        this.initials = initials;
    }

    public WorkerActivity addWorkerActivity(Activity activity) {
        // Danny
        if(hasWorkerActivity(activity)) {
            return null;
        }

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
        for (WorkerActivity workerActivity : workerActivityList){
            if (workerActivity.getActivity().getActivityId().equals(activity)){
                return workerActivity;
            }
        }
        return null;
    }

    public boolean incrementWorkTime(Activity activity, int hours, int minutes) {
        // Jonas
        if (!hasWorkerActivity(activity)){
            addWorkerActivity(activity);
        }
        WorkerActivity workerActivity = getWorkerActivity(activity.getActivityId());
        workerActivity.incrementWorkTime(hours, minutes);
        return true;
    }

    public boolean hasWorkerActivity(Activity activity) {
        // Jonas
        return getWorkerActivity(activity.getActivityId()) != null ? true : false;
    }
}