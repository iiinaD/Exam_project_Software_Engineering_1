package dtu.system.unit_tests.cucumber;

import dtu.system.app.Application;
import dtu.system.app.DateServer;
import dtu.system.app.ErrorMessageHolder;
import dtu.system.app.OperationNotAllowedException;
import dtu.system.domain.Activity;
import dtu.system.domain.HalfHours;
import dtu.system.domain.Worker;
import dtu.system.domain.Project;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.mockito.internal.matchers.Null;

import java.util.*;

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
	public void theWorkerIsLoggedIn(String worker) {
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
		try {
			app.getLoggedInWorker();
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
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
	@Then("the worker is in the systems worker list")
	public void isInTheSystemsWorkerList() {
		// Jonas
		assertTrue(app.isWorkerInWorkerList(worker.getInitials()));
	}

	@Then("the worker exist in systems worker List")
	public void theWorkerExistInSystemsWorkerList() {
		// Jonas
		assertTrue(app.isWorkerInWorkerList(worker.getInitials()));
	}

	@And("the worker can login using his initial {string} to login")
	public void theWorkerCanLoginUsingHisInitialToLogin(String initials) {
		// Jonas
		app.logIn(initials);
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

	@Given("{string} is logged in")
	public void jodlIsLoggedIn(String initials) {
		// Jonas
		app.logIn(initials);
	}

	@Given("there is a project {string} with an activity {string}")
	public void thereIsAProjectWithAnActivity(String string, String string2) throws OperationNotAllowedException {
		// Gee
		project = app.createProject(string);
		activity = project.addActivity();
	}

	@When("the worker removes the activity named {string}")
	public void theWorkerRemovesTheActivityNamed(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the activity named {string} should not exist in the activity list of project “{int}”")
	public void theActivityNamedShouldNotExistInTheActivityListOfProject(String string, Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("the activity has a description of “Debugging software”")
	public void theActivityHasADescriptionOfDebuggingSoftware() {
		// Gee
		activity.setDescription("Debugging software");
	}

	@When("the worker set the description of an activity of “Do not give up on life”")
	public void theWorkerSetTheDescriptionOfAnActivityOfDoNotGiveUpOnLife() {
		// Gee
		activity.setDescription("Do not give up on life");
	}

	@Then("the description of the activity should be “Do not give up on life”") // NEED FIX dont hard code fix cucumber
	public void theDescriptionOfTheActivityShouldBeDoNotGiveUpOnLife() {
		// Gee
		assertEquals("Do not give up on life",activity.description);
	}

	@Given("the activity has a budget time of {int}")
	public void theActivityHasABudgetTimeOf(Integer int1) {
		// Gee
		activity.setBudgetTime(int1);
	}

	@When("the worker changes the budget time to {int}")
	public void theWorkerChangesTheBudgetTimeTo(Integer int1) {
		// Gee
		activity.setBudgetTime(int1);
	}

	@Then("the budget time of the activity should be {int}")
	public void theBudgetTimeOfTheActivityShouldBe(Integer int1) {
		// Gee
		assertEquals(int1, activity.budgetTime);
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

	@Given("a worker with the initials {string} does not exist")
	public void aWorkerWithTheInitialsDoesNotExist(String initials) {
		// Danny
		this.worker = new Worker(initials);

		assertFalse(app.isWorkerInWorkerList(initials));
	}

	@When("the worker creates a new worker with the initials {string}")
	public void theWorkerCreatesANewWorkerWithTheInitials(String initials) {
		// Danny
		try {
			app.addNewWorker(worker);
		} catch(OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("a worker with the initials {string} has been created")
	public void aWorkerWithTheInitialsHasBeenCreated(String string) {
		// Danny
		assertTrue(app.isWorkerInWorkerList(worker.getInitials()));
	}

	@Then("an error message {string} is given")
	public void anErrorMessageIsGiven(String errorMessage) {
		// Danny
		assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
	}

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
	public void theWorkerCreatesANewActivityToTheProject() throws OperationNotAllowedException {
		//Jonas
		try {
			this.activity = app.addActivityToProject(project.getProjectNumber());
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
}
