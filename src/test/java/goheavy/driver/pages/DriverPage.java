package goheavy.driver.pages;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import general.PageObject;
import general.Setup;

public class DriverPage extends PageObject {
	private final By addDriverButtonLocator = By.xpath("//span[text()='Add Driver']/ancestor::button[@class='ant-btn ant-btn-primary']");
	private final HashMap<String, WebElement> formElements;
	By driverPhotoLocator = By.xpath("//label[contains(text(),'shoulders')]/" +
			"ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']");
	By driverLicenseFrontLocator = By.xpath("//label[contains(@title,'(Front)')]/"
			+ "ancestor::div[contains(@class,'ant-form-item')]/descendant::input[@type='file']");
	By driverLicenseBackLocator = By.xpath("//label[contains(@title,'(Back)')]/"
			+ "ancestor::div[contains(@class,'ant-form-item')]/descendant::input[@type='file']");
	By tShirtSizeLocator = By.id("tShirtSize");
	By tShirtOptionsLocator = By.id("tShirtSize_list");
	By searchFieldLocator = By.xpath("//input[@placeholder='Search...']");
	By newDriverNameLocator = By.xpath("//td[@class='ant-table-cell'][2]");
	String form;
	String fullName;

	public DriverPage() {
		super();
		this.urlPath = "/driver";
		formElements = new HashMap<>();
		setForm("//form[@id='driver-form']");
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}


	public By getAddDriverButtonLocator() {
		return addDriverButtonLocator;
	}

	
	public void waitAdditionalTime() {
		Setup.getWait().thread(1500);
	}
	
	public void waitAdditionalShortTime() {
		Setup.getWait().thread(500);
	}

	public void clicksOnAddDriverButton()
	{
		try {
			waitForSpinningElementDisappear();
			waitAdditionalTime();
			clickOnElement(addDriverButtonLocator);
		} catch (Exception ignored) { }
	}

	public boolean insertValidData() {
		try {
			waitForSpinningElementDisappear();
			waitAdditionalTime();

			setImageImproved("Driver Photo (including shoulders)", null);

			String formId = "driver-form";

			String firstName = getFaker().name().firstName();
			Setup.setKeyValueStore("driverName", firstName);
			sendDataToInput(getElement("firstName"), Setup.getValueStore("driverName"), null, getForm());

			String lastName = getFaker().name().lastName();
			Setup.setKeyValueStore("driverLastName", lastName);
			sendDataToInput(getElement("lastName"),Setup.getValueStore("driverLastName"), null, getForm());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			sendDataToInput(getElement("birthAt"), formatter.format(getFaker().date().birthday(21, 80)), null, getForm());
			sendDataToInput(getElement("birthAt"), null, Keys.RETURN, getForm());
			waitAdditionalShortTime();
			sendDataToInput(getElement("experienceYear"), String.valueOf(getFaker().number().numberBetween(3, 6)), null, getForm());
			scrollToWebElement(getWebElement(By.id("mobilePhone")), "//div[contains(@class, 'ContentDiv')]");
			sendDataToInput(getElement("mobilePhone"), getFaker().phoneNumber().cellPhone(), null, getForm());
			waitAdditionalShortTime();
			sendDataToInput(getElement("email"), getFaker().internet().emailAddress(), null, getForm());
			waitAdditionalShortTime();
			scrollToWebElement(getWebElement(By.id("tShirtSize")), "//div[contains(@class, 'ContentDiv')]");
			interactAndRandomSelectFromDropDown("tShirtSize", "tShirtSize_list");
			scrollToWebElement(getWebElement(By.id("address")), "//div[contains(@class, 'ContentDiv')]");
			sendDataToInput(getElement("address"), getFaker().address().streetAddress() + getFaker().address().streetAddressNumber() + getFaker().address().streetName(), null, getForm());
			waitAdditionalShortTime();
			sendDataToInput(getElement("addressZipCode"), getFaker().address().zipCode(), null, getForm());
			waitAdditionalShortTime();
			sendDataToInput(getElement("addressCity"), getFaker().address().city(), null, getForm());
			waitAdditionalShortTime();

			scrollToWebElement(getWebElement(By.id("addressStateId")), "//div[contains(@class, 'ContentDiv')]");

			interactAndRandomSelectFromDropDown("addressStateId", "addressStateId_list");

			setImageImproved("Driver's License Photo (Front)", null);

			setImageImproved("Driver's License Photo (Back)", null);

			scrollToWebElement(getWebElement(By.id("dlNumber")), "//div[contains(@class, 'ContentDiv')]");
			sendDataToInput(getElement("dlNumber"), getFaker().number().digits(7), null, getForm());
			waitAdditionalShortTime();

			sendDataToInput(getElement("dlIssuedDate"), formatter.format(getFaker().date().past(2, TimeUnit.DAYS)), null, getForm());
			sendDataToInput(getElement("dlIssuedDate"), null, Keys.RETURN, getForm());
			waitAdditionalShortTime();

			sendDataToInput(getElement("dlExpirationDate"), formatter.format(getFaker().date().future(3, TimeUnit.DAYS)), null, getForm());
			sendDataToInput(getElement("dlExpirationDate"), null, Keys.RETURN, getForm());

			interactAndRandomSelectFromDropDown("dlClassType", "dlClassType_list");

			return true;
		} catch(Exception e) {
			Assert.fail(e.getMessage());
			return false;
		}
	}

	public boolean searchDriverCreated() {
		try {
			waitForSpinningElementDisappear();
			setFullName(Setup.getValueStore("driverName") + " " + Setup.getValueStore("driverLastName"));
			//fullName = Setup.getValueStore("driverName") + " " + Setup.getValueStore("driverLastName");

			Setup.getActions().sendKeys(getWebElement(searchFieldLocator), getFullName()).build().perform();
			Setup.getActions().sendKeys(getWebElement(searchFieldLocator), Keys.RETURN).build().perform();

			Assert.assertNotNull(getWebElement(By.xpath("//td[text()='" + getFullName() +  "']")));

			return true;
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return false;
		}
	}


}
