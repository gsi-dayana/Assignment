package goheavy.driver.pages;

import general.PageObject;
import org.openqa.selenium.By;

public class DriverDocuments extends PageObject {

    By statusesListLocator = By.xpath("//span[contains(@class,'ant-tag')]");
    By viewIconLocator = By.xpath("//span[contains(@class,'anticon-check-circle')]");

    public DriverDocuments() {
        super();
        this.urlPath = "document/driver";
    }


}
