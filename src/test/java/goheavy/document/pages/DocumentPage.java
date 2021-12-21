package goheavy.document.pages;

import general.PageObject;
import general.Setup;
import org.junit.Assert;
import org.openqa.selenium.By;

public class DocumentPage extends PageObject {

    By documentTitleLocator = By.xpath("//span[contains(@class,'iaAGQP')]");
    By docIconLocator = By.xpath("//span[contains(@class,'anticon-file-text')][1]");
    By statusesListLocator = By.xpath("//span[contains(@class,'ant-tag')]");
    By viewIconLocator = By.xpath("//span[contains(@class,'anticon-check-circle')]");

    public DocumentPage() {
        super();
        this.urlPath = "document/driver";
    }

    public boolean clickOnDocIcon() {
        String driverName = getWebElement(By.xpath("//td[2]")).getText();
        Setup.setKeyValueStore("driverFullName",driverName);
        clickOn(getWebElement(docIconLocator));
        return true;
    }

    public void  checkDocumentView() {
        waitForSpinningElementDisappear();
        Assert.assertTrue("Document title not found",
                getWebElement(documentTitleLocator).getText().equals("Documents for " + Setup.getValueStore("driverFullName")));
    }


}
