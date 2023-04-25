package dtu.system.domain;

import dtu.system.app.OperationNotAllowedException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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

    public boolean incrementWorkTime(Activity activity, int hours, int minutes) {
        if (workerActivityList.isEmpty()){
            return false;
        }

        for(WorkerActivity workerActivity :workerActivityList){
            if(Objects.equals(workerActivity.getActivity(), activity)){
                workerActivity.incrementWorkTime(hours, minutes);
                return true;
            }
        }
        return false;
    }
}