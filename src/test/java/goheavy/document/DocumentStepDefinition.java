package goheavy.document;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DocumentStepDefinition {
    private final DocumentStep documentStep;

    public DocumentStepDefinition() {
        super();
        documentStep = new DocumentStep();
    }


    @When("Clicks on the \"Documents\" icon that belong to a driver.")
    public void clicks_on_the_documents_icon_that_belong_to_a_driver(){
        try {
            documentStep.clicksOnDocumentIcon();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The system displays the \"Documents for DriverName\" view.")
    public void the_system_displays_the_documents_for_drivers_name_view(){
        try {
            documentStep.checkPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
