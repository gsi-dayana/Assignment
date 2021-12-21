package goheavy.driver;

import general.PageObject;
import general.Setup;
import general.Steps;
import goheavy.driver.pages.DriverListPage;
import goheavy.driver.pages.DriverPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")
public class DriverStep extends Steps{
	private final DriverPage driverPage;
	private final DriverListPage driverListPage;
	private final PageObject po;

	public DriverStep() {
		driverPage = new DriverPage();
		driverListPage = new DriverListPage();
		po = new PageObject();
	}

	public void checkPage() {
		String path = driverPage.getPagePath().toLowerCase();
		Assert.assertTrue(" The path provided is not correct in the url. path: " + path,
				driverPage.getCurrentUrl().toLowerCase().contains(path));
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

	public void checkDriverCreated() {
		Assert.assertTrue(driverPage.searchDriverCreated());
	}

	public void clickOnAddButton() {
		po.clicks_button_done();
	}

	public void checkDriverListView() {
		Assert.assertTrue(driverListPage.checkDriverListView());
	}

	public void clickDriverDocument() {
		String path = "//td[text()='"+ driverPage.getFullName() +"']/ancestor::tr/descendant::span[@class='anticon anticon-file-text']";
		WebElement doc = po.getWebElement(By.xpath(path));
		Setup.getActions().click(doc).build().perform();
		Assert.assertTrue("Driver Documents page not found",
				po.getWebElement(By.xpath("//span[contains(@class,'iaAGQP')]")).getAttribute("value").contains("Documents for "+ driverPage.getFullName()));
	}
}
