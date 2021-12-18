package goheavy.driver;

import general.PageObject;
import general.Steps;
import goheavy.driver.pages.DriverPage;
import org.junit.Assert;

@SuppressWarnings("unused")
public class DriverStep extends Steps{
	private final DriverPage driverListPage;
	private final DriverPage driverPage;
	private final PageObject po;

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
