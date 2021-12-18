package general;

import goheavy.driver.DriverStep;
import goheavy.vehicles.VehicleStep;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GeneralSteps extends PageObject {
    private String LogOutStep1Xpath;
    private String LogOutStep2Xpath;
    private String menuXpath;
    private String SettingsXpath;
    private String AccountSettingsXpath;
    private By element;
    private final VehicleStep vehicleStep;
    private final DriverStep driverStep;

    public GeneralSteps() {
        super();
        setLogOutStep1Xpath();
        setLogOutStep2Xpath();
        setMenuXpath();
        setSettingsXpath();
        setAccountSettingsXpath();
        vehicleStep = new VehicleStep();
        driverStep = new DriverStep();
    }

    private By getElement() {
        return element;
    }

    private void setElement(By element) {
        this.element = element;
    }

    private String getAccountSettingsXpath() {
        return AccountSettingsXpath;
    }

    private void setAccountSettingsXpath() {
        AccountSettingsXpath = "//span[text()='Account Settings']";
    }

    private String getSettingsXpath() {
        return SettingsXpath;
    }

    private void setSettingsXpath() {
        SettingsXpath = "//span[text()='Settings']";
    }

    private String getMenuXpath() {
        return menuXpath;
    }

    private void setMenuXpath() {
        this.menuXpath = "//ul[@class='ant-menu ant-menu-root ant-menu-inline ant-menu-light' and @role='menu']";
    }

    String getLogOutStep1Xpath() {
        return LogOutStep1Xpath;
    }

    void setLogOutStep1Xpath() {
        LogOutStep1Xpath = "//span[@class='ant-avatar ant-avatar-circle ant-avatar-image']";
    }

    String getLogOutStep2Xpath() {
        return LogOutStep2Xpath;
    }

    private void setLogOutStep2Xpath() {
        LogOutStep2Xpath = "//span[@class='ant-dropdown-menu-title-content' and text()='Logout']";
    }

    private void waitForSpinnerToDisappear() {
        waitForSpinningElementDisappear();
    }

    public void logoutProcess() {
        waitForSpinnerToDisappear();
        Setup.getActions().moveToElement(getWebElement(By.xpath(getLogOutStep1Xpath()))).click().perform();
        Setup.getActions().moveToElement(getWebElement(By.xpath(getLogOutStep2Xpath()))).click().perform();
    }

    @SuppressWarnings("unused")
    public void goToAccountSettingsView() {
        waitForSpinnerToDisappear();
        setElement(By.xpath(getMenuXpath() + getSettingsXpath()));
        getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
        WebElement settings = getWebElement(getElement());
        Setup.getActions().moveToElement(settings).click().perform();
        Setup.getWait().thread(200);
        setElement(By.xpath(getAccountSettingsXpath()));
        getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
        WebElement accountSettings = getWebElement(getElement());
        Setup.getActions().moveToElement(accountSettings).click().perform();
        waitForSpinnerToDisappear();
    }

    @Given("The user is in {string} view.")
    public void the_user_is_in_account_settings_view(String view) {
        try {
            if (view.equals("Vehicles & Insurance List")){
                vehicleStep.goToAccountSettingsView();
                vehicleStep.checkPage();
            } else if(view.equals("Drivers List")) {
                driverStep.goToView();
                driverStep.checkPage();
            }
        } catch (Exception e) {
            vehicleStep.goToAccountSettingsView();
            vehicleStep.checkPage();
        }
    }
}
