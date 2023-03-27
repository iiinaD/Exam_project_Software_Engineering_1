package dtu.system.unit_tests.cucumber;

import dtu.system.app.Application;
import dtu.system.app.ErrorMessageHolder;
import dtu.system.app.OperationNotAllowedException;
import dtu.system.domain.Activity;
import dtu.system.domain.Worker;
import dtu.system.domain.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

	Worker worker;
	Application app;
	ErrorMessageHolder errorMessage;

	public StepDefinitions(Application app, ErrorMessageHolder errorMessage) {
		//Jonas
		this.app = app;
		this.errorMessage = errorMessage;
	}
	@Given("a worker with the name {string} exists")
	public void aWorkerWithTheNameExists(String initials) throws OperationNotAllowedException
	{
		this.worker = new Worker(initials);
		app.addNewWorker(worker);
	}
	@Given("a worker with the name {string} is logged in")
	public void aWorkerWithTheNameIsLoggedIn(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Given("a project with the number  {int} and  name {string} exists")
	public void aProjectWithTheNumberAndNameExists(Integer int1, String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("the worker tries to change the name of the project to {string}")
	public void theWorkerTriesToChangeTheNameOfTheProjectTo(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the name of the project changes to {string}")
	public void theNameOfTheProjectChangesTo(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Given("two workers with the names {string} and {string} exists")
	public void twoWorkersWithTheNamesAndExists(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("{string} tries to assign {string} as project leader")
	public void triesToAssignAsProjectLeader(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("{string} becomes the project leader")
	public void becomesTheProjectLeader(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the worker tries to create a new project with the number {int}")
	public void theWorkerTriesToCreateANewProjectWithTheNumber(Integer projectNumber) {
		try {
			app.createProject("This is a new project");
			Project createdProject = app.getProjectList().get(0);
			assertEquals(createdProject.getProjectNumber(), projectNumber);
		} catch (OperationNotAllowedException exception) {
			errorMessage.setErrorMessage(exception.getMessage());
		}
	}
	@Then("the new project gets created")
	public void theNewProjectGetsCreated() {
		assertTrue(app.getProjectList().size() > 0);
	}
	@Given("the worker is not logged in")
	public void theWorkerIsNotLoggedIn() {
		assertEquals(null, app.getLoggedInWorker());
	}
	@Then("the new project does not get created")
	public void theNewProjectDoesNotGetCreated() {
		assertEquals(0,app.getProjectList().size());
	}


	@Given("a project with the number  {int} exists")
	public void aProjectWithTheNumberExists(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}



	@Given("there is a worker with initials {string}")
	public void thereIsAWorkerWithInitials(String initials) {
		//Jonas
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

		assertTrue(app.isWorkerInWorkerList(worker));
	}

	@Then("the worker exist in systems worker List")
	public void theWorkerExistInSystemsWorkerList() {
		assertTrue(app.isWorkerInWorkerList(worker));
	}

	@And("the worker can login using his initial {string} to login")
	public void theWorkerCanLoginUsingHisInitialToLogin(String initials) {
		//Jonas
		app.logIn(initials);
	}

	@And("systems has a logged in worker")
	public void systemsHasALoggedInWorker() {
		//Jonas
		assertTrue(app.getLoggedInStatus());
	}

	@Then("the worker is logged in")
	public void theWorkerIsLoggedIn() {
		//Jonas
		app.logIn(worker.getInitials());
		assertEquals(worker, app.getLoggedInWorker());
	}


	@Given("a worker with the name “jodl” exists")
	public void aWorkerWithTheNameJodlExists() throws OperationNotAllowedException {
		Worker JODI = new Worker("jodi");
		app.addNewWorker(JODI);
	}

	@Given("“jodl” is logged in")
	public void jodlIsLoggedIn() {
		app.logIn("jodi");
	}

	Activity activity = new Activity();
	@Given("there is a project {string} with an activity {string}")
	public void thereIsAProjectWithAnActivity(String string, String string2) {
		//
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
		activity.setDescription("Debugging software");
	}

	@When("the worker set the description of an activity of “Do not give up on life”")
	public void theWorkerSetTheDescriptionOfAnActivityOfDoNotGiveUpOnLife() {
		activity.setDescription("Do not give up on life");
	}

	@Then("the description of the activity should be “Do not give up on life”")
	public void theDescriptionOfTheActivityShouldBeDoNotGiveUpOnLife() {
		assertEquals("Do not give up on life",activity.description);
	}

	@Given("the activity has a budget time of {int}")
	public void theActivityHasABudgetTimeOf(Integer int1) {
		activity.setBudgetTime(int1);
	}

	@When("the worker changes the budget time to {int}")
	public void theWorkerChangesTheBudgetTimeTo(Integer int1) {
		activity.setBudgetTime(int1);
	}

	@Then("the budget time of the activity should be {int}")
	public void theBudgetTimeOfTheActivityShouldBe(Integer int1) {
		assertEquals(int1, activity.budgetTime);
	}

	@Given("there is a worker with initials {string} logged in to the system")
	public void thereIsAWorkerWithInitialsLoggedInToTheSystem(String initials) throws OperationNotAllowedException {
		//Jonas
		if (!app.isWorkerInWorkerList(worker)){
			if (worker == null){
				this.worker = new Worker(initials);
			}
			app.addNewWorker(worker);
		}
		app.logIn(worker.getInitials());
		assertTrue(app.isWorkerInWorkerList(worker));
	}

	@Given("a worker with the name {string} does not exist")
	public void aWorkerWithTheNameDoesNotExist(String initials)
	{
		this.worker = new Worker(initials);

		assertFalse(app.isWorkerInWorkerList(worker));
	}

	@When("the worker creates a new worker with this name")
	public void theWorkerCreatesANewWorkerWithThisName()
	{
		try
		{
			app.addNewWorker(worker);
		}
		catch(OperationNotAllowedException e)
		{
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("a worker by the name of of {string} has been created")
	public void aWorkerByTheNameOfOfHasBeenCreated(String string)
	{
		assertTrue(app.isWorkerInWorkerList(worker));
	}

	@Then("an error message {string} is given")
	public void anErrorMessageIsGiven(String errorMessage)
	{
		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
	}

	@And("the error message {string} is given")
	public void theErrorMessageIsGiven(String errorMessage) {
		assertEquals(errorMessage,this.errorMessage.getErrorMessage());
	}
}
