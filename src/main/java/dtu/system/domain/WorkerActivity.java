package dtu.system.domain;

public class WorkerActivity
{
     private Activity activity;
     private Worker worker;
     private double workTime = 0.0;

     public WorkerActivity(Worker worker, Activity activity) {
          // Danny
          this.activity = activity;
          this.worker = worker;
     }

     public Activity getActivity() {
          // Danny
          return activity;
     }
     public Worker getWorker(){
          return worker;
     }

     public double getWorkTime() {
          // Danny
          return workTime;
     }

     public void incrementWorkTime(HalfHours workTime) {
          // Danny
          this.workTime += workTime.getTime();
     }
}
