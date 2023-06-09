package dtu.system.unit_tests.cucumber;

import dtu.system.app.Application;
import dtu.system.app.DateServer;
import dtu.system.app.OperationNotAllowedException;
import dtu.system.domain.Activity;
import dtu.system.domain.HalfHours;
import dtu.system.domain.Worker;
import dtu.system.domain.Project;
import dtu.system.domain.WorkerActivity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

	private Worker worker;
	private Application app;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	private Activity activity;
	private int projectNumberTemp;
	private HalfHours halfHours;
	private DateServer date;
	private Calendar currentDate;
	private WorkerActivity workerActivity;
	private String string;
	private DateServer dateHolder = new DateServer();
	private Activity activity2;
	private ArrayList<Activity> activityList = new ArrayList<>();
	private int week;
	private int year;


	public StepDefinitions(Application app, ErrorMessageHolder errorMessage) {
		// Jonas
		this.app = app;
		this.errorMessageHolder = errorMessage;
	}


	@Given("a project with the number {int} and name {string} exists")
	public void aProjectWithTheNumberAndNameExists(Integer projectNumber, String projectName) throws OperationNotAllowedException {
		// Daniel
		app.createProject(projectName);
		assertTrue(app.hasProjectWithNumber(projectNumber));
	}

	@When("the worker tries to change the name of the project {int} to {string}")
	public void theWorkerTriesToChangeTheNameOfTheProjectTo(int projectNumber, String newProjectName) {
		// Daniel
		try {
			app.changeProjectName(projectNumber,newProjectName);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the name of the project {int} changes to {string}")
	public void theNameOfTheProjectChangesTo(int projectNumber, String newProjectName) throws OperationNotAllowedException {
		// Daniel
		assertEquals(app.getProjectWithNumber(projectNumber).getName(),newProjectName);
	}

	@Given("two workers with the names {string} and {string} exists")
	public void twoWorkersWithTheNamesAndExists(String worker1, String worker2) throws OperationNotAllowedException {
		// Daniel
		worker = new Worker(worker1);
		app.addNewWorker(worker);
		worker = new Worker(worker2);
		app.addNewWorker(worker);
		assertTrue(app.isWorkerInWorkerList(worker1) && app.isWorkerInWorkerList(worker2));
	}

	@And("the worker {string} is logged in")
	public void theWorkerIsLoggedIn(String worker) throws OperationNotAllowedException {
		// Daniel
		app.logIn(worker);
		assertTrue(app.getLoggedInStatus());
	}

	@And("a project with the number {int} exists in the application")
	public void aProjectWithTheNumberExistsInTheApplication(Integer projectNumber) throws OperationNotAllowedException {
		// Daniel
		project = app.createProject("Very important project");
		assertTrue(app.hasProjectWithNumber(projectNumber));
	}

	@When("{string} is assigned as project leader to the project with number {int}")
	public void isAssignedAsProjectLeaderToTheProjectWithNumber(String worker, int projectNumber) {
		// Daniel
		try {
			app.setProjectLeader(projectNumber, worker);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("{string} becomes the project leader of the project {int}")
	public void becomesTheProjectLeaderOfTheProject(String leader, int projectNumber) throws OperationNotAllowedException {
		// Daniel
		assertTrue(app.getProjectWithNumber(projectNumber).isProjectLeader(new Worker(leader)));
	}

	@When("the worker tries to create a new project with the number {int}")
	public void theWorkerTriesToCreateANewProjectWithTheNumber(Integer projectNumber) {
		// Daniel
		this.projectNumberTemp = projectNumber;
		try {
			app.createProject("This is a new project");
		} catch (OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the new project gets created")
	public void theNewProjectGetsCreated() throws OperationNotAllowedException {
		// Jonas
		assertEquals(app.getProjectWithNumber(projectNumberTemp).getProjectNumber(), projectNumberTemp);
	}

	@Given("the worker is not logged in")
	public void theWorkerIsNotLoggedIn() {
		// Jonas
		assertFalse(app.getLoggedInStatus(), "App logged in status is true");
		assertNull(app.getLoggedInWorker());
	}

	@Then("the new project {int} does not get created")
	public void theNewProjectDoesNotGetCreated(int number) {
		// Jonas
		try {
			app.getProjectWithNumber(number);
		} catch (OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("there is a worker with initials {string}")
	public void thereIsAWorkerWithInitials(String initials) {
		// Jonas
		this.worker = new Worker(initials);
	}

	@When("the worker is added to systems worker list")
	public void theWorkerIsAddedToSystemsWorkerList() throws OperationNotAllowedException {
		// Jonas
		app.addNewWorker(worker);
	}

	@And("the worker can login using his initial {string} to login")
	public void theWorkerCanLoginUsingHisInitialToLogin(String initials) {
		// Jonas
		try {
			app.logIn(initials);
		} catch (OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@And("systems has a logged in worker")
	public void systemsHasALoggedInWorker() {
		// Jonas
		assertTrue(app.getLoggedInStatus());
	}

	@Then("the worker is logged in")
	public void theWorkerIsLoggedIn() throws OperationNotAllowedException {
		// Jonas
		app.logIn(worker.getInitials());
		assertEquals(worker, app.getLoggedInWorker());
	}

	@Given("a worker with the name {string} exists")
	public void aWorkerWithTheNameExists(String initials) throws OperationNotAllowedException {
		// Jonas
		this.worker = new Worker(initials);
		app.addNewWorker(worker);
	}

	@Given("a worker with the name {string} dont exist")
	public void aWorkerWithTheNameDontExist(String initials) {
		//Jonas
		this.worker = new Worker(initials);
		assertFalse(app.isWorkerInWorkerList(worker.getInitials()));
	}

	@Given("{string} is logged in")
	public void IsLoggedIn(String initials) throws OperationNotAllowedException {
		// Jonas
		app.logIn(initials);
	}

	@Given("there is a worker with initials {string} logged in to the system")
	public void thereIsAWorkerWithInitialsLoggedInToTheSystem(String initials) throws OperationNotAllowedException {
		// Jonas
		if (!app.isWorkerInWorkerList(initials)){
			if (worker == null){
				this.worker = new Worker(initials);
			}
			app.addNewWorker(worker);
		}
		app.logIn(worker.getInitials());
		assertTrue(app.isWorkerInWorkerList(initials));
	}

	//calendar.feature
	@Given("the date server is running")
	public void theDateServerIsRunning() {
		//Gee
		date = new DateServer();
	}

	@When("I request the date")
	public void iRequestTheDate() {
		//Gee
		currentDate = date.getDate();
	}

	@Then("the day should be the current date")
	public void theDayShouldBeTheCurrentDate() {
		//Gee
		Calendar calendar = new GregorianCalendar();
		Calendar expectedDate = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(expectedDate.getTime(), currentDate.getTime());
	}

	@Given("a worker with the initials {string} exists")
	public void aWorkerWithTheInitialsExists(String initials) throws OperationNotAllowedException {
		//Gee
		this.worker = new Worker(initials);
		app.addNewWorker(worker);
	}

	@Given("the project has an empty activity list")
	public void theProjectHasAnEmptyActivityList() {
		//Gee
		assertTrue(project.getActivityList().isEmpty());
	}

	@When("the worker creates a new Activity to the project.")
	public void theWorkerCreatesANewActivityToTheProject() {
		//Jonas
		try {
			this.activity = app.addActivityToProject(project);
		} catch (OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project has the activity {string} in its activity list.")
	public void theProjectHasTheActivityInItsActivityList(String string) {
		//Gee
		assertTrue(project.getActivityList().contains(activity));
	}


	@And("there is a project {int} in the system")
	public void thereIsAProjectInTheSystem(int projectNumber) throws OperationNotAllowedException {
		//Jonas
		if (!app.getLoggedInStatus()) {
			app.logIn(worker.getInitials());
			app.createProject("name");
			app.logOut();
		} else {
			app.createProject("name");
		}
		this.project = app.getProjectWithNumber(projectNumber);
		assertEquals(project.getProjectNumber(), projectNumber);
	}

	@Given("console takes input {int} hours {int} minuts")
	public void consoleTakesInputHoursMinuts(int hour, int min) {
		// Jonas
		this.halfHours = new HalfHours(hour, min);
	}


	@Then("halfHours is {double}")
	public void halfHoursIs(double time) {
		// Jonas
		assertEquals(time, halfHours.getTime());
	}

	@When("halfHours is ingrementes with {int} hours {int} minuts")
	public void halfhoursIsIngrementesWithHoursMinuts(int hour, int min) {
		// Jonas
		halfHours.increment(hour, min);
	}

	@Given("a worker with the initials {string} does not exist")
	public void aWorkerWithTheInitialsDoesNotExist(String initials) {
		// Danny
		this.worker = new Worker(initials);

		assertFalse(app.isWorkerInWorkerList(initials));
	}

	@When("a worker creates a new worker using these initials")
	public void theWorkerCreatesANewWorkerUsingTheseInitials() {
		// Danny
		try {
			app.addNewWorker(worker);
		} catch(OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("a worker with these initials exists in the system")
	public void aWorkerWithTheseInitialsExistInTheSystem() {
		// Danny
		assertTrue(app.isWorkerInWorkerList(worker.getInitials()));
	}

	@Then("an error message {string} is given")
	public void anErrorMessageIsGiven(String errorMessage) {
		// Danny
		assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
	}

	@Given("the project has activity {string} in its activity list")
	public void theProjectHasActivityInItsActivityList(String activityId) throws OperationNotAllowedException {
		// Danny
		activity = app.addActivityToProject(project);

		assertEquals(activityId, activity.getActivityId());
		assertTrue(app.getProjectWithNumber(project.getProjectNumber()).getActivityList().contains(activity));
	}

	@Given("the worker has an activity {string} in his activity list")
	public void theWorkerHasAnActivityInHisActivityList(String activityId) {
		// Danny
		try{
			workerActivity = app.addActivityToWorker(worker, activity);
		} catch(OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}

		assertEquals(activityId, workerActivity.getActivity().getActivityId());
		assertTrue(app.getWorkerList().get(app.getWorkerList().indexOf(worker)).getWorkerActivityList().contains(workerActivity));
	}

	@Given("the worker has worked for {int} hours and {int} minutes on the activity")
	public void theWorkerHasWorkedForHoursAndMinutesOnTheActivity(int hours, int minutes) throws OperationNotAllowedException {
		// Danny
		app.incrementWorkTime(worker, activity, hours, minutes);
	}

	@Then("the worker has spent {int} hours and {int} minutes on the activity")
	public void theWorkerHasSpentHoursAndMinutesOnTheActivity(int hours, int minutes) {
		// Danny
		workerActivity.incrementWorkTime(hours, minutes);
		int workerActivityIndex = app.getWorkerList().get(app.getWorkerList().indexOf(worker)).getWorkerActivityList().indexOf(workerActivity);

		assertEquals(workerActivity.getWorkTime(), app.getWorkerList().get(app.getWorkerList().indexOf(worker)).getWorkerActivityList().get(workerActivityIndex).getWorkTime());
	}

	@Given("the worker has no activities in his activity list")
	public void theWorkerHasNoActivitiesInHisActivityList(){
		// Danny
		assertTrue(app.getWorkerList().get(app.getWorkerList().indexOf(worker)).getWorkerActivityList().isEmpty());
	}

	@When("the worker tries to edit his working hours")
	public void theWorkerTriesToEditHisWorkingHours() {
		// Danny
		try {
			app.incrementWorkTime(worker, activity, 1, 0);
		}
		catch(OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the worker creates a new activity with the name {string} and the description {string}")
	public void theWorkerCreatesANewActivityWithTheNameAndTheDescription(String activityName, String activityDescription) {
		// Danny
		try {
			activity = app.addActivityToProject(project, activityName, activityDescription);
		} catch(OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project has activity {string} in its activity list with the given name and description")
	public void theProjectHasActivityInItsActivityListWithTheGivenNameAndDescription(String activityId) throws OperationNotAllowedException {
		// Danny
		int projectActivityIndex = app.getProjectWithNumber(project.getProjectNumber()).getActivityList().indexOf(activity);

		assertEquals(activityId, app.getProjectWithNumber(project.getProjectNumber()).getActivityList().get(projectActivityIndex).getActivityId());
		assertEquals(activity.getActivityName(), app.getProjectWithNumber(project.getProjectNumber()).getActivityList().get(projectActivityIndex).getActivityName());
		assertEquals(activity.getDescription(), app.getProjectWithNumber(project.getProjectNumber()).getActivityList().get(projectActivityIndex).getDescription());
	}

	@Given("the project has no project leader")
	public void theProjectHasNoProjectLeader() throws OperationNotAllowedException {
		// Danny
		assertFalse(app.getProjectWithNumber(project.getProjectNumber()).hasProjectLeader() );
	}

	@Given("a project with the number {int} does not exist")
	public void aProjectWithTheNumberDoesNotExist(int projectNumber) {
		// Danny
		assertFalse(app.hasProjectWithNumber(projectNumber));
	}

	@Given("{string} hasn't already been assigned as the project leader")
	public void hasnTAlreadyBeenAssignedAsTheProjectLeader(String initials) throws OperationNotAllowedException {
		// Danny
		assertFalse(app.getProjectWithNumber(project.getProjectNumber()).isProjectLeader(new Worker(initials)));
	}

	@When("the worker increments his working hours by {int} hours and {int} minutes")
	public void theWorkerIncrementsHisWorkingHoursByHoursAndMinutes(int hours, int minutes) throws OperationNotAllowedException {
		// Danny
		app.incrementWorkTime(worker, activity, hours, minutes);
	}

	//access_hours_overview.feature

	@Given("a project named {string} with an activity {string}")
	public void aProjectNamedWithAnActivity(String string, String string2) {
		try {
			project = app.createProject(string); //create project
			activity = app.addActivityToProject(project);
			app.setProjectLeader(project.getProjectNumber(), app.getLoggedInWorker().getInitials());

		} catch (OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("add a activity {string}")
	public void addAActivity(String string) {
		// Gee
		try {
			activity2 = app.addActivityToProject(project);//add an activity
		} catch (OperationNotAllowedException e) {
			throw new RuntimeException(e);
		}
	}

	@Given("{string} worked for {int} hours and {int} minutes on activity {string}")
	public void workedForHoursAndMinutesOnActivity(String string, Integer int1, Integer int2, String string2) {
		try {
			app.incrementWorkTime(worker, app.getWorkerActivity(worker.getInitials(), string2).getActivity(), int1, int2);
		} catch (OperationNotAllowedException e) {
			throw new RuntimeException(e);
		}
	}

	@When("the worker access hours overview for activity {string}")
	public void theWorkerAccessHoursOverviewForActivity(String string) {
		//Gee
		try {
			this.string = app.hoursOverview(app.getWorkerActivity(worker.getInitials(), string));
		} catch (OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the worker access personal hours overview")
	public void theWorkerAccessPersonalHoursOverview() {
		//Gee
		this.string = app.hoursOverview(worker);
	}

	@When("the worker access activity hours overview")
	public void theWorkerAccessActivityHoursOverview() {
		//Gee
		this.string = app.hoursOverview(activity);
	}

	@Then("the worker should see")
	public void theWorkerShouldSee(String docString) {
		//Gee
		assertEquals(docString, string);
	}

	//edit_activities.feature
	@Given("the activity has a description of {string}")
	public void theActivityHasADescriptionOf(String string) throws OperationNotAllowedException {
		//Gee
		app.setActivityDescription(activity, string);
	}

	@When("the worker set the description of an activity of {string}")
	public void theWorkerSetTheDescriptionOfAnActivityOf(String string) throws OperationNotAllowedException {
		//Gee
		app.setActivityDescription(activity, string);
	}

	@Then("the description of the activity should be {string}")
	public void theDescriptionOfTheActivityShouldBe(String string) {
		//Gee
		assertEquals(string, app.getActivityDescription(activity));
	}

	@Given("the activity has a budget time of {int} hours")
	public void theActivityHasABudgetTimeOfHours(Integer int1) throws OperationNotAllowedException {
		//Gee
		app.setActivityBudgetTime(activity,new HalfHours(int1, 0));
	}

	@When("the worker changes the budget time to {int} hours")
	public void theWorkerChangesTheBudgetTimeToHours(Integer int1) throws OperationNotAllowedException {
		//Gee
		app.setActivityBudgetTime(activity,new HalfHours(int1, 0));
	}

	@Then("the budget time of the activity should be {int} hours")
	public void theBudgetTimeOfTheActivityShouldBeHours(Integer int1) {
		//Gee
		assertEquals(new HalfHours(int1, 0).getTime(), app.getActivityBudgetTime(activity).getTime());
	}

	@And("a project with the number {int} and name {string} and leader {string} exists")
	public void aProjectWithTheNumberAndNameAndLeaderExists(int projectNumber, String projectName, String projectLeader) throws OperationNotAllowedException {
		// Daniel
		app.createProject("Very important project",app.getWorkerWithInitials(projectLeader));
		assertTrue(app.hasProjectWithNumber(projectNumber));
		project = app.getProjectWithNumber(projectNumber);
		String theAssignedLeader = project.getProjectLeader().getInitials();
		assertEquals(projectLeader,theAssignedLeader);
	}

	@When("the project leader tries to mark the project as finished")
	public void theProjectLeaderTriesToMarkTheProjectAsFinished() throws OperationNotAllowedException {
		// Daniel
		app.markProjectFinished(project);
	}

	@Then("the project is finished")
	public void theProjectIsFinished() {
		// Daniel
		assertTrue(app.isProjectFinished(project));
	}

	@When("the non project leader tries to mark the project as finished")
	public void theNonProjectLeaderTriesToMarkTheProjectAsFinished() {
		// Daniel
		try {
			app.markProjectFinished(project);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("it works")
	public void itWorks() {
		//Jonas
		int x = dateHolder.getDate().getWeeksInWeekYear();
		int y = dateHolder.getDate().getWeekYear();
		int z = dateHolder.getDate().get(Calendar.WEEK_OF_YEAR);
//		System.out.println("current year: " + y);
//		System.out.println("current week: " + z);
//		System.out.println( x + " weeks in year " + y);
		assertEquals(y, date.getCurrentYear(), "current year test");
		assertEquals(x, date.getNumberOfWeeksInYear(y), "number of weeks in current year");
		assertEquals(z, date.getCurrentWeek());
	}

	@Then("year {int} there is {int} weeks")
	public void iYearThereIsWeeks(int year, int week) {
		// Jonas
		assertEquals(week, date.getNumberOfWeeksInYear(year), "number of weeks in current year");
	}

	@When("the project leader {string} assigns the worker {string} to the activity")
	public void theProjectLeaderAssignsTheWorkerToTheActivity(String projectLeaderInitials, String workerInitials) {
		// Daniel
		try{
			app.setProjectLeader(project.getProjectNumber(),projectLeaderInitials);
			app.addWorkerToActivity(project.getProjectNumber(),activity.getActivityId(),workerInitials);
		}catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the worker {string} is assigned to the activity")
	public void theWorkerIsAssignedToTheActivity(String workerInitials) throws OperationNotAllowedException {
		// Daniel
		List<Worker> activityWorkerList = app.workersAssignedToActivity(project.getProjectNumber(),activity.getActivityId());
		assertEquals(activityWorkerList.get(0).getInitials(), workerInitials);
	}

	@When("{string} assigns the worker {string} to the activity")
	public void AssignsTheWorkerToTheActivity(String worker, String workerInitials) throws OperationNotAllowedException {
		// Daniel
		app.logOut();
		app.logIn(worker);
		try {
			app.addWorkerToActivity(project.getProjectNumber(),activity.getActivityId(), workerInitials);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the activity is planned to start week {int} year {int} and end week {int} year {int}")
	public void theActivityIsPlannedToStartWeekYearAndEndWeekYear(int week0, int year0, int week1, int year1) {
		//Jonas
		try {
			app.activityPlanStartAndEnd(project.getProjectNumber(), activity.getActivityId(), week0, week1, year0, year1);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

		@Then("the planned number of weeks is {int}")
		public void thePlannedNumberOfWeeksIs(int weeks) {
			//Jonas
			assertEquals(weeks, activity.getBudgetWeeks());
		}

    @When("the worker try to acces activity {string}")
    public void theWorkerTryToAccesActivityItDontExist(String activity) {
			//Jonas
			try {
				app.getActivityFromProject(project.getProjectNumber(), activity);
			} catch (OperationNotAllowedException e){
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
    }

		@And("{string} has activity {string} in his activity list")
		public void hasActivityInHisActivityList(String initials, String activity) {
			// Jonas
			try {
				Activity activity1 = app.getWorkerActivity(initials, activity).getActivity();
				Activity activity2 = app.getActivityFromProject(project.getProjectNumber(), activity);
				assertEquals(activity1, activity2);
			} catch (OperationNotAllowedException e){
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
		}

    @And("{int} gets a new activity {string}")
    public void getsANewActivity(int projectNumber, String activityId) throws OperationNotAllowedException {
			// Jonas
			this.project = app.getProjectWithNumber(projectNumber);
			project.addActivity();
			this.activity = app.getActivityFromProject(projectNumber, activityId);
			assertEquals(activity.getActivityId(), activityId);
    }

		@And("{string} is added to {string}")
		public void isAddedTo(String initials, String activityId) {
			// Jonas
			int projectNumber = Integer.valueOf(activityId.substring(0,5));
			try {
				app.addWorkerToActivity(projectNumber, activityId, initials);
				assertTrue(app.getActivityFromProject(projectNumber, activityId).isWorkerAssigned(initials));
			} catch (OperationNotAllowedException e){
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
		}

		@When("a worker want to know which workers work in week {int} year {int}")
		public void aWorkerWantToKnowWhichWorkersWorkInWeekYear(int week, int year) {
			// Jonas
			try{
				this.week = week;
				this.year = year;
				this.activityList = app.activitiesInWeekAndYear(week,year);
			}catch (OperationNotAllowedException e){
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
		}

		@Then("a activityList will have length {int}")
		public void aActivityListWillHaveLength(int length) {
			// Jonas
			assertEquals(activityList.size(), length);
		}

		@When("the worker wants to view which workers are assigned the activity's a string is given")
		public void theWorkerWantsToViewWhichWorksAreAssignedTheAnticipatesAStringIsGiven() throws OperationNotAllowedException {
			// Jonas
			String print = app.timeSchedule(week, year);
			assertNotNull(print);
			System.out.println(print);
		}


		@Given("a worker wants to get an overview of project {int}")
		public void aWorkerWantsToGetAnOverviewOfProject(int projectNumber) throws OperationNotAllowedException {
			// Jonas
			String print = app.getProjectOverview(projectNumber);
			assertNotNull(print);
			System.out.println(print);
		}

		@Given("there is {int} projects int the system")
		public void thereIsProjectsIntTheSystem(int size) {
			// Jonas
			assertEquals(app.getProjectList().size(), size);
		}

		@Given("a worker wants to get an overview of activity {string}")
		public void aWorkerWantsToGetAnOverviewOfActivity(String activityId) throws OperationNotAllowedException {
			// Jonas
			String print = app.getActivityOverview(activityId);
			assertNotNull(print);
			System.out.println(print);
		}

		@Given("a project named {string} with an activity named {string}")
		public void aProjectNamedWithAnActivityNamed(String projectName, String activityName) {
			// Daniel
			try {
				project = app.createProject(projectName); //create project
				activity = app.addActivityToProject(project,activityName,"");
				//app.addActivityToWorker(worker, activity);
				app.setProjectLeader(project.getProjectNumber(), app.getLoggedInWorker().getInitials());
			} catch (OperationNotAllowedException e){
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
		}

		@When("a worker changes the activity name to {string}")
		public void aWorkerChangesTheActivityName(String newActivityName) throws OperationNotAllowedException {
			// Daniel
			app.changeActivityName(activity,newActivityName);
		}

		@Then("the activity name has changed to {string}")
		public void theActivityNameHasChanged(String newActivityName) {
			// Daniel
			assertEquals(newActivityName, activity.getActivityName());
		}

		@Given("a worker wants to get all projects that is still active")
		public void aWorkerWantsToGetAllProjectsThatIsStillActive() {
			// Jonas
			this.string = app.getStringActiveProjects();
			assertNotNull(string);
		}

		@Then("something correct is printed")
		public void somethingCorrectIsPrinted() {
			// Jonas
			System.out.println(string);
		}

		@Then("he marks project {int} as finished")
		public void heMarksProjectAsFinished(int projectNumber) throws OperationNotAllowedException {
			// Jonas
			this.project = app.getProjectWithNumber(projectNumber);
			app.markProjectFinished(project);
			assertTrue(project.getIsFinished());
		}

		@And("project {int} does not exist")
		public void projectDoesNotExist(int arg0) {
			assertFalse(app.hasProjectWithNumber(arg0));
		}

    @When("he registers he has worked for {int} hours and {int} minutes on activity {string}")
    public void heRegistersHeHasWorkedForHoursAndMinutesOnActivity(int hours, int min, String activityId) throws OperationNotAllowedException {
			// Jonas
			Activity activity1 = app.getActivityFromProject(app.getProjectNumberFromActivityId(activityId), activityId);
			app.incrementWorkTime(worker, activity1, hours, min);
    }

		@Then("the worker has worked {double} hours on activity {string}")
		public void theWorkerHasWorkedHoursOnActivity(double hours, String activityId) throws OperationNotAllowedException {
			// Jonas
			Activity activity1 = app.getActivityFromProject(app.getProjectNumberFromActivityId(activityId), activityId);
			double time = app.getWorkerActivity(worker.getInitials(), activity1.getActivityId()).getWorkTime().getTime();
			assertEquals(hours, time);
		}
}
