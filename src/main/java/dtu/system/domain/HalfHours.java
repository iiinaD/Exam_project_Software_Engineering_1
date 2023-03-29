package dtu.system.domain;

import static java.lang.Math.*;

public class HalfHours {
    private double halfHours;

    public HalfHours(double time){
        double ceil = ceil(time);
        double floor = floor(time);
        double mid = (ceil - floor)/2;

        double distCeil = ceil - time;
        double distFloor = time - floor;
        double distMid = abs(mid - time);

        double min = min(distCeil, min(distFloor, distMid));

        if (min == ceil){
            this.halfHours = ceil;
        } else if (min == floor){
            this.halfHours = floor;
        } else {
            this.halfHours = mid;
        }
    }

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
