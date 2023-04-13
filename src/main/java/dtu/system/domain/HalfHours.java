package dtu.system.domain;

import static java.lang.Math.*;

public class HalfHours {
    private double halfHours;

    public HalfHours(){
        //Jonas
        this.halfHours = 0;
    }
    public HalfHours(int hour, int min) {
        //Jonas
        this.halfHours = 0;
        increment(hour, min);
    }

    public void increment(int hour, int min){
        //Jonas
        double mini = min(abs(min - 60), min(min, abs(min - 30)));
        double res;
        if (mini == min){
            res = 0;
        } else if (mini == abs(min-30)){
            res = 0.5;
        } else {
            res = 1;
        }
        this.halfHours += hour+res;
    }

    public double getTime() {
        //Jonas
        return halfHours;
    }
}
