package goheavy.vehicles;

import io.cucumber.java.en.*;
import general.GeneralSteps;

@SuppressWarnings("unused")
public class VehicleStepDefinition {
    private final VehicleStep vehicleStep;

    public VehicleStepDefinition() {
        vehicleStep = new VehicleStep();
        GeneralSteps generalSteps = new GeneralSteps();
    }

    @When("User clicks on \"Add Vehicle\" button.")
    public void the_user_clicks_on_add_vehicle_button() {
        try {
            vehicleStep.userClicksOnAddVehicleButton();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Given("The system opens the \"Add Vehicle\" view.")
    public void the_system_opens_the_add_vehicle_view() {
        try {
            vehicleStep.theSystemOpensTheAddVehicleView();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("User hover overs a Vehicle document image component with an image loaded")
    public void hover_over_image_component_with_image() {
        try {
            vehicleStep.hoverOverImageComponent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("The user inserts valid data")
    public void the_user_inserts_valid_data_and_clicks_done_button() {
        try {
            vehicleStep.userInsertsValidDataAndClicksDone();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("User clicks on the \"Done\" button.")
    public void clicks_button() {
        vehicleStep.clicks_button_done();
    }

    //Improve this to meet the other NF requirement
    @Then("The System Creates a new Vehicle in {string} status.")
    public void the_system_creates_vehicle_and_registers_date(String status) {
        try {
            vehicleStep.systemCreatesVehicleOnStatus(status);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @And("The System registers the creation date.")
    public void the_system_registers_the_creation_date() {
        try {
            vehicleStep.systemRegistersCreationDate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Then("System returns to the \"Vehicles & Insurance List\" view")
    public void returns_to_view() {
        vehicleStep.returnToMainView();
    }

    @Then("System displays message {string}")
    public void system_displays_message(String message) {
        vehicleStep.systemDisplaysMessage(message);
    }

    @Then("The system displays \"Assign Vehicle\" view.")
    public void system_displays_assign_view() {
        try {
            vehicleStep.systemDisplaysAssignView();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("User assigns the vehicle.")
    public void user_assigns_the_vehicle() {
        try {
            vehicleStep.userAssignsTheVehicle();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
