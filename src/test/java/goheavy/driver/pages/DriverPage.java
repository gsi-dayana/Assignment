package goheavy.driver.pages;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import general.InputType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import general.PageObject;
import general.Setup;

@SuppressWarnings("unused")
public class DriverPage extends PageObject {
	private final By menuDriversLinkLocator = By.xpath("//span[text()='Drivers']/ancestor::span[@class='ant-menu-title-content']");
	private final By addDriverButtonLocator = By.xpath("//span[text()='Add Driver']/ancestor::button[@class='ant-btn ant-btn-primary']");
	private final By addDriverTitleLocator = By.xpath("//span[text()='Add Driver']/ancestor::div[@class='ant-row ant-row-space-between ant-row-middle']");
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

	public DriverPage() {
		super();
		this.urlPath = "/driver";
		formElements = new HashMap<>();
		setForm("//form[@id='driver-form']");
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public By getMenuDriversLinkLocator() {
		return menuDriversLinkLocator;
	}

	public By getAddDriverButtonLocator() {
		return addDriverButtonLocator;
	}

	public By getAddDriverTitleLocator() {
		return addDriverTitleLocator;
	}

	public boolean goToView() {
		try {
			waitForSpinningElementDisappear();
			//waitAdditionalTime();
			clickOnElement(menuDriversLinkLocator);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
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

	public void getFormElements() {
		formElements.put("driverPhoto",getWebElement(By.xpath("//label[contains(text(),'shoulders')]/" +
				"ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']")));
		formElements.put("firstName", getElement("firstName"));
		formElements.put("lastName", getElement("lastName"));
		formElements.put("birthAt", getElement("birthAt"));
		formElements.put("experienceYear", getElement("experienceYear"));
		formElements.put("mobilePhone", getElement("mobilePhone"));
		formElements.put("email", getElement("email"));
		formElements.put("address", getElement("address"));
		formElements.put("addressCity", getElement("addressCity"));
		formElements.put("addressStateId", getElement("addressStateId"));
		formElements.put("addressCountryId", getElement("addressCountryId"));
		formElements.put("addressZipCode", getElement("addressZipCode"));
		formElements.put("state", getElement("addressStateId"));
		formElements.put("driverLicenseFront",getWebElement(By.xpath("//label[contains(@title,'(Front)')]/"
				+ "ancestor::div[contains(@class,'ant-form-item')]/descendant::input[@type='file']")));
		formElements.put("driverLicenseBack",getWebElement(By.xpath("//label[contains(@title,'(Back)')]/"
				+ "ancestor::div[contains(@class,'ant-form-item')]/descendant::input[@type='file']")));
		formElements.put("dlNumber", getElement("dlNumber"));
		formElements.put("dlIssuedDate", getElement("dlIssuedDate"));
		formElements.put("dlExpirationDate", getElement("dlExpirationDate"));
		formElements.put("dlClassType", getElement("dlClassType"));
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

	public boolean checkDriverCreation() {
		try {
			waitForSpinningElementDisappear();
			String fullName = Setup.getValueStore("driverName") + " " + Setup.getValueStore("driverLastName");

			Setup.getActions().sendKeys(getWebElement(searchFieldLocator), fullName).build().perform();
			Setup.getActions().sendKeys(getWebElement(searchFieldLocator), Keys.RETURN).build().perform();

			Assert.assertNotNull(getWebElement(By.xpath("//td[text()='" + fullName +  "']")));

			return true;
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return false;
		}
	}

}
