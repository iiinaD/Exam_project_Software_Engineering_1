package dtu.system.domain;

public class WorkerActivity
{
     private Activity activity;
     private Worker worker;
     private double workTime = 0.0;
     private HalfHours halfHours = new HalfHours();

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

     public HalfHours getWorkTime() {
          return halfHours;
     }

     public void incrementWorkTime(int hours, int min) {
          halfHours.increment(hours, min);
     }
}
