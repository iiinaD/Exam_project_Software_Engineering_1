package dtu.system.domain;

import java.util.Locale;

public class WorkerActivity {
          private Activity activity;
     private HalfHours halfHours = new HalfHours();

     public WorkerActivity(Activity activity) {
          // Danny
          this.activity = activity;
     }

     public void incrementWorkTime(int hours, int min) {
          // Jonas
          halfHours.increment(hours, min);
     }

     public Activity getActivity() {
          // Danny
          return activity;
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
          return String.format(Locale.CANADA_FRENCH, "%s\t%s\t%.1f Hrs",actName, projName, time);
     }
}
