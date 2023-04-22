package dtu.system.app;

import dtu.system.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Application {

    private ArrayList<Worker> workerList = new ArrayList<>();
    private Worker loggedInWorker;
    private Boolean loggedIn = false;
    private List<Project> projectList = new ArrayList<>();
    private DateServer dateServer = new DateServer();

    // Worker in system Related
    public void addNewWorker(Worker worker) throws OperationNotAllowedException {
        //Jonas
        if (isWorkerInWorkerList(worker.getInitials())){
            throw new OperationNotAllowedException("A worker with these initials already exists in the system.");
        }

        workerList.add(worker);
    }

    public boolean isWorkerInWorkerList(String initials) {
        //Jonas
        for (Worker i : workerList){
            if (i.getInitials().equals(initials)){
                return true;
            }
        }
        return false;
    }

    public List<Worker> getWorkerList() {
        // Danny
        return workerList;
    }

    public Worker getWorkerWithInitials(String workerInitials) throws OperationNotAllowedException {
        // Daniel
        for (Worker worker : workerList) {
            if (workerInitials.equals(worker.getInitials())) {
                return worker;
            }
        }
        throw new OperationNotAllowedException("No worker with the initials " + workerInitials + " exists in the system");
    }

    // Login logOut related
    public void logIn(String initials) throws OperationNotAllowedException {
        //Jonas
        if (!isWorkerInWorkerList(initials)){
            throw new OperationNotAllowedException("This worker doesn't exist in our system");
        }

        for (Worker i : workerList){
            if (i.getInitials().equals(initials)){
                this.loggedInWorker = i;
                this.loggedIn = true;
            }
        }
    }
    public void logOut() {
        //Jonas
        this.loggedIn = false;
        this.loggedInWorker = null;
    }

    public boolean getLoggedInStatus() {
        // Jonas
        return loggedIn;
    }

    public Worker getLoggedInWorker(){
        //Jonas
        return loggedInWorker;
    }

    public void loggedInTestError() throws OperationNotAllowedException{
        //Jonas
        if (!loggedIn){
            throw new OperationNotAllowedException("You must be logged in to access this feature. Please log in and try again.");
        }
    }

    // Project Related
    public Project createProject(String projectName, Worker projectLeader) throws OperationNotAllowedException {
        //Daniel
        loggedInTestError();

        int projectNumber = getNextProjectNumber();
        Project project = new Project(projectName,projectLeader,projectNumber);
        projectList.add(project);
        return project;
    }

    public Project createProject(String projectName) throws OperationNotAllowedException{
        //Daniel
        loggedInTestError();

        int projectNumber = getNextProjectNumber();
        Project project = new Project(projectName,projectNumber);
        projectList.add(project);
        return project;
    }

    public int getNextProjectNumber() {
        //Daniel
        //String year = String.valueOf(dateServer.getDate().get(Calendar.YEAR));
        String year = String.valueOf(dateServer.getCurrentYear());
        int twoDigitYear = Integer.parseInt(year.substring(2));
        return twoDigitYear*1000 + projectList.size() + 1;
    }

    public List<Project> getProjectList() {
        //Daniel
        return projectList;
    }

    public Project getProjectWithNumber(int projectNumber) throws OperationNotAllowedException {
        //Jonas
        for (Project p : projectList){
            if (p.getProjectNumber() == projectNumber) {
                return p;
            }
        }
        // mangler test
        throw new OperationNotAllowedException("No project with the id " + projectNumber + " exists in the system");
    }

    public boolean hasProjectWithNumber(int projectNumber) {
        // Daniel
        try {
            getProjectWithNumber(projectNumber);
        } catch (OperationNotAllowedException e) { //Mangler test
            return false;
        }
        return true;
    }

    public void changeProjectName(int projectNumber, String newProjectName) throws OperationNotAllowedException {
        // Daniel
        Project project = getProjectWithNumber(projectNumber);
        project.setProjectName(newProjectName);

    }

    public void setProjectLeader(int projectNumber, String workerInitials) throws OperationNotAllowedException {
        // Daniel
        Project project = getProjectWithNumber(projectNumber);
        Worker worker = getWorkerWithInitials(workerInitials);
        String loggedInWorkerName = getLoggedInWorker().getInitials();
        if (project.hasProjectLeader() && loggedInWorkerName.equals(project.getProjectLeader().getInitials())) {
            project.setProjectLeader(worker);
        } else if (project.hasProjectLeader()) {
            throw new OperationNotAllowedException("Only the current project leader can assign a new leader");
        } else {
            project.setProjectLeader(worker);
        }
    }

    // Activity Related
    public Activity addActivityToProject(Project project) throws OperationNotAllowedException {
        //Jonas
        loggedInTestError();
        return project.addActivity();
    }

    public Activity addActivityToProjectWithNameAndDescription(Project project, String activityName, String activityDescription) throws OperationNotAllowedException {
        // Danny
        loggedInTestError();

        return project.addActivityWithNameAndDescription(activityName, activityDescription);
    }

    public void setActivityBudgetTime(Activity activity, HalfHours halfHours){
        //Gee
        activity.setBudgetTime(halfHours);
    }
    public HalfHours getActivityBudgetTime(Activity activity){
        //Gee
        return activity.budgetTime;
    }
    public void setActivityDescription(Activity activity, String description){
        //Gee
        activity.setDescription(description);
    }
    public String getActivityDescription(Activity activity) {
        //Gee
        return activity.description;
    }

    public Activity getActivityFromProject(int projectNumber, String activityId) throws OperationNotAllowedException {
        // Jonas
        Activity activity = getProjectWithNumber(projectNumber).getActivity(activityId);
        if (activity == null){
            throw new OperationNotAllowedException(activityId + " dont exist");
        }
        return activity;
    }

    public void ActivityPlanStartAndEnd(int projectNumber, String activityId, int week0, int week1, int year0, int year1) throws OperationNotAllowedException {
        // Jonas
        Activity a = getActivityFromProject(projectNumber, activityId);
        if (a != null){
            a.setStartEndWeekAndYears(week0, week1, year0, year1);
        }
    }

    // Workers Activity's
    public void incrementWorkTime(Worker worker, Activity activity, int hours, int minutes) throws OperationNotAllowedException {
        // Gee,
        //I don't do access control here for now, we might want to rethink the login check.
        // Response: Cant we just est if worker == loggedInWorker?

        if (worker.getWorkerActivityList().isEmpty()){
            throw new OperationNotAllowedException("This worker doesn't have any activities yet.");
        }

        List<WorkerActivity> workerActivityList = worker.getWorkerActivityList();
        for(WorkerActivity workerActivity :workerActivityList){
            if(Objects.equals(workerActivity.getActivity(), activity)){
                workerActivity.incrementWorkTime(hours, minutes);
            }
        }
    }

    public WorkerActivity addActivityToWorker(Worker worker, Activity activity){
        // Gee
        return worker.addWorkerActivity(activity);
    }

    public  String hoursOverview(Worker worker){
        String output = "";
        List<WorkerActivity> workerActivityList = worker.getWorkerActivityList();
        if(workerList.isEmpty()) return "This worker has no activity";
        for(WorkerActivity workerActivity : workerActivityList){
            output += workerActivity.prettyPrintData()+"\n";
        }
        return output;
    }

    public  String hoursOverview(Activity activity){
        String output = "";
        List<Worker> workerList = activity.getWorkerList();
        if(workerList.isEmpty()) return "No workers in this activity";
        output += String.format("%s\t%s\n", activity.getActivityId(), activity.getParentProject().getProjectName());
        for(Worker worker : workerList){
            for(WorkerActivity workerActivity: worker.getWorkerActivityList()){
                if(workerActivity.getActivity().equals(activity)){
                    output += String.format(Locale.GERMANY,"%s\t%.1f Hrs\n", worker.getInitials(),workerActivity.getWorkTime().getTime());
                }
            }
        }
        return output;
    }
    public String hoursOverview(WorkerActivity workerActivity){
        // Gee
        return workerActivity.prettyPrintData();
    }

    public void markProjectFinished(Project project) throws OperationNotAllowedException {
        // Daniel
        if (project.getProjectLeader().equals(loggedInWorker)) {
            project.finishProject();
        } else {
            throw new OperationNotAllowedException("Only project leaders can finish projects");
        }
    }

    public boolean isProjectFinished(Project project) {
        // Daniel
        return project.getIsFinished();
    }

    // Date
    public void setDateServer(DateServer dateServer) {
        //to do
    }
    public void addWorkerToActivity(int projectNumber, String activityId, String workerInitials) throws OperationNotAllowedException {
        // Daniel
        loggedInTestError();
        if (!isProjectLeader(projectNumber, loggedInWorker.getInitials())) {
            throw new OperationNotAllowedException("Only project leaders can assign workers to activities");
        }
        Project project = getProjectWithNumber(projectNumber);
        Activity activity = project.getActivity(activityId);
        Worker worker = getWorkerWithInitials(workerInitials);
        activity.addWorker(worker);
        worker.addWorkerActivity(activity);
    }

    private boolean isProjectLeader(int projectNumber, String initials) throws OperationNotAllowedException {
        //Daniel
        Project project = getProjectWithNumber(projectNumber);
        if (!project.hasProjectLeader()) {
            return false; // Missing test
        }
        String projectLeader = project.getProjectLeader().getInitials();
        return projectLeader.equals(initials);
    }

    public List<Worker> WorkersAssignedToActivity(int projectNumber, String activityId) throws OperationNotAllowedException {
        // Daniel
        Project project = getProjectWithNumber(projectNumber);
        Activity activity = project.getActivity(activityId);
        return activity.getWorkerList();
    }

    public WorkerActivity findWorkerActivity(String initials, String activity) throws OperationNotAllowedException {
        //Jonas
        Worker worker = getWorkerWithInitials(initials);
        WorkerActivity workerActivity = worker.getWorkerActivity(activity);
        if (workerActivity == null){
            throw new OperationNotAllowedException(initials + " dont have activity: "+ activity + " in its workerActivityList");
        }
        return workerActivity;
    }
}