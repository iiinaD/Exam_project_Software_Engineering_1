package dtu.system.domain;

public class WorkerActivity {
     // Should not have a worker
     private Worker worker;
     private Activity activity;
     private HalfHours halfHours = new HalfHours();

     public WorkerActivity(Worker worker, Activity activity) {
          // Danny
          this.activity = activity;
          this.worker = worker;
          //activity.addWorkerActivity(this); //link WorkerActivity to that Activity
     }

     public void incrementWorkTime(int hours, int min) {
          // Jonas
          halfHours.increment(hours, min);
     }

     public Activity getActivity() {
          // Danny
          return activity;
     }

     public Worker getWorker(){
          // Gee
          return worker;
     }

     public HalfHours getWorkTime() {
          // Jonas
          return halfHours;
     }

     public String prettyPrintData(){
          // Gee
          Activity theAct = getActivity(); //get Activity
          String actName = theAct.getActivityId(); //get Activity ID
          String projName = theAct.getParentProject().getProjectName();//get Project Name
          double time = getWorkTime().getTime();
          return String.format("%s\t%s\t%.1f Hrs",actName, projName, time);
     }
}
