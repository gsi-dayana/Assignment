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

@SuppressWarnings("unused")
public class DriverPage extends PageObject {
	private By menuDriversLinkLocator = By.xpath("//span[text()='Drivers']/ancestor::span[@class='ant-menu-title-content']");
	private By addDriverButtonLocator = By.xpath("//span[text()='Add Driver']/ancestor::button[@class='ant-btn ant-btn-primary']");
	private By addDriverTitleLocator = By.xpath("//span[text()='Add Driver']/ancestor::div[@class='ant-row ant-row-space-between ant-row-middle']");
	private HashMap<String, WebElement> formElements;
	By driverPhotoLocator = By.xpath("//label[contains(text(),'shoulders')]/" +
			"ancestor::div[@class='ant-row ant-form-item']/descendant::input[@type='file']");
	By driverLicenseFrontLocator = By.xpath("//label[contains(@title,'(Front)')]/"
			+ "ancestor::div[contains(@class,'ant-form-item')]/descendant::input[@type='file']");
	By driverLicenseBackLocator = By.xpath("//label[contains(@title,'(Back)')]/"
			+ "ancestor::div[contains(@class,'ant-form-item')]/descendant::input[@type='file']");
	By tshirtSizelocator = By.id("tShirtSize");
	By tshirtOptionsLocator = By.id("tShirtSize_list");
	By searchFieldLocator = By.xpath("//input[@placeholder='Search...']");
	By newDriverNameLocator = By.xpath("//td[@class='ant-table-cell'][2]");
	String form;

	public DriverPage() {
		super();
		this.urlpath = "/driver";
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
			waitForSpinningElementDissapear();
			//waitAddittionalTime();
			clickOnElement(menuDriversLinkLocator);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void waitAddittionalTime() {
		Setup.getWait().thread(5000);
	}
	
	public void waitAddittionalShortTime() {
		Setup.getWait().thread(1000);
	}

	public boolean clicksOnAddDriverButton()
	{
		try {
			waitForSpinningElementDissapear();
			waitAddittionalTime();
			clickOnElement(addDriverButtonLocator);
			return true;
		} catch (Exception e) {
			return false;
		}
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
			waitForSpinningElementDissapear();
			String avatar = (String) Setup.getValueStore("avatar");
			getWebElement(driverPhotoLocator).sendKeys(avatar);
			sendDataToInput(getElement("firstName"), getFaker().name().firstName(), null, getForm());
			sendDataToInput(getElement("lastName"), getFaker().name().lastName(), null, getForm());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			sendDataToInput(getElement("birthAt"), formatter.format(getFaker().date().birthday(21, 80)), null, getForm());
			sendDataToInput(getElement("birthAt"), null, Keys.RETURN, getForm());
			sendDataToInput(getElement("experienceYear"), String.valueOf(getFaker().number().numberBetween(3, 11)), null, getForm());
			sendDataToInput(getElement("mobilePhone"), getFaker().phoneNumber().cellPhone(), null, getForm());
			sendDataToInput(getElement("mobilePhone"),null , Keys.RETURN, getForm());
			sendDataToInput(getElement("email"), getFaker().internet().emailAddress(), null, getForm());
			selectDropdown("T-Shirt Size", By.id("tshirt"), By.id("tshirtOptions"));
			//interactDropdown("tShirtSize","tShirtSize_list");
			//interactWithDropDownElement(tshirtSizelocator,true,tshirtOptionsLocator);
			sendDataToInput(getElement("address"), getFaker().address().streetAddress() + getFaker().address().streetAddressNumber() + getFaker().address().streetName(), null, getForm());
			sendDataToInput(getElement("addressZipCode"), getFaker().address().zipCode(), null, getForm());
			sendDataToInput(getElement("addressCity"), getFaker().address().city(), null, getForm());
			selectDropdown("State", By.id("addressStateId"), By.id("addressStateId_list"));
			getWebElement(driverLicenseFrontLocator).sendKeys(avatar);
			getWebElement(driverLicenseBackLocator).sendKeys(avatar);
			sendDataToInput(getElement("dlNumber"), getFaker().number().digits(7), null, getForm());
			sendDataToInput(getElement("dlIssuedDate"), formatter.format(getFaker().date().past(1, TimeUnit.DAYS)), null, getForm());
			sendDataToInput(getElement("dlExpirationDate"), formatter.format(getFaker().date().future(2, TimeUnit.DAYS)), null, getForm());
			selectDropdown("DL Class Type", By.id("dlClassType"), By.id("dlClassType_list"));
			//interactDropdown("dlClassType","dlClassType_list");
			clicks_button_done();
			return true;
		} catch(Exception e) {
			Assert.fail(e.getMessage());
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
