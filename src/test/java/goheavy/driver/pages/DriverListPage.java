package goheavy.driver.pages;

import general.PageObject;
import org.openqa.selenium.By;

public class DriverListPage extends PageObject {

	private final By menuDriversLinkLocator = By.xpath("//span[text()='Drivers']/ancestor::span[@class='ant-menu-title-content']");
	private final By addDriverTitleLocator = By.xpath("//span[text()='Add Driver']/ancestor::div[@class='ant-row ant-row-space-between ant-row-middle']");

	public DriverListPage() {
		super();
		this.urlPath = "/driver";
	}

	public boolean goToView() {
			waitForSpinningElementDisappear();
			clickOnElement(menuDriversLinkLocator);
			return true;
	}

	public boolean checkDriverListView() {
		return getWebElement(addDriverTitleLocator).isDisplayed();
	}
}
