package dtu.system.domain;

public class Worker {
    private String initials;
    public Worker(String initials) {
        // husk test længde
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }
}
