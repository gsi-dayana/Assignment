package goheavy.driver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import general.PageObject;
import general.Setup;
import general.Steps;
import goheavy.driver.pages.DriverPage;

@SuppressWarnings("unused")
public class DriverStep extends Steps{
	private DriverPage driverListPage;
	private DriverPage driverPage;
	private PageObject po;

	public DriverStep() {
		driverListPage = new DriverPage();
		driverPage = new DriverPage();
		po = new PageObject();
	}

	public void checkPage() {
		String path = driverListPage.getPagePath().toLowerCase();
		Assert.assertTrue(" The path provided is not correct in the url. path: " + path,
				driverListPage.getCurrentUrl().toLowerCase().contains(path));
	}

	public void goToView() {
		Assert.assertTrue(driverListPage.goToView());
	}

	public void clickOnAddDriver() {
//		Assert.assertTrue("Add Driver button not found in the view",
//				po.getWebElement(driverPage.getAddDriverButtonLocator()).isDisplayed());
//		po.clickOnElement(driverPage.getAddDriverButtonLocator());
		driverPage.clicksOnAddDriverButton();
	}
	public void addDriver() {
		Assert.assertTrue(driverPage.insertValidData());
	}

	public void checkNewDriver() {
		driverPage.checkDriverCreation();
	}

	public void clickOnAddButton() {
		po.clicks_button_done();
	}

	public void checkDriverListView() {
		Assert.assertTrue(po.getWebElement(driverListPage.getAddDriverTitleLocator()).isDisplayed());
	}

}
