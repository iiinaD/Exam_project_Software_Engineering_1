package dtu.system.unit_tests.cucumber;

import dtu.system.app.Application;
import dtu.system.app.DateServer;
import dtu.system.app.ErrorMessageHolder;
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

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

	Worker worker;
	Application app;
	ErrorMessageHolder errorMessageHolder;
	Project project;
	Activity activity;
	private int projectNumberTemp;
	HalfHours halfHours;
	private DateServer date;
	private Calendar currentDate;
	private WorkerActivity workerActivity;
	private String result;


	public StepDefinitions(Application app, ErrorMessageHolder errorMessage) {
		// Jonas
		this.app = app;
		this.errorMessageHolder = errorMessage;
	}

	@Given("a worker with the name {string} is logged in")
	public void aWorkerWithTheNameIsLoggedIn(String initials) throws OperationNotAllowedException {
		// Daniel
		worker = new Worker(initials);
		app.addNewWorker(worker);
		app.logIn(initials);
		assertTrue(app.getLoggedInStatus());
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
		worker = new Worker(worker1);
		app.addNewWorker(worker);
		worker = new Worker(worker2);
		app.addNewWorker(worker);
		assertTrue(app.isWorkerInWorkerList(worker1) && app.isWorkerInWorkerList(worker2));
	}
	@And("the worker {string} is logged in")
	public void theWorkerIsLoggedIn(String worker) throws OperationNotAllowedException {
		app.logIn(worker);
		assertTrue(app.getLoggedInStatus());
	}
	@And("a project with the number {int} exists in the application")
	public void aProjectWithTheNumberExistsInTheApplication(Integer projectNumber) throws OperationNotAllowedException {
		app.createProject("Very important project");
		assertTrue(app.hasProjectWithNumber(projectNumber));
	}
	@When("{string} is assigned as project leader to the project with number {int}")
	public void isAssignedAsProjectLeaderToTheProjectWithNumber(String worker, int projectNumber) throws OperationNotAllowedException {
		app.setProjectLeader(projectNumber, worker);
	}
	@Then("{string} becomes the project leader of the project {int}")
	public void becomesTheProjectLeaderOfTheProject(String leader, int projectNumber) throws OperationNotAllowedException {
		String newProjectLeader = app.getProjectWithNumber(projectNumber).getProjectLeader().getInitials();
		assertEquals(leader, newProjectLeader);
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
		//Project createdProject = app.getProjectList().get(0);
		//assertEquals(createdProject.getProjectNumber(),projectNumber);
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
	public void theWorkerCanLoginUsingHisInitialToLogin(String initials) throws OperationNotAllowedException {
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
	public void aWorkerWithTheNameJodlExists(String initials) throws OperationNotAllowedException {
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
	public void jodlIsLoggedIn(String initials) throws OperationNotAllowedException {
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
	public void the_date_server_is_running() {
		// Gee
		date = new DateServer();
	}

	@When("I request the date")
	public void i_request_the_date() {
		// Gee
		currentDate = date.getDate();
	}

	@Then("the day should be the current date")
	public void the_day_should_be_the_current_date() {
		// Gee
		Calendar calendar = new GregorianCalendar();
		Calendar expectedDate = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(expectedDate.getTime(), currentDate.getTime());
	}

	@Given("a worker with the initials {string} exists")
	public void a_worker_with_the_initials_jodl_exists(String initials) throws OperationNotAllowedException {
		// Gee
		this.worker = new Worker(initials);

		app.addNewWorker(worker);
	}

	@Given("the project has an empty activity list")
	public void the_project_has_an_empty_activity_list() {
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

	@Then("the project has activity {string} in its activity list.")
	public void the_project_has_activity_in_its_activity_list(String string) {
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
		assertEquals(halfHours.getTime(), time);
	}
	@When("halfHours is ingrementes with {int} hours {int} minuts")
	public void halfhoursIsIngrementesWithHoursMinuts(int hour, int min) {
		halfHours.increment(hour, min);
	}

	//////////////////////////////////////////////////////////////
	///// DANNY (for better overview during implementation) //////
	//////////////////////////////////////////////////////////////

	@Given("a worker with the initials {string} does not exist")
	public void aWorkerWithTheInitialsDoesNotExist(String initials) {
		// Danny
		this.worker = new Worker(initials);

		assertFalse(app.isWorkerInWorkerList(initials));
	}

	@When("the worker creates a new worker using these initials")
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
		workerActivity = app.addActivityToWorker(worker, activity);

		assertEquals(activityId, workerActivity.getActivity().getActivityId());
		assertTrue(app.getWorkerList().get(app.getWorkerList().indexOf(worker)).getWorkerActivityList().contains(workerActivity));
	}

	@Given("the worker has worked for {int} hours and {int} minutes on the activity")
	public void theWorkerHasWorkedForHoursAndMinutesOnTheActivity(int hours, int minutes) throws OperationNotAllowedException {
		// Danny
		app.incrementWorkTime(worker, activity, hours, minutes);
	}

	@When("the worker increments his working hours to {int} hours and {int} minutes")
	public void theWorkerIncrementsHisWorkingHoursToHoursAndMinutes(int hours, int minutes) throws OperationNotAllowedException {
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
		} catch(OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

    //////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////

	//access_hours_overview.feature

	@Given("a project named {string} with an activity {string}")
	public void a_project_named_with_an_activity(String string, String string2) {
		//app.logIn("xxxx");
		try {
			project = app.createProject(string); //create project
			activity = app.addActivityToProject(project); //add an activity
			//activity.setID(string2);
		} catch (OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("{string} worked for {int} hours on activity {string}")
	public void worked_for_hours_on_activity(String string, Integer int1, String string2) throws OperationNotAllowedException {
		app.addActivityToWorker(worker, activity);
		app.incrementWorkTime(worker, activity, int1, 0);
	}

	@When("the worker access hours overview for activity {string}")
	public void the_worker_access_hours_overview_for_activity(String string) {
		this.result = app.hoursOverview(this.worker, string);
	}

	@Then("the worker should see {string}")
	public void the_worker_should_see(String string) {
		assertEquals(string, result);
	}

	//edit_activities.feature
	@Given("the activity has a description of {string}")
	public void the_activity_has_a_description_of(String string) {
		app.setActivityDescription(activity, string);
	}

	@When("the worker set the description of an activity of {string}")
	public void the_worker_set_the_description_of_an_activity_of(String string) {
		app.setActivityDescription(activity, string);
	}

	@Then("the description of the activity should be {string}")
	public void the_description_of_the_activity_should_be(String string) {
		assertEquals(string, app.getActivityDescription(activity));
	}

	@Given("the activity has a budget time of {int} hours")
	public void the_activity_has_a_budget_time_of_hours(Integer int1) {
		app.setActivityBudgetTime(activity,new HalfHours(int1, 0));
	}

	@When("the worker changes the budget time to {int} hours")
	public void the_worker_changes_the_budget_time_to_hours(Integer int1) {
		app.setActivityBudgetTime(activity,new HalfHours(int1, 0));
	}

	@Then("the budget time of the activity should be {int} hours")
	public void the_budget_time_of_the_activity_should_be_hours(Integer int1) {
		assertEquals(new HalfHours(int1, 0).getTime(), app.getActivityBudgetTime(activity).getTime());
	}


}
