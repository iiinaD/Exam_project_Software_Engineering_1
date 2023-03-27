package dtu.system.domain;

public class Project {

    private String projectName;
    private Worker projectLeader;
    private int projectNumber;

    public Project(String projectName, Worker projectLeader, int projectNumber) {
        this.projectName = projectName;
        this.projectNumber = projectNumber;
        this.projectLeader = projectLeader;
    }

    public Project(String projectName,int projectNumber) {
        this.projectName = projectName;
        this.projectNumber = projectNumber;
    }

    public void setProjectLeader(Worker newProjectLeader) {
        projectLeader = newProjectLeader;
    }

    public int getProjectNumber() {
        return projectNumber;
    }
}
