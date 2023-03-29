package dtu.system.domain;

import static java.lang.Math.*;

public class HalfHours {
    private double halfHours;


    public HalfHours(int hour, int min) {
        double mini = min(abs(min - 60), min(min, abs(min - 30)));
        double res;

        if (mini == min){
            res = 0;
        } else if (mini == abs(min-30)){
            res = 0.5;
        } else {
            res = 1;
        }

        this.halfHours = hour+res;
    }

    public double getTime() {
        return halfHours;
    }
}
