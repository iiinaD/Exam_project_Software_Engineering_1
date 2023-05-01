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
        // Pre
        assert abs(min) >= 0 && abs(min) <= 60;
        double res;
        double mini = min(abs(abs(min) - 60), min(abs(min), abs(abs(min) - 30)));
        if (mini == abs(min)){ // 1
            res = 0;
        } else if (mini == abs(abs(min)-30)){ // 2
            res = 0.5;
        } else { // 3
            res = 1;
        }
        this.halfHours += hour+res*signum(min);
        if (halfHours < 0){ // 4
            this.halfHours = 0;
        }
        // post
        assert halfHours % 0.5 == 0;
    }
    public double getTime() {
        //Jonas
        return halfHours;
    }
}
