package dtu.system.app;

import dtu.system.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Application {

    private ArrayList<Worker> workerList = new ArrayList<>();
    private Worker loggedInWorker;
    private Boolean loggedIn = false;
    private List<Project> projectList = new ArrayList<>();
    private DateServer dateServer = new DateServer();

    // Worker in system Related
    public void addNewWorker(Worker worker) throws OperationNotAllowedException {
        // Danny
        if (isWorkerInWorkerList(worker.getInitials())){
            throw new OperationNotAllowedException("A worker with these initials already exists in the system.");
        }
        else {
            if (worker.getInitials().length() < 2 || worker.getInitials().length() > 4) {
                throw new OperationNotAllowedException("Worker initials can't contain less than 2 or more than 4 characters.");
            }
            else if(!worker.getInitials().matches("[a-zA-Z]+")) {
                throw new OperationNotAllowedException("Worker initials can't contain numbers or special characters.");
            }
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
        validProjectNumberTest(projectNumber);
        for (Project p : projectList){
            if (p.getProjectNumber() == projectNumber) {
                return p;
            }
        }

        throw new OperationNotAllowedException("No project with the id " + projectNumber + " exists in the system");
    }

    public boolean hasProjectWithNumber(int projectNumber) {
        // Daniel
        try {
            getProjectWithNumber(projectNumber);
        } catch (OperationNotAllowedException e) {
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
        if(project.getIsFinished()){
            throw new OperationNotAllowedException("Cannot add activity: Project is completed!");
        }
        return project.addActivity();
    }

    public Activity addActivityToProject(Project project, String activityName, String activityDescription) throws OperationNotAllowedException {
        // Danny
        loggedInTestError();
        if(project.getIsFinished()){
            throw new OperationNotAllowedException("Cannot add activity: Project is completed!");
        }
        return project.addActivity(activityName, activityDescription);
    }

    public void setActivityBudgetTime(Activity activity, HalfHours halfHours) throws OperationNotAllowedException {
        //Gee
        activity.setBudgetTime(halfHours);
    }
    public HalfHours getActivityBudgetTime(Activity activity){
        //Gee
        return activity.getBudgetTime();
    }
    public void setActivityDescription(Activity activity, String description) throws OperationNotAllowedException {
        //Gee
        activity.setDescription(description);
    }
    public String getActivityDescription(Activity activity) {
        //Gee
        return activity.getDescription();
    }

    public Activity getActivityFromProject(int projectNumber, String activityId) throws OperationNotAllowedException {
        // Jonas
        validActivityIdTest(activityId);
        Activity activity = getProjectWithNumber(projectNumber).getActivity(activityId);
        if (activity == null){
            throw new OperationNotAllowedException("The activity with id: " + activityId + " does not exist");
        }
        return activity;
    }

    public void activityPlanStartAndEnd(int projectNumber, String activityId, int week0, int week1, int year0, int year1) throws OperationNotAllowedException {
        // Jonas
        validActivityIdTest(activityId);
        Activity a = getActivityFromProject(projectNumber, activityId);
        if (a != null){
            a.setStartEndWeekAndYears(week0, week1, year0, year1);
        }
    }

    // Workers Activity's
    public void incrementWorkTime(Worker worker, Activity activity, int hours, int minutes) throws OperationNotAllowedException {
        // Gee
        if(!worker.incrementWorkTime(activity, hours, minutes)){
            throw new OperationNotAllowedException("This worker doesn't have any activities yet.");
        }
    }

    public WorkerActivity addActivityToWorker(Worker worker, Activity activity) throws OperationNotAllowedException {
        // Gee
        WorkerActivity workerActivity = worker.addWorkerActivity(activity);
        if(workerActivity == null){
            throw new OperationNotAllowedException("Worker already has that activity!");
        }
        return workerActivity;
    }

    public  String hoursOverview(Worker worker){
        //Gee
        StringBuilder output = new StringBuilder();
        List<WorkerActivity> workerActivityList = worker.getWorkerActivityList();
        if(workerList.isEmpty()) return "This worker has no activity";
        for(WorkerActivity workerActivity : workerActivityList){
            output.append(workerActivity.data()).append("\n");
        }
        return output.toString();
    }

    public  String hoursOverview(Activity activity){
        //Gee
        StringBuilder output = new StringBuilder();
        List<Worker> workerList = activity.getWorkerList();
        if(workerList.isEmpty()) return "No workers in this activity";
        output.append(String.format("%s\t%s\n", activity.getActivityId(), activity.getParentProject().getProjectName()));
        for(Worker worker : workerList){
            for(WorkerActivity workerActivity: worker.getWorkerActivityList()){
                if(workerActivity.getActivity().equals(activity)){
                    output.append(String.format(Locale.GERMANY, "%s\t%.1f Hrs\n", worker.getInitials(), workerActivity.getWorkTime().getTime()));
                }
            }
        }
        return output.toString();
    }

    public String hoursOverview(WorkerActivity workerActivity){
        // Gee
        return workerActivity.data();
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


    public void addWorkerToActivity(int projectNumber, String activityId, String workerInitials) throws OperationNotAllowedException {
        // Daniel
        loggedInTestError();
        if (!isProjectLeader(projectNumber, loggedInWorker.getInitials())) {
            throw new OperationNotAllowedException("Only project leaders can assign workers to activities.");
        }
        validActivityIdTest(activityId);
        Project project = getProjectWithNumber(projectNumber);
        Activity activity = project.getActivity(activityId);
        Worker worker = getWorkerWithInitials(workerInitials);
        if(!activity.addWorker(worker)){
            throw new OperationNotAllowedException(worker.getInitials() + " is already in the list!");
        }
        worker.addWorkerActivity(activity);
    }

    private boolean isProjectLeader(int projectNumber, String initials) throws OperationNotAllowedException {
        //Daniel
        Project project = getProjectWithNumber(projectNumber);
        if (!project.hasProjectLeader()) {
            return false;
        }
        String projectLeader = project.getProjectLeader().getInitials();
        return projectLeader.equals(initials);
    }

    public List<Worker> workersAssignedToActivity(int projectNumber, String activityId) throws OperationNotAllowedException {
        // Daniel
        validActivityIdTest(activityId);
        Project project = getProjectWithNumber(projectNumber);
        Activity activity = project.getActivity(activityId);
        return activity.getWorkerList();
    }

    public WorkerActivity findWorkerActivity(String initials, String activityId) throws OperationNotAllowedException {
        //Jonas
        validActivityIdTest(activityId);
        Worker worker = getWorkerWithInitials(initials);
        WorkerActivity workerActivity = worker.getWorkerActivity(activityId);
        if (workerActivity == null){
            throw new OperationNotAllowedException(initials + " dont have activity: "+ activityId + " in its workerActivityList");
        }
        return workerActivity;
    }

    public ArrayList<Activity> activitiesInWeekAndYear(int week, int year) throws OperationNotAllowedException {
        // Jonas
        validWeekYearTest(week, year);
        ArrayList<Activity> activityList = new ArrayList<>();
        for (Project project : projectList){
            for (Activity activity : project.getActivityList()){
                if (activity.isInGivenWeekAndYear(week, year)){
                    activityList.add(activity);
                }
            }
        }
        return activityList;
    }

    private void validWeekYearTest(int week, int year) throws OperationNotAllowedException {
        if(week < 1 || week >52){
            throw new OperationNotAllowedException("Invalid week: Week must be between 1-52");
        }
        if(year < 1000 || year >9999){
            throw new OperationNotAllowedException("Invalid year: Year must be between 1000-9999");
        }
    }

    public String timeSchedule(int week, int year) throws OperationNotAllowedException {
        // Jonas
        String print = "\n" + "Worker overview of week "+ week + " in year " + year +"\n\n";
        ArrayList<Activity> activityList = activitiesInWeekAndYear(week, year);
        Boolean foundOne = false;

        for (Worker worker : workerList){
            print += "Worker \"" + worker.getInitials() + "\":\n";
            for (Activity activity : activityList){
                if (activity.isWorkerAssigned(worker.getInitials())){
                    foundOne = true;
                    print += activity.overview(1,false, false, false);
                }
            }
            if (!foundOne){
                print += "\t<empty>\n";
            }
            print += "\n";
            foundOne = false;
        }
        return print;
    }

    public String getProjectOverview(int projectNumber) throws OperationNotAllowedException {
        // Jonas
        return getProjectWithNumber(projectNumber).overview(1);
    }

    public String getActivityOverview(String activityId) throws OperationNotAllowedException {
        // Jonas
        validActivityIdTest(activityId);
        Activity activity = getActivityFromProject(getProjectNumberFromActivityId(activityId), activityId);
        return activity.overview(1, true, true, true);
    }

    public int getProjectNumberFromActivityId(String activityId) throws OperationNotAllowedException{
        // Jonas
        validActivityIdTest(activityId);
        return Integer.valueOf(activityId.substring(0,5));
    }
    
    public void changeActivityName(Activity activity, String newName) throws OperationNotAllowedException {
        // Daniel
        activity.setActivityName(newName);
    }

    public void validProjectNumberTest(int number) throws OperationNotAllowedException{
        if(number < 10000 ||number > 99999 ){
            throw new OperationNotAllowedException("Project number invalid: Incorrect format. Should be [Year]+[3-digit number]");
        }
    }
    
    public void validActivityIdTest(String id) throws OperationNotAllowedException {
        if (!id.contains("-")) {
            //missing "-"
            throw new OperationNotAllowedException("Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]");
        }
        String[] strings = id.split("-", 0);
        if (strings.length != 2) {
            //missing front or back
            throw new OperationNotAllowedException("Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]");
        }
        if (!strings[0].matches("[0-9]+") || strings[0].length() != 5) {
            //should contain numbers only and correct length
            throw new OperationNotAllowedException("Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]");
        }
        if (!strings[1].matches("[0-9]+") || strings[1].length() != 3) {
            //ditto
            throw new OperationNotAllowedException("Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]");
        }
    }



    public String getStringActiveProjects() {
        // Jonas
        String print = "";
        Boolean foundOne = false;
        print += "\t----- All Active projects ----- \n";
        for (Project project : projectList) {
            if (!project.getIsFinished()) {
                foundOne = true;
                print += project.info(2) + "\n";
            }
        }
        if (projectList.isEmpty() || !foundOne){
            print += "\t\t<empty>\n";
        }
        return print;

    }
}
