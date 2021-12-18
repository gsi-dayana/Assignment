package goheavy.vehicles.page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import general.PageObject;
import general.Setup;

@SuppressWarnings("unused")
public class VehiclePage extends PageObject {
    private String vehiclesLiXpath;
    private String addVehicleButtonXpath;
    private String addVehicleTitleXpath;
    private String carIconXpath;
    private String stepBaseXpath;
    private String VINImageUploadItemXpath;
    private String VINInputXpath;
    private String vehicleTypeXpath;
    private String vehicleMakeXpath;
    private String vehicleYearMakeXpath;
    private String vehicleCapacitySubSectionXpath;
    private final String vehicleInsuranceImageXpath = "//label[@class='ant-form-item-required' and "
            + "@title='Current Insurance Certificate Picture']/ancestor::div[@class='ant-row ant-form-item']/descendant::input"
            + "[@type='file']";
    By check = By.xpath("//span[@role='img' and @aria-label='check']/" +
            "ancestor::div[@class='ant-steps-item-container']/descendant::div[@class='ant-steps-item-title']");

    public VehiclePage() {
        super();
        setVehiclesLiXpath();
        setAddVehicleButtonXpath();
        setAddVehicleTitleXpath();
        setCarIconXpath();
        setStepBaseXpath();
        setVINImageUploadItemXpath();
        setVINInputXpath();
        setVehicleTypeXpath();
        setVehicleMakeXpath();
        setVehicleYearMakeXpath();
        setVehicleCapacitySubSectionXpath();
        this.urlPath = "/vehicleinsurance";
    }

    public WebElement getCheckText() {
        return getWebElement(check);
    }

    private String getVehicleCapacitySubSectionXpath() {
        return vehicleCapacitySubSectionXpath;
    }

    private void setVehicleCapacitySubSectionXpath() {
        this.vehicleCapacitySubSectionXpath = "//span[@class='ant-divider-inner-text' and text()='Vehicle Capacity']";
    }

    private String getVehicleYearMakeXpath() {
        return vehicleYearMakeXpath;
    }

    private void setVehicleYearMakeXpath() {
        this.vehicleYearMakeXpath = "//input[@placeholder='Enter Vehicle year']/ancestor::div[@class='ant-picker-input']";
    }

    private String getVehicleMakeXpath() {
        return vehicleMakeXpath;
    }

    private void setVehicleMakeXpath() {
        this.vehicleMakeXpath = "//input[@placeholder='Enter Vehicle make' and @id='make']";
    }

    private String getVehicleTypeXpath() {
        return vehicleTypeXpath;
    }

    private void setVehicleTypeXpath() {
        this.vehicleTypeXpath = "//input[@id='vehicleTypeId' and @role='combobox']";
    }

    private String getVINInputXpath() {
        return VINInputXpath;
    }

    private void setVINInputXpath() {
        VINInputXpath = "//label[@title='Vehicle ID No. (VIN)' and text()='Vehicle ID No. (VIN)']/ancestor::div[contains(@class,'ant-form-item')]/descendant::input[@placeholder='XXXXXXXXXXXXXXXXX' and @maxlength='17']";
    }

    private String getVINImageUploadItemXpath() {
        return VINImageUploadItemXpath;
    }

    private void setVINImageUploadItemXpath() {
        VINImageUploadItemXpath = "//label[@class='ant-form-item-required' and @title='VIN Image']/ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']";
    }

    private String getStepBaseXpath() {
        return stepBaseXpath;
    }

    private void setStepBaseXpath() {
        this.stepBaseXpath = "//div[@class='ant-steps-item-container']";
    }

    private String getCarIconXpath() {
        return carIconXpath;
    }

    private void setCarIconXpath() {
        this.carIconXpath = "//span[text()='Add Vehicle']/span[@role='img' and @class='anticon anticon-car']";
    }

    private String getAddVehicleTitleXpath() {
        return addVehicleTitleXpath;
    }

    private void setAddVehicleTitleXpath() {
        this.addVehicleTitleXpath = "//span[@role='img' and @aria-label='car' and @class='anticon anticon-car']/ancestor::div[contains(@class, 'ant-col')]/descendant::span[text()='Add Vehicle']";
    }

    private String getAddVehicleButtonXpath() {
        return addVehicleButtonXpath;
    }

    private void setAddVehicleButtonXpath() {
        this.addVehicleButtonXpath = "//span[text()='Add Vehicle']/ancestor::button[@type='button' and @class='ant-btn ant-btn-primary']";
    }

    private String getVehiclesLiXpath() {
        return vehiclesLiXpath;
    }

    private void setVehiclesLiXpath() {
        this.vehiclesLiXpath = "//span[text()='Vehicles']/ancestor::li[contains(@class, 'ant-menu-item')]";
    }

    public boolean goToVehiclesAndInsuranceListView() {
        try {
            waitForSpinningElementDisappear();
            Setup.getWait().thread(1500);
            WebElement element = getWebElement(By.xpath(getVehiclesLiXpath()));
            clickOn(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickOnAddVehicleButton() {
        try {
            waitForSpinningElementDisappear();
            Setup.getWait().thread(3000);
            WebElement element = getWebElement(By.xpath(getAddVehicleButtonXpath()));
            clickOn(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean systemOpensAddVehicleView() {
        Setup.getWait().thread(5000);
        waitForSpinningElementDisappear();

        try {
            Assert.assertNotNull("Page Title not found", getPageElementBy(By.xpath(getAddVehicleTitleXpath())));
            Assert.assertNotNull("Page Icon not Found", getPageElementBy(By.xpath(getCarIconXpath())));
            Assert.assertTrue("Step 1 not found", checkStep(getWebElement(By.xpath(getStepBaseXpath())), "1", "Vehicle Info"));
            Assert.assertNotNull("VIN Image Upload not found", getPageElementBy(By.xpath(getVINImageUploadItemXpath())));
            Assert.assertNotNull("VIN Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVINInputXpath())));
            Assert.assertNotNull("Vehicle Type Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVehicleTypeXpath())));
            checkVehicleTypeComponentBehaviour();
            Assert.assertNotNull("Vehicle Make Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVehicleMakeXpath())));
            Assert.assertNotNull("Vehicle Year Make Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVehicleYearMakeXpath())));
            Assert.assertNotNull("Vehicle Sub Section not found",
                    getPageElementBy(By.xpath(getVehicleCapacitySubSectionXpath())));
            //TODO: Work in progress check for every element on the page to be Expected
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void checkVehicleTypeComponentBehaviour() {
        waitForSpinningElementDisappear();
        try {
            Setup.getWait().thread(150);
            clickOn(getWebElement(By.xpath(getVehicleTypeXpath())));
            Setup.getWait().thread(150);
            Assert.assertNotNull("Vehicle types not found or none to show", getPageElementBy(By.xpath(
                    "//div[@class='rc-virtual-list-holder-inner']")));
            WebElement vehicle_type_list = getPageElementBy(By.xpath("//div[@class='rc-virtual-list-holder-inner']"));
            List<WebElement> element_list = vehicle_type_list.findElements(By.xpath(
                    "//div[@class='ant-select-item ant-select-item-option']"));
            int val = element_list.size();
            if (element_list.size() > 3)
                val = 0;

            int number = (int) (Math.random() * val + 1);
            hoverElement(null, element_list.get(number));
            clickOn(element_list.get(number));
            Setup.getWait().thread(150);
        } catch (Exception ignored) { }
    }

    public boolean checkStep(WebElement element, String step, String stepName) {
        waitForSpinningElementDisappear();
        String numberXpath = "//span[@class='ant-steps-icon' and text()='" + step + "']";
        String titleXpath = "//div[@class='ant-steps-item-title' and text()='" + stepName + "']";

        try {
            Assert.assertNotNull("Error on Step Number Retrieve", element.findElement(
                    By.xpath(numberXpath)));
            Assert.assertNotNull("Error on Step Title Retrieve", element.findElement(
                    By.xpath(titleXpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getPageElementBy(By by) {
        try {
            return getWebElement(By.xpath(getAddVehicleTitleXpath()));
        } catch (Exception e) {
            Assert.fail("Expected Title Page element not found");
            return null;
        }
    }

    public boolean hoverElement(By by, WebElement element) {
        try {
            Setup.getWait().thread(500);
            if (element != null)
                Setup.getActions().moveToElement(element).build().perform();
            else
                Setup.getActions().moveToElement(getWebElement(by)).build().perform();
            Setup.getWait().thread(500);
            return true;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    public void sendDataToInput(WebElement element, String data, Keys key, String form) {
        try {
            if (element.getAttribute("value").length() > 0)
                clear_element_text(element);
        } catch (Exception ignored) { }
        scrollToWebElement(element, form);
        if (data != null)
            Setup.getActions().sendKeys(element, data).build().perform();
        else
            Setup.getActions().sendKeys(element, key).build().perform();
    }

    public WebElement systemCreatesVehicleOnStatus(String status) {
        try {
            waitForSpinningElementDisappear();
            Setup.getActions().sendKeys(getWebElement(By.xpath(""
                            + "//input[@placeholder='Search...' and @type='text' and @class='ant-input']")),
                    (String) Setup.getValueStore("vehicleMake")).build().perform();

            Setup.getWait().thread(250);

            Setup.getActions().click(getWebElement(By.xpath(""
                    + "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']"))).build().perform();

            Setup.getWait().thread(250);

            String status_xpath = "//span[@class='ant-tag ant-tag-blue' and text()='" + status + "']";
            return getWebElement(By.xpath(status_xpath));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    public WebElement systemsRegistersCreationDate() {
        waitForSpinningElementDisappear();
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date_compare = dtf.format(LocalDateTime.now());
            String date_xpath = "//td[@class='ant-table-cell' and contains(text(), '" + date_compare + "')]";
            return getWebElement(By.xpath(date_xpath));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }


    public boolean systemDisplaysAssignView() {
        Setup.getWait().thread(3000);
        waitForSpinningElementDisappear();
        try {
            String xpath = "//div[text()='Assign Vehicle' and @class='ant-modal-title']";
            WebElement alert = getWebElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    public boolean userAssignsTheVehicle() {
        Setup.getWait().thread(3000);
        waitForSpinningElementDisappear();
        try {
            Setup.getActions().moveToElement(getWebElement(By.xpath("//input[@placeholder='Please, select a option']"))).
                    build().perform();
            Setup.getWait().thread(500);
            Setup.getActions().click(getWebElement(By.xpath("//input[@placeholder='Please, select a option']"))).
                    build().perform();
            Setup.getWait().thread(1000);
            Setup.getActions().moveToElement(getWebElement(By.xpath("//div[@class='ant-select-item-option-content']"))).
                    build().perform();
            Setup.getWait().thread(500);
            Setup.getActions().click(getWebElement(By.xpath("//div[@class='ant-select-item-option-content']"))).
                    build().perform();
            Setup.getWait().thread(500);
            Setup.getActions().click(getWebElement(By.xpath("//button[@class='ant-btn ant-btn-primary' and @type='button']/"
                    + "descendant::span[text()='Assign']"))).build().perform();
            Setup.getWait().thread(1000);
            System.exit(0);
            return true;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    public void clicks_button_done() {
        clickOn(getWebElement(By.xpath("//button[@type='submit']")));

        waitForSpinningElementDisappear();

    }

    public void systemDisplaysMessage(String message) {
        try {
            waitForSpinningElementDisappear();
            String xpath = "//div[@class='ant-notification ant-notification-topRight']";
            WebElement alert = getWebElement(By.xpath(xpath));
            Assert.assertEquals(alert.getText(), message);
        } catch (Exception e) {
            Assert.fail(e.getMessage());

        }
    }

    public boolean hoverOverImageComponent() {
        waitForSpinningElementDisappear();
        try {
            setImage(getWebElement(By.xpath(getVINImageUploadItemXpath())));
            Setup.getWait().thread(500);
            Setup.getActions().moveToElement(getWebElement(By.xpath("//div[contains(@class, 'kxeirt')]/descendant::img")))
                    .build().perform();
            Setup.getWait().thread(500);

            Assert.assertTrue(hoverElement(By.xpath("//span[@role='img' and @class='anticon anticon-eye' and @cursor='pointer']")
                    , null));
            clickOn(getWebElement(By.xpath("//span[@role='img' and @class='anticon anticon-eye' and @cursor='pointer']")));
            clickOn(getWebElement(By.xpath("//span[@class='anticon anticon-close ant-modal-close-icon' and @role='img']")));
            Assert.assertTrue(hoverElement(By.xpath("//span[@role='img' and @class='anticon anticon-check' and @cursor='pointer']")
                    , null));
            clickOn(getWebElement(By.xpath("//span[@role='img' and @class='anticon anticon-check' and @cursor='pointer']")));
            Assert.assertTrue(hoverElement(By.xpath("//span[@role='img' and @class='anticon anticon-close' and @cursor='pointer']")
                    , null));

            return true;
        } catch (Exception e) {
            Assert.fail("Expected Image Over element not found");
            return false;
        }
    }
}
