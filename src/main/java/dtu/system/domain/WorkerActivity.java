package dtu.system.domain;

public class WorkerActivity
{
     private Activity activity;
     private double workTime = 0.0; // Worng
     private HalfHours halfHours = new HalfHours();

     public WorkerActivity(Activity activity) {
          // Danny
          this.activity = activity;
     }

     public Activity getWorkerActivity() {
          // Danny
          return activity;
     }

     public HalfHours getWorkTime() {
          return halfHours;
     }

     public void incrementWorkTime(int hours, int min) {
          halfHours.increment(hours, min);
     }
}
