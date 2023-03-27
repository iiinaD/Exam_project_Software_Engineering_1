package dtu.system.domain;

public class Project {

    private String projectName;
    private Worker projectLeader;
    private int projectNumber;

    public Project(String projectName, Worker projectLeader, int projectNumber) {
        // Daniel
        this.projectName = projectName;
        this.projectNumber = projectNumber;
        this.projectLeader = projectLeader;
    }

    public Project(String projectName,int projectNumber) {
        // Daniel
        this.projectName = projectName;
        this.projectNumber = projectNumber;
    }

    public void setProjectLeader(Worker newProjectLeader) {
        // Daniel
        projectLeader = newProjectLeader;
    }

    public int getProjectNumber() {
        // Daniel
        return projectNumber;
    }
}
