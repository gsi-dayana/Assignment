package goheavy.vehicles.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import general.Setup;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public class DrivingRequirementsPage extends TabsPage {

    private String stepThreeForm = "//*[@id='step-three-form']/" +
            "ancestor::div[@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']";
    By getVehicleLicensePlateLocator = By.id("licensePlateStateIssuedId");

    By insurancePolicyLocator = By.id("insurancePolicyNo");
    By insuranceCertificateLocator = By.id("insuranceCertificateCompany");
    By insuranceRenewalLocator = By.id("insuranceRenewal");
    By vehicleLicensePlateLocator = By.id("licensePlateNo");
    String path = "/ancestor::div[contains(@class,'ant-form-item')]/descendant::div[@role='alert']";
    By insurancePolicyErrorSMSLocator = By.xpath("//input[@id='insurancePolicyNo']"+path);
    By insuranceCertificateErrorSMSLocator = By.xpath("//input[@id='insuranceCertificateCompany']"+path);
    By insuranceRenewalErrorSMSLocator = By.xpath("//input[@id='insuranceRenewal']"+path);
    By vehicleLicensePlateErrorSMSLocator = By.xpath("//input[@id='licensePlateNo']"+path);

    public DrivingRequirementsPage() {
        super();


    }

    private String getVehicleInsuranceImageXpath() {
        return "//label[@title='Current Insurance Certificate Picture']/ancestor::" +
                    "div[@class='ant-row ant-form-item']/descendant::input[@type='file']";
    }

    public String getStepThreeForm() {
        return stepThreeForm;
    }

    public void setStepThreeForm(String stepThreeForm) {
        this.stepThreeForm = stepThreeForm;
    }

    public void insertValidData() {
        setImage(getWebElement(By.xpath(getVehicleInsuranceImageXpath())));

        clickOn(getWebElement(By.id("verificationDelivery")));
        clickOn(getWebElement(By.id("verificationLicenseTime")));

        sendDataToInput(getWebElement(By.id("insurancePolicyNo")),
                getFaker().number().digits(12), null, getStepThreeForm());

        Setup.getWait().thread(150);

        sendDataToInput(getWebElement(By.id("insuranceCertificateCompany")),
                getFaker().name().firstName(), null, getStepThreeForm());

        Setup.getWait().thread(150);

        introduceDate();

        Setup.getWait().thread(150);

        sendDataToInput(getWebElement(By.id("insuranceRenewal")),
                getFaker().name().firstName(), null, getStepThreeForm());

        Setup.getWait().thread(150);

        sendDataToInput(getWebElement(By.id("licensePlateNo")),
                getFaker().number().digits(6), null, getStepThreeForm());

        Setup.getWait().thread(150);

        managePlateState();

        scrollToWebElement(null, getStepThreeForm());

        Setup.getWait().thread(150);

        setImage(getWebElement(By.xpath("//label[@title='License Plate Photo']/ancestor::div[contains(@class, "
                + "'ant-form-item')]/descendant::input[@type='file']")));

        Setup.getWait().thread(150);

        setImage(getWebElement(By.xpath("//label[@title='Vehicle Registration Sticker']/ancestor::div[contains(@class, "
                + "'ant-form-item')]/descendant::input[@type='file']")));

        Setup.getWait().thread(150);
    }

    public void introduceDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -2);
        Date past_date = calendar.getTime();
        DateFormat short_date = DateFormat.getDateInstance(DateFormat.SHORT);

        //From Date
        String by = "insuranceEffectiveDate";
        Setup.getActions().moveToElement(getWebElement(By.id(by))).build().perform();
        Setup.getActions().click(getWebElement(By.id(by))).build().perform();
        Setup.getActions().sendKeys(getWebElement(By.id(by)), short_date.format(getFaker().date().between(past_date, date)))
                .build().perform();
        //Date here
        int min_val = 1;
        int max_val = 10;
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min_val, max_val + 1);

        manageDate(true, randomNum);

        //To Date
        calendar.add(Calendar.YEAR, 2);
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date future_date = calendar.getTime();

        try {
            by = "insuranceExpirationDate";
            Setup.getActions().moveToElement(getWebElement(By.id(by))).build().perform();
            Setup.getActions().click(getWebElement(By.id(by))).build().perform();
            Setup.getActions().sendKeys(getWebElement(By.id(by)), short_date.format(future_date))
                    .build().perform();
            manageDate(false, randomNum);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void manageDate(boolean back, int random_num) {
        String xpath = "//button[@class='ant-picker-header-prev-btn']";
        Setup.getWait().thread(500);
        if (!back) {
            //TODO: Get this improved
            xpath = "/html/body/div[3]/div/div/div/div/div[1]/div[1]/button[3]";
            Setup.getActions().moveToElement(getWebElement(By.xpath(xpath))).build().perform();
            Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();
            xpath = "/html/body/div[3]/div/div/div/div/div[1]/div[2]/table/tbody/tr[5]/td[6]/div";
            Setup.getActions().moveToElement(getWebElement(By.xpath(xpath))).build().perform();
            Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();
        } else {
            try {
                for (int i = 0; i < random_num; i++) {
                    Setup.getActions().click(getWebElement(By.xpath(xpath)))
                            .build().perform();
                }
                String xpath_day = "//div[@class='ant-picker-cell-inner' and text()='" + random_num + "']";
                Setup.getActions().click(getWebElement(By.xpath(xpath_day))).build().perform();
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        }
    }

    private void managePlateState() {
        String xpath = "//input[@id='licensePlateStateIssuedId']/ancestor::div[@class='ant-form-item-control-input']";

        sendDataToInput(getWebElement(By.xpath(xpath)),
                null, Keys.SPACE, getStepThreeForm());

        List<WebElement> states = getWebElements(By.xpath("//div[@class='ant-select-item ant-select-item-option']"));

        int val = states.size();

        try {
            int number = (int) (Math.random() * val + 1);
            if (number == 0)
                number = 2;

            clickOn(states.get(number - 1));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void checkVehicleLicensePlateComponentBehaviour() {
        waitForSpinningElementDisappear();
        try {
            Setup.getWait().thread(150);
            clickOn(getWebElement(getVehicleLicensePlateLocator));
            Setup.getWait().thread(150);
            Assert.assertNotNull("Vehicle License Plate not found or none to show", getPageElementBy(By.xpath(
                    "//div[@class='rc-virtual-list-holder-inner']")));
            WebElement vehicle_plate_list = getPageElementBy(By.xpath("//div[@class='rc-virtual-list-holder-inner']"));
            List<WebElement> element_list = vehicle_plate_list.findElements(By.xpath(
                    "//div[@class='ant-select-item ant-select-item-option']"));
            int val = element_list.size();
            if (element_list.size() > 3)
                val = 0;
            int number = (int) (Math.random() * val + 1);
            hoverElement(null, element_list.get(number));
            clickOn(element_list.get(number));
        } catch(Exception ignored) { }
    }

    public boolean systemOpensAddVehicleView() {
        return true;
    }
}
