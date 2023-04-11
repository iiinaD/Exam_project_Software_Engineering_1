package dtu.system.domain;

public class WorkerActivity
{
     private Activity activity;
     private HalfHours workTime;

     public WorkerActivity(Activity activity) {
          // Danny
          this.activity = activity;
     }

     public void setWorkTime(HalfHours workTime) {
          // Danny
          this.workTime = workTime;
     }

     public Activity getWorkerActivity() {
          // Danny
          return activity;
     }

     public HalfHours getWorkTime() {
          // Danny
          return workTime;
     }
}
