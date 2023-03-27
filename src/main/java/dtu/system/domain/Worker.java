package dtu.system.domain;

public class Worker
{
    private String initials;

    public Worker(String initials)
    {
        if (initials.length() > 4) {
             throw new IllegalArgumentException("Worker initials can't contain more than 4 characters.");
        } else if (initials.length() < 2) {
            throw new IllegalArgumentException("Worker intitials has to contain at least 2 characters.");
        }

        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }
}