package goheavy.vehicles.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import general.PageObject;
import general.Setup;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public class VehicleInfoPage extends TabsPage {
    PageObject po;
    private String VINImageUploadItemXpath;
    private String VINInputID;
    private String VehicleMakeID;
    private String VINInputXpath;
    private String makeXpath;
    private String payloadXpath;
    private String vehicleYearMakeXpath;
    private String vehicleCapacitySubSectionXpath;
    private String VehicleTypeID;
    private String stepBaseXpath;
    private String addVehicleTitleXpath;
    private String carIconXpath;
    private String longXpath;
    private String widthXpath;
    private String heightXpath;
    private String formScroll = "//*[@id='step-one-form']/ancestor::div["
            + "@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']";
    private String vehiclePayloadID;

    private String VehicleTypeXpath;

    String path = "/ancestor::div[contains(@class,'ant-form-item')]/descendant::div[@role='alert']";
    By VINInputErrorSMSLocator = By.xpath("//input[@id='vin']"+path);
    By vehicleMakeErrorSMSLocator = By.xpath("//input[@id='make']"+path);
    By vehicleYearErrorSMSLocator = By.xpath("//input[@placeholder='Enter Vehicle year']"+path);
    By LDimensionErrorSMSLocator = By.xpath("//input[@id='long']"+path+"[1]");
    By WDimensionErrorSMSLocator = By.xpath("//input[@id='width']"+path+"[2]");
    By HDimensionErrorSMSLocator = By.xpath("//input[@id='height']"+path+"[3]");
    By payloadErrorSMSLocator = By.xpath("//input[@id='payload']"+path);

    By vehicleInfoCheck = By.xpath("//span[@role='img' and @aria-label='check']/" +
            "ancestor::div[@class='ant-steps-item-container']/descendant::div[@class='ant-steps-item-title']");

    VehicleFeaturesPage vehicleFeaturesPage;

    public VehicleInfoPage() {
        super();
        po = new PageObject();
        vehicleFeaturesPage = new VehicleFeaturesPage();
        setVINImageUploadItemXpath();
        setVINInputID();
        setVehicleMakeID();
        setVehicleTypeID();
        setStepBaseXpath();
        setCarIconXpath();
        setVehicleYearMakeXpath();
        setVehicleCapacitySubSectionXpath();
        setVehiclePayloadID();
        setVINInputXpath();
        setAddVehicleTitleXpath();
        setMakeXpath("//input[@id='make']");
        setLongXpath("//input[@id='long']");
        setWidthXpath("//input[@id='width']");
        setHeightXpath("//input[@id='height']");
        setVehicleTypeXpath("//input[@id='vehicleTypeId']");
        setPayloadXpath("//input[@id='payload']");
    }

    public WebElement getVehicleInfoCheck() {
        return getWebElement(vehicleInfoCheck);
    }

    public By getPayloadErrorSMSLocator() {
        return payloadErrorSMSLocator;
    }

    public void setPayloadErrorSMSLocator(By payloadErrorSMSLocator) {
        this.payloadErrorSMSLocator = payloadErrorSMSLocator;
    }

    public String getVehicleTypeXpath() {
        return VehicleTypeXpath;
    }

    public void setVehicleTypeXpath(String vehicleTypeXpath) {
        VehicleTypeXpath = vehicleTypeXpath;
    }

    public String getPayloadXpath() {
        return payloadXpath;
    }

    public void setPayloadXpath(String payloadXpath) {
        this.payloadXpath = payloadXpath;
    }

    public String getMakeXpath() {
        return makeXpath;
    }

    public void setMakeXpath(String makeXpath) {
        this.makeXpath = makeXpath;
    }

    public String getLongXpath() {
        return longXpath;
    }

    public void setLongXpath(String longXpath) {
        this.longXpath = longXpath;
    }

    public String getWidthXpath() {
        return widthXpath;
    }

    public void setWidthXpath(String widthXpath) {
        this.widthXpath = widthXpath;
    }

    public String getHeightXpath() {
        return heightXpath;
    }

    public void setHeightXpath(String heightXpath) {
        this.heightXpath = heightXpath;
    }

    private String getVehiclePayloadID() {
        return vehiclePayloadID;
    }

    private void setVehiclePayloadID() {
        this.vehiclePayloadID = "payload";
    }

    //
    private String getFormScroll() {
        return this.formScroll;
    }

    private void setFormScroll(String formScroll) {
        this.formScroll = formScroll;
    }


    private String getVehicleCapacitySubSectionXpath() {
        return this.vehicleCapacitySubSectionXpath;
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

    private String getVehicleMakeID() {
        return VehicleMakeID;
    }

    private void setVehicleMakeID() {
        this.VehicleMakeID = "make";
    }


    private String getVINInputID() {
        return this.VINInputID;
    }


    private void setVINInputID() {
        this.VINInputID = "vin";
    }

    private String getVINImageUploadItemXpath() {
        return this.VINImageUploadItemXpath;
    }

    private void setVINImageUploadItemXpath() {
        this.VINImageUploadItemXpath = "//label[@class='ant-form-item-required' and @title='VIN Image']/ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']";
    }

    private String getCarIconXpath() {
        return carIconXpath;
    }

    private void setCarIconXpath() {
        this.carIconXpath = "//span[text()='Add Vehicle']/span[@role='img' and @class='anticon anticon-car']";
    }


    private String getVINInputXpath() {
        return this.VINInputXpath;
    }

    private void setVINInputXpath() {
        this.VINInputXpath = "//input[@id='vin']";
    }

    private String getAddVehicleTitleXpath() {
        return this.addVehicleTitleXpath;
    }

    private void checkVehicleTypeComponentBehaviour() {
        waitForSpinningElementDisappear();
        try {
            Setup.getWait().thread(150);
            clickOn(getWebElement(By.id(getVehicleTypeID())));
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
        } catch(Exception ignored) {}
    }
    private void setAddVehicleTitleXpath() {
        this.addVehicleTitleXpath = "//span[@role='img' and @aria-label='car' and @class='anticon anticon-car']/ancestor::div[contains(@class, 'ant-col')]/descendant::span[text()='Add Vehicle']";
    }

    private String getVehicleTypeID() {
        return this.VehicleTypeID;
    }


    private void setVehicleTypeID() {
        this.VehicleTypeID = "vehicleTypeId";
    }


    private String getStepBaseXpath() {
        return this.stepBaseXpath;
    }

    private void setStepBaseXpath() {
        this.stepBaseXpath = "//div[@class='ant-steps-item-container']";
    }

    public void insertValidData() {

        sendDataToInput(getWebElement(By.id(getVINInputID())), getFaker().number().digits(17), null, getFormScroll());

        checkVehicleTypeComponentBehaviour();
        String vehicleMake = getFaker().superhero().name();
        Setup.setKeyValueStore("vehicleMake", vehicleMake);

        sendDataToInput(getWebElement(By.id(getVehicleMakeID())), (String) Setup.getValueStore("vehicleMake"), null,
                getFormScroll());
        //checkVehicleTypeComponentBehaviour();
        int min_val = 1995;
        int max_val = 2018;

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min_val, max_val + 1);

        sendDataToInput(getWebElement(By.xpath(getVehicleYearMakeXpath())), String.valueOf(randomNum), null, getFormScroll());
        sendDataToInput(getWebElement(By.xpath(getVehicleYearMakeXpath())), null, Keys.RETURN, getFormScroll());

        fillDimensions();
        min_val = 90;
        max_val = 210;
        randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.id(getVehiclePayloadID())), String.valueOf(randomNum), null, getFormScroll());
        scrollToWebElement(null, getFormScroll());

        clickOn(getWebElement(By.xpath("//button[@type='submit']/descendant::span[text()='Next']")));
        waitForSpinningElementDisappear();
        Setup.getWait().thread(1500);

    }

    private void fillDimensions() {
        int min_val = 22;
        int max_val = 45;

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.xpath("//input[@id='long']")), String.valueOf(randomNum), null, getFormScroll());
        randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.xpath("//input[@id='width']")), String.valueOf(randomNum), null, getFormScroll());
        randomNum = tlr.nextInt(min_val, max_val + 1);
        sendDataToInput(getWebElement(By.xpath("//input[@id='height']")), String.valueOf(randomNum), null, getFormScroll());
    }

    public boolean systemOpensAddVehicleView() {
        Setup.getWait().thread(1500);
        waitForSpinningElementDisappear();

        try {
            Assert.assertNotNull("Page Title not found", getPageElementBy(By.xpath(getAddVehicleTitleXpath())));
            Assert.assertNotNull("Page Icon not Found", getPageElementBy(By.xpath(getCarIconXpath())));
            Assert.assertTrue("Step 1 not found", checkStep(getWebElement(By.xpath(getStepBaseXpath())), "1", "Vehicle Info"));
            Assert.assertNotNull("VIN Image Upload not found", getPageElementBy(By.xpath(getVINImageUploadItemXpath())));
            Assert.assertNotNull("VIN Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVINInputXpath())));
            Assert.assertNotNull("Vehicle Type Input not found or do not match Expected Criteria",
                    getPageElementBy(By.id(getVehicleTypeID())));
            checkVehicleTypeComponentBehaviour();
            Assert.assertNotNull("Vehicle Make Input not found or do not match Expected Criteria",
                    getPageElementBy(By.xpath(getVehicleMakeID())));
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


    public WebElement getPageElementBy(By by) {
        try {
            return getWebElement(By.xpath(getAddVehicleTitleXpath()));
        } catch (Exception e) {
            Assert.fail("Expected Title Page element not found");
            return null;
        }
    }

}
