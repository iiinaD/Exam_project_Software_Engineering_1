package dtu.system.unit_tests.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.system.app.Application;
import dtu.system.domain.Worker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

public class StepDefinitions {

	Worker worker;
	Application app;

	public StepDefinitions(Application app) {
		//Jonas
		this.app = app;
		//this.errorMessageHolder = errorMessageHolder;
	}

	@Given("a worker with the name “daha” exists")
	public void aWorkerWithTheNameDahaExists() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Given("the worker is not logged in")
	public void theWorkerIsNotLoggedIn() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("the worker tries to create a new project with the number {int}")
	public void theWorkerTriesToCreateANewProjectWithTheNumber(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the new project gets created")
	public void theNewProjectGetsCreated() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the new project does not get created")
	public void theNewProjectDoesNotGetCreated() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("a worker with the name “daha” is logged in")
	public void aWorkerWithTheNameDahaIsLoggedIn() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Given("a project with the number  {int} and  name “Web projekt” exists")
	public void aProjectWithTheNumberAndNameWebProjektExists(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("the worker tries to change the name of the project to “Web projekt Google”")
	public void theWorkerTriesToChangeTheNameOfTheProjectToWebProjektGoogle() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the name of the project changes to “Web projekt Google”")
	public void theNameOfTheProjectChangesToWebProjektGoogle() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("two workers with the names “daha” and “jodl” exists")
	public void twoWorkersWithTheNamesDahaAndJodlExists() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Given("a project with the number  {int} exists")
	public void aProjectWithTheNumberExists(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("“daha” tries to assign “jodl” as project leader")
	public void dahaTriesToAssignJodlAsProjectLeader() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("“jodl” becomes the project leader")
	public void jodlBecomesTheProjectLeader() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}


	@Given("there is a worker with initials {string}")
	public void thereIsAWorkerWithInitials(String initials) {
		//Jonas
		this.worker = new Worker(initials);
	}

	@When("the worker is added to systems worker list")
	public void theWorkerIsAddedToSystemsWorkerList() {
		// Jonas
		app.addNewWorker(worker);

	}

	@Then("the worker exist in systems worker List")
	public void theWorkerExistInSystemsWorkerList() {
		assertTrue(app.isWorkerInWorkerList(worker));
	}
}
