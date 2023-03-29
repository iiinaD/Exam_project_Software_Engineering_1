package dtu.system.domain;

public class Worker
{
    private String initials;

    public Worker(String initials) {
        // Danny
        if (initials.length() < 2 || initials.length() > 4) {
             throw new IllegalArgumentException("Worker initials can't contain less than 2 or more than 4 characters.");
        }
        else if(!initials.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Worker initials can't contain numbers or special characters.");
        }

        this.initials = initials;
    }

    public String getInitials() {
        // Jonas
        return initials;
    }
}