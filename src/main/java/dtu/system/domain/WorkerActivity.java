package dtu.system.domain;

public class WorkerActivity {
     private Worker worker;
     private Activity activity;
     private HalfHours halfHours = new HalfHours();

     public WorkerActivity(Worker worker, Activity activity) {
          // Danny
          this.activity = activity;
          this.worker = worker;
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
}
