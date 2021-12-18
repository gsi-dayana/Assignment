package goheavy.account.page;

import general.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")
public class AccountPage extends PageObject{
    private final String formScroll = "//*[@id=\"account-settings\"]/ancestor::div["
            + "@class=\"templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj\"]";

    public AccountPage() {
        super();
        this.urlPath = "accountsettings";
    }

    @SuppressWarnings("unused")
    public WebElement getFrom() {
        return this.getWebElement(By.cssSelector("#account-settings"));
    }
}
