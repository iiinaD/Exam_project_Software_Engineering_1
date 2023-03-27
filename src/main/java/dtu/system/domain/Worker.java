package dtu.system.domain;

public class Worker
{
    private String initials;

    public Worker(String initials)
    {
        if(initials.length() > 4)
        {
             throw new IllegalArgumentException("Worker initials can't contain more than 4 characters.");
        }

        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }
}