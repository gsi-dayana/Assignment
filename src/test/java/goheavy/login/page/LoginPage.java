package goheavy.login.page;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import general.PageObject;
import general.Setup;

public class LoginPage extends PageObject {
	String user_id = "email";
	String password_id = "password";
	String logo_alt_text = "Site Logo";
	String logo_image_src =
			"https://goheavy-qa.gsoftinnovation.net/storage/public/setting/wBkh9qGXs0tiTOIJaA0i4GT18fSQsAKs0wzhfo5z.png";
	String LoginH1Xpath;
	String LoginFormXpath;
	String LoginPassShowHideButtonXpath;
	String CompanyLogoXpath;
	String FormSubmitButtonXpath;
	String email;
	String password;
	private By element;
	private String missingRequiredFieldText;
	private String EmailRequiredTextXpath;
	private String PassRequiredTextXpath;
	private String ForgotPassLinkXpath;
	private String IncorrectEmailOrPassDivXpath;
	private HashMap<String, WebElement> elements;

	public LoginPage() {
		super();
		setLoginH1Xpath("//h1[text()='Login']");
		setLoginFormXpath("//form[@id='admin-form-session']");
		setCompanyLogoXpath();
		setLoginPassShowHideButtonXpath(
				"//span[@role='img' and @aria-label='eye-invisible' and "
				+ "@class='anticon anticon-eye-invisible ant-input-password-icon']");
		setFormSubmitButtonXpath("//button[@environment='admin' and @type='button']");
		setForgotPassLinkXpath();
		setEmailRequiredTextXpath();
		setPassRequiredTextXpath();
		setIncorrectEmailOrPassDivXpath();
		setElements(new HashMap<>());
		setWaitTime(5000);
		this.urlPath = "login";
	}

	public WebElement getElement(By by) {
		return getWebElement(by);
	}

	By getElement() {
		return element;
	}

	void setElement(By element) {
		this.element = element;
	}

	public String getIncorrectEmailOrPassDivXpath() {
		return IncorrectEmailOrPassDivXpath;
	}

	void setIncorrectEmailOrPassDivXpath() {
		IncorrectEmailOrPassDivXpath = "//div[@class='ant-notification-notice-message']";
	}

	String getMissingRequiredFieldText() {
		return missingRequiredFieldText;
	}

	void setMissingRequiredFieldText(String missingRequiredFieldText) {
		this.missingRequiredFieldText = missingRequiredFieldText;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HashMap<String, WebElement> getElements() {
		return elements;
	}

	void setElements(HashMap<String, WebElement> elements) {
		this.elements = elements;
	}

	String getPassRequiredTextXpath() {
		return PassRequiredTextXpath;
	}

	void setPassRequiredTextXpath() {
		PassRequiredTextXpath = "//label[@title='Password']/../..//div[@role='alert']";
	}

	String getEmailRequiredTextXpath() {
		return EmailRequiredTextXpath;
	}

	void setEmailRequiredTextXpath() {
		EmailRequiredTextXpath = "//label[@title='Email']/../..//div[@role='alert']";
	}

	public String getForgotPassLinkXpath() {
		return ForgotPassLinkXpath;
	}

	void setForgotPassLinkXpath() {
		ForgotPassLinkXpath = "//span[@class='ant-tag ant-tag-checkable']";
	}

	public String getFormSubmitButtonXpath() {
		return FormSubmitButtonXpath;
	}

	public void setFormSubmitButtonXpath(String formSubmitButtonXpath) {
		FormSubmitButtonXpath = formSubmitButtonXpath;
	}

	public String getLoginPassShowHideButtonXpath() {
		return LoginPassShowHideButtonXpath;
	}

	public void setLoginPassShowHideButtonXpath(String loginPassShowHideButtonXpath) {
		LoginPassShowHideButtonXpath = loginPassShowHideButtonXpath;
	}

	String getCompanyLogoXpath() {
		return CompanyLogoXpath;
	}

	void setCompanyLogoXpath() {
		CompanyLogoXpath = "//img[@alt='Site Logo']";
	}

	public String getLoginH1Xpath() {
		return LoginH1Xpath;
	}

	public void setLoginH1Xpath(String loginH1Xpath) {
		LoginH1Xpath = loginH1Xpath;
	}

	public String getLoginFormXpath() {
		return LoginFormXpath;
	}

	public void setLoginFormXpath(String loginFormXpath) {
		LoginFormXpath = loginFormXpath;
	}

	public boolean getCompanyLogoAndImage() {
		setElement(By.xpath(getCompanyLogoXpath()));
		getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
		WebElement element = getElement(By.xpath(getCompanyLogoXpath()));
		return element.isDisplayed() && element.getAttribute("alt").equals(logo_alt_text) &&
				element.getAttribute("src").equals(logo_image_src);
	}

	public void fillCredentials(String email, String password) {
		setEmail(email);
		setPassword(password);
		WebElement email_element = getWebElement(By.id(user_id));
		email_element.sendKeys(getEmail());
		WebElement pass_element = getWebElement(By.id(password_id));
		pass_element.sendKeys(getPassword());

		getElements().put("email", email_element);
		getElements().put("password", pass_element);
	}

	public boolean getMissingFieldsErrorMessage(String message) {
		setMissingRequiredFieldText(message);

		try {
			if (getEmail().trim().isEmpty()) {
				setElement(By.xpath(getEmailRequiredTextXpath()));
				getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
				Setup.getWait().waitUntilElementAppear(By.xpath(getEmailRequiredTextXpath()), getWaitTime());
				WebElement missing_email_element = getWebElement(By.xpath(getEmailRequiredTextXpath()));
				getElements().put("required_email", missing_email_element);
				if (!getElements().get("required_email").getText().equals(getMissingRequiredFieldText()))
					return false;
			} else {
				throw new Exception("--Information-- Email is not Missing from Login Form Input");
			}

			if (getPassword().trim().isEmpty()) {
				setElement(By.xpath(getPassRequiredTextXpath()));
				getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
				WebElement missing_pass_element = getWebElement(By.xpath(getPassRequiredTextXpath()));
				getElements().put("required_password", missing_pass_element);
				if (!getElements().get("required_password").getText().equals(getMissingRequiredFieldText()))
					return false;
			} else {
				throw new Exception("--Information-- Password is not Missing from Login Form Input");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public boolean getNotRegisteredInfoErrorMessage(String message) {
		setElement(By.xpath(getIncorrectEmailOrPassDivXpath()));
		getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
		By element = By.xpath(getIncorrectEmailOrPassDivXpath());
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));
		return getElement(By.xpath(getIncorrectEmailOrPassDivXpath())).getText().equals(message);
	}

	public void clickOn(By by) {
		clicksOnButton(by);
	}

	public void waitForElementDisappear() {
		Setup.getWait().waitUntilElementDisappear(By.id(user_id), getWaitTime());
		Setup.getWait().waitUntilElementDisappear(By.id(password_id), getWaitTime());
	}
}
