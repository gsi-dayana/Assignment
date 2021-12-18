package goheavy.account.page;

import general.PageObject;
import general.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountPage extends PageObject{

    private String formScroll = "//*[@id=\"account-settings\"]/ancestor::div["
            + "@class=\"templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj\"]";

    public AccountPage() {
        super();
        this.urlpath = "accountsettings";
    }

    public WebElement getFrom() {
        return this.getWebElement(By.cssSelector("#account-settings"));
    }

}



