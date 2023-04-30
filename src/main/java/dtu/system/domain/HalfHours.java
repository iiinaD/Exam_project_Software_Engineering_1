package dtu.system.domain;

import static java.lang.Math.*;

public class HalfHours {
    private double halfHours = 0;

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

        double mini = min(abs(abs(min) - 60), min(abs(min), abs(abs(min) - 30)));
        double res;
        if (mini == abs(min)){
            res = 0;
        } else if (mini == abs(abs(min)-30)){
            res = 0.5;
        } else {
            res = 1;
        }
        this.halfHours += hour+res*signum(min);
        if (halfHours < 0){
            this.halfHours = 0;
        }
    }

    public double getTime() {
        //Jonas
        return halfHours;
    }
}
