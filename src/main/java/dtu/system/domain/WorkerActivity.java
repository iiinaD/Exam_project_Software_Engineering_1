package dtu.system.domain;

public class WorkerActivity
{
     private Activity activity;
     private double workTime = 0.0;

     public WorkerActivity(Activity activity) {
          // Danny
          this.activity = activity;
     }

     public Activity getWorkerActivity() {
          // Danny
          return activity;
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
