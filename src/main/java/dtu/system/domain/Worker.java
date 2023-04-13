package dtu.system.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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

    private String fetchWorkData(WorkerActivity workerActivity){
        Activity theAct = workerActivity.getActivity(); //get Activity
        String actName = theAct.getActivityId(); //get Activity ID
        String projName = theAct.getParentProject().getProjectName();//get Project Name
        double time = workerActivity.getWorkTime().getTime();
        int timeInt = (int)time; //get time spent as integer
        return String.format("%s\t%s\t%d Hrs",actName, projName, timeInt);
    }

    public String accessHoursOverview(Activity activity){
        for (WorkerActivity workerActivity: workerActivityList) { //look through the worker's list of activity
            if (activity.equals(workerActivity.getActivity())){
                return fetchWorkData(workerActivity);
            }
        }
        return "activity not found";
    }

    public String accessHoursOverview(String activity){
        if (workerActivityList.isEmpty()) return "no activity for this worker";

        for (WorkerActivity workerActivity: workerActivityList) {
            if (workerActivity.getActivity() != null && Objects.equals(workerActivity.getActivity().getActivityId(), activity)){
                return fetchWorkData(workerActivity);
            }
        }

        return "activity not found from string";
    }



}