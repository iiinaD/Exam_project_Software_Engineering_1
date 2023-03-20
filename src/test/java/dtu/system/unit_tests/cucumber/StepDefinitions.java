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

	@Given("a worker with the name {string} is logged in")
	public void a_worker_with_the_name_is_logged_in(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Given("a project with the number  {int} and  name {string} exists")
	public void a_project_with_the_number_and_name_exists(Integer int1, String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("the worker tries to change the name of the project to {string}")
	public void the_worker_tries_to_change_the_name_of_the_project_to(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the name of the project changes to {string}")
	public void the_name_of_the_project_changes_to(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Given("the worker is logged in")
	public void theWorkerIsLoggedIn() {
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
	@Given("the worker is not logged in")
	public void theWorkerIsNotLoggedIn() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the new project does not get created")
	public void theNewProjectDoesNotGetCreated() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("two workers with the names {string} and {string} exists")
	public void two_workers_with_the_names_and_exists(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("{string} tries to assign {string} as project leader")
	public void tries_to_assign_as_project_leader(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("{string} becomes the project leader")
	public void becomes_the_project_leader(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
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
	public void theWorkerIsAddedToSystemsWorkerList() {
		// Jonas
		app.addNewWorker(worker);
	}
	@Then("the worker is in the systems worker list")
	public void isInTheSystemsWorkerList() {
		// Jonas

		assertTrue(app.isWorkerInWorkerList(worker));
	}

}
