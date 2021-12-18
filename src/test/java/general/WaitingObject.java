package general;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitingObject {

	private final WebDriver driver;

	public WaitingObject(WebDriver driver) {
		this.driver = driver;
		this.waitForLoading(20);
	}

	/**
	 * @executeExpectedCondition Method to execute the wait statement
	 * @param expected
	 * @param message
	 */
	public void executeExpectedCondition(ExpectedCondition<?> expected, String message) {
		waitMethod().withMessage(message).until(expected);
	}

	/**
	 *
	 * @param time
	 */
	public void waitForLoading(long time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}

	/**
	 *
	 * @param time
	 */
	@SuppressWarnings("unused")
	public void implicitWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 *
	 * @param by
	 * @param time
	 */
	@SuppressWarnings("unused")
	public void waitUntilElementAppear(By by, int time) {
		WebElement element1 = driver.findElement(by);
		ExpectedCondition<?> expectedCondition = ExpectedConditions.visibilityOf(element1);
		String mss = "Element " + element1 + " not found";
		executeExpectedCondition(expectedCondition, mss);
	}

	/**
	 *
	 * @param by
	 * @param time
	 */
	@SuppressWarnings("unused")
	public void waitUntilElementDisappear(By by, int time) {
		WebElement element1;
		try {
			element1 = driver.findElement(by);
		} catch (Exception e) {
			return;
		}
		ExpectedCondition<?> expectedCondition = ExpectedConditions.invisibilityOf(element1);
		String mss = "Element " + element1 + " still in dom ";
		executeExpectedCondition(expectedCondition, mss);

	}

	/**
	 *
	 * @return
	 */
	private WebDriverWait waitMethod() {
		return new WebDriverWait(this.driver, 10);
	}

	/**
	 *
	 * @param time
	 */
	public void thread(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
