package goheavy.driver.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
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

	public boolean insertValidData(boolean update) {
		try {
			waitForSpinningElementDisappear();
			waitAdditionalTime();

			String title = "Driver Photo (including shoulders)";
			setImageImproved(title, null);
			//acceptImage(title); <- Using in Fleet Owner Driver Creation Process

			String formId = "driver-form";

			String name = getFaker().name().firstName();
			Setup.setKeyValueStore("driverName", name);

			if (update) {
				sendDataToInputByWebElement(getWebElement(By.id("firstName")), (String) Setup.getValueStore("driverName"));
				sendDataToInputByWebElement(getWebElement(By.id("lastName")), getFaker().name().lastName());
				sendDataToInputByWebElement(getWebElement(By.id("experienceYear")),
						String.valueOf(getFaker().number().numberBetween(3, 8)));
				sendDataToInputByWebElement(getWebElement(By.id("email")), getFaker().internet().emailAddress());

				//Setting GoHeavy Ready Status
				Setup.getActions().moveToElement(getWebElement(By.xpath("//input[@id='status']"))).build().perform();
				waitAdditionalShortTime();
				Setup.getActions().click(getWebElement(By.xpath("//input[@id='status']"))).build().perform();
				waitAdditionalShortTime();
				Setup.getActions().moveToElement(getWebElement(By.xpath("//div[text()='GoHeavy Ready']"))).build().perform();
				waitAdditionalShortTime();
				Setup.getActions().click(getWebElement(By.xpath("//div[text()='GoHeavy Ready']"))).build().perform();

				sendDataToInputByWebElement(getWebElement(By.id("address")), getFaker().address().streetName());
				sendDataToInputByWebElement(getWebElement(By.id("addressCity")), getFaker().address().cityName());

				formScrollImproved(formId, Integer.parseInt(Setup.getTimeouts().get("pageLoad").toString()));

				title = "Driver's License Photo (Front)";
				setImageImproved(title, null);
				title = "Driver's License Photo (Back)";
				setImageImproved(title, null);

				String xpath = "//*[@type='submit']";
				Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();
			}

			sendDataToInputImproved("First Name", (String) Setup.getValueStore("driverName"), null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();

			sendDataToInputImproved("Last Name", getFaker().name().lastName(), null,  InputType.input, true, formId, 40);
			waitAdditionalShortTime();

			int min_val = 22;
			int max_val = 55;

			ThreadLocalRandom tlr = ThreadLocalRandom.current();
			int randomNum = tlr.nextInt(min_val, max_val + 1);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String date_compare = dtf.format(LocalDateTime.now().plusYears(randomNum * -1));

			sendDataToInputImproved("Birth Date", date_compare, null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();
			sendDataToInputImproved("Birth Date", null, Keys.RETURN,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();

			sendDataToInputImproved("Experience", String.valueOf(getFaker().number().numberBetween(3, 8)), null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();

			sendDataToInputImproved("Mobile", "53" + getFaker().number().digits(8), null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();

			sendDataToInputImproved("Email", getFaker().internet().emailAddress(), null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();

			scrollToWebElement(getWebElement(By.id("tShirtSize")), "//div[contains(@class, 'ContentDiv')]");

			//tShirtSize
			//tShirtSize_list
			interactAndRandomSelectFromDropDown("tShirtSize", "tShirtSize_list");

			sendDataToInputImproved("ZIP Code", getFaker().address().zipCode(), null,  InputType.input, true, formId, 210);
			waitAdditionalShortTime();

			sendDataToInputImproved("Address", getFaker().address().streetName(), null,  InputType.textarea, true, formId, 210);
			waitAdditionalShortTime();

			sendDataToInputImproved("City", getFaker().address().cityName(), null,  InputType.input, true, formId, 210);
			waitAdditionalShortTime();

			scrollToWebElement(getWebElement(By.id("addressStateId")), "//div[contains(@class, 'ContentDiv')]");

			//Setup.getWait().thread(60000);

			//addressStateId
			//addressStateId_list
			interactAndRandomSelectFromDropDown("addressStateId", "addressStateId_list");

			//Driver's License Photo (Front)
			title = "Driver's License Photo (Front)";
			setImageImproved(title, null);

			//Driver's License Photo (Back)
			title = "Driver's License Photo (Back)";
			setImageImproved(title, null);

			sendDataToInputImproved("Driver's License (DL) Number", "1" + getFaker().number().digits(6), null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();

			scrollToWebElement(getWebElement(By.id("dlClassType")), "//div[contains(@class, 'ContentDiv')]");

			//dlClassType
			//dlClassType_list
			interactAndRandomSelectFromDropDown("dlClassType", "dlClassType_list");

			min_val = 2;
			max_val = 5;

			randomNum = tlr.nextInt(min_val, max_val + 1);

			dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			date_compare = dtf.format(LocalDateTime.now().plusMonths((randomNum * -1)));

			sendDataToInputImproved("DL Issued Date", date_compare, null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();
			sendDataToInputImproved("DL Issued Date", null, Keys.RETURN,
					InputType.input, true, formId, 40);

			date_compare = dtf.format(LocalDateTime.now().plusMonths((randomNum)));

			sendDataToInputImproved("DL Expiration Date", date_compare, null,
					InputType.input, true, formId, 40);
			waitAdditionalShortTime();
			sendDataToInputImproved("DL Expiration Date", null, Keys.RETURN,
					InputType.input, true, formId, 40);

			String xpath = "//*[@type='submit']";
			Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();

			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void checkDriverCreation() {
		String driverNb = getElement("firstName").getAttribute("value") +
				getElement("lastName").getAttribute("value");
		sendDataToInput(getWebElement(searchFieldLocator), driverNb, Keys.RETURN,null);
		Assert.assertEquals("Driver's Name does not match. \nExpected: "+ driverNb +
						"\nActual: "+getWebElement(newDriverNameLocator).getAttribute("value"),
				driverNb,getWebElement(newDriverNameLocator).getAttribute("value"));
	}
}
