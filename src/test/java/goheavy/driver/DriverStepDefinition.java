package goheavy.driver;

import io.cucumber.java.en.*;
import general.*;
import org.junit.Assert;

@SuppressWarnings("unused")
public class DriverStepDefinition {
	private final DriverStep driverSteps;

	public DriverStepDefinition() {
		GeneralSteps generalSteps = new GeneralSteps();
		driverSteps = new DriverStep();
	}

	@Given("the user is on the \"Driver List\" view")
	public void the_user_is_on_the_driver_list_view() {
		try {
			driverSteps.goToView();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Given("clicks on the \"Add Driver\" button")
	public void clicks_on_the_add_driver_button() {
		try {
			driverSteps.clickOnAddDriver();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@When("insert valid data")
	public void insert_valid_data() {
			driverSteps.addDriver();
	}

	@When("clicks on the \"Add\" button")
	public void clicks_on_button() {
		try {
			driverSteps.clickOnAddButton();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Then("the system will add the new driver into the Driver List")
	public void the_system_will_add_the_new_driver_into_the_driver_list() {
		try {
			driverSteps.checkNewDriver();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Then("will redirect to the previous view")
	public void will_redirect_to_the_previous_view() {
		try {
			driverSteps.checkDriverListView();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
