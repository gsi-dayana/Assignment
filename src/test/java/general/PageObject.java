package general;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class PageObject {
    private WebDriver driver;
    protected String urlPath = "";
    private WebDriverWait wait;
    private int waitTime;
    private final String spinningElement = "//div[contains(@class,'ant-spin-spinning')]";
    public Faker faker;
    private HashMap<String, WebElement> choiceItems;

    public PageObject() {
        this.setDriver(Setup.getDriver());
        this.setFaker(new Faker());
        PageFactory.initElements(this.getDriver(), this);
        setWaitTime(20);
        setWait(new WebDriverWait(this.getDriver(), this.getWaitTime()));
        setChoiceItems(new HashMap<>());
    }

    public void setImage(WebElement element) {
        String avatar = (String) Setup.getValueStore("avatar");
        element.sendKeys(avatar);
        setChoiceItems(new HashMap<>());
    }

    public HashMap<String, WebElement> getChoiceItems() {
        return choiceItems;
    }

    public void setChoiceItems(HashMap<String, WebElement> choiceItems) {
        this.choiceItems = choiceItems;
    }

    @SuppressWarnings("unused")
    public void setImageImproved(String title, Object object) {
        String xpath = "//label[@title=\"" + title + "\"]/ancestor::div[@class='ant-row ant-form-item']"
                + "/descendant::input[@type='file']";
        WebElement image_element = getWebElement(By.xpath(xpath));
        String avatar = (String) Setup.getValueStore("avatar");
        image_element.sendKeys(avatar);
    }

    @SuppressWarnings("unused")
    public void sendDataToInputImproved(String labelText, String data, Keys key, InputType type, boolean scroll, String form,
                                        int y_pos) {
        String xpath = "//label[text()=\"" + labelText + "\"]/ancestor::div[@class='ant-row ant-form-item']/"
                + "descendant::" + type + "[@placeholder]";
        WebElement element = getWebElement(By.xpath(xpath));
        Setup.getActions().moveToElement(element).build().perform();
        if (scroll)
            formScrollImproved(form, y_pos);
        if (key != null)
            Setup.getActions().sendKeys(element, key).build().perform();
        else
            Setup.getActions().sendKeys(element, data).build().perform();
    }

    @SuppressWarnings("unused")
    public void print(Object string) {
        System.out.println(string);
    }

    @SuppressWarnings("unused")
    public void submitForm(String formId) {
        try {
            String formXpath = "//*[@id='" + formId + "']/ancestor::div"
                    + "[@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']";
            getWebElement(By.xpath(formXpath)).submit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void formScrollImproved(String form, int yScrollBy) {
        String formXpath = "//*[@id='" + form + "']/ancestor::div[@class='templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj']";
        String script = "arguments[0].scrollBy(0, " + yScrollBy + ");";
        Setup.getJsExecutor().executeScript(script, getWebElement(By.xpath(formXpath)));
    }

    @SuppressWarnings("unused")
    public void killEvent() {
        System.exit(0);
    }

    @SuppressWarnings("unused")
    public void acceptImage(String title) {
        String xpath = "//label[@title=\"" + title + "\"]/ancestor::div[contains(@class, 'ant-col')]/"
                + "descendant::span[@class='anticon anticon-check']";

        Setup.getActions().moveToElement(getWebElement(By.xpath(xpath))).build().perform();
        Setup.getWait().thread(250);
        Setup.getActions().click(getWebElement(By.xpath(xpath))).build().perform();
        Setup.getWait().thread(200);
    }

    public Faker getFaker() {
        return faker;
    }

    void setFaker(Faker faker) {
        this.faker = faker;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void openURL() {
        Setup.openUrl(System.getProperty("defaultURL").concat("/").concat(urlPath));
    }

    public WebElement getWebElement(By by) {
        return this.getDriver().findElement(by);
    }

    protected List<WebElement> getWebElements(By by) {
        return this.getDriver().findElements(by);
    }

    protected void clicksOnButton(By by) {
        getWebElement(by).click();
        waitForSpinningElementDisappear();
        Setup.getWait().waitForLoading((Integer) Setup.getTimeouts().get("implicit"));
    }

    public void sendDataToInputByWebElement(WebElement element, String data) {
        clear_element_text(element);
        Setup.getActions().moveToElement(element).build().perform();
        Setup.getActions().sendKeys(element, data).build().perform();
    }

    public void interactAndRandomSelectFromDropDown(String id_dropdown, String id_options) {
        try {
            WebElement element = getWebElement(By.xpath("//input[@id='" + id_dropdown +"' and @role='combobox']"));
            Setup.getActions().moveToElement(element).build().perform();
            Setup.getActions().click(element).build().perform();
            String xpath = "//div[@role='listbox' and @id='" + id_options + "']/ancestor::div[contains(@class, "
                    + "'ant-select-dropdown')]/descendant::div[@class='ant-select-item-option-content']";
            List<WebElement> select_elements = getWebElements(By.xpath(xpath));
            WebElement option_element = select_elements.get(
                    getFaker().number().numberBetween(3, 5));
            Setup.getActions().moveToElement(option_element).build().perform();
            Setup.getWait().thread(750);
            Setup.getActions().click(option_element).build().perform();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCurrentUrl() {
        return this.getDriver().getCurrentUrl();
    }

    public String getPagePath() {
        return this.urlPath;
    }

    public void checkSpinningAppears() {
        Setup.getWait().waitUntilElementAppear(By.xpath(spinningElement), (Integer) Setup.getTimeouts().get("pageLoad"));
        Setup.getWait().waitForLoading((Integer) Setup.getTimeouts().get("implicit"));
    }

    public void waitForSpinningElementDisappear() {
        try {
            //Setup.getWait().waitForLoading((Long) (Setup.getTimeouts().get("implicit")));
            checkSpinningAppears();
            Setup.getWait().waitUntilElementDisappear(By.xpath(spinningElement), 10);
            Setup.getWait().waitForLoading((Long) (Setup.getTimeouts().get("implicit")));
            Setup.getWait().thread(500);
        } catch (Exception ignored) { }
    }

    @SuppressWarnings("unused")
    public void scroll(String scrollElementXpath, By targetElementXpath) {

        WebElement el = this.getWebElement(targetElementXpath);
        int desired_y = el.getSize().height / 2 + el.getLocation().y;

        int current_y = (Integer.parseInt(String.valueOf(Setup.executeScript("return window.innerHeight"))) / 2)
                + Integer.parseInt(String.valueOf(Setup.executeScript("return window.pageYOffset")));
        int scroll_y_by = (desired_y + 150) + (current_y + 150);

        Setup.executeScript("var el=" + "document.evaluate('" + scrollElementXpath + "',"
                + " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
                + " el.scrollTo(0, arguments[0]);", scroll_y_by);

        Setup.getWait().thread(500);
    }


    public void clickOn(WebElement element) {
        waitForSpinningElementDisappear();
        Setup.getWait().thread(150);
        Setup.getActions().moveToElement(element).build().perform();
        Setup.getActions().click(element).build().perform();
        waitForSpinningElementDisappear();
        Setup.getWait().thread(150);
    }

    public void clickOnElement(By by) {
        Setup.getActions().moveToElement(getWebElement(by)).build().perform();
        Setup.getActions().click(getWebElement(by)).build().perform();
    }

    public void sendDataToInput(WebElement element, Object data, Keys key, String form) {
        try {
            if (element.getAttribute("value").length() > 0)
                clear_element_text(element);
        } catch (Exception ignored) { }
        scrollToWebElement(element, form);
        if (data != null)
            Setup.getActions().sendKeys(element, (CharSequence) data).build().perform();
        else
            Setup.getActions().sendKeys(element, key).build().perform();
    }

    public  void scrollToWebElement(WebElement element, String form) {
        Setup.getWait().thread(250);
        int y_pos = Integer.parseInt(Setup.timeouts.get("script").toString());
        if (element != null)
            y_pos = element.getLocation().y / 2;

        String script = "arguments[0].scrollBy(0, " + y_pos + ");";
        Setup.getJsExecutor().executeScript(script, getWebElement(By.xpath(form)));
        Setup.getWait().thread(500);
    }

    public void clear_element_text(WebElement element) {
        int length = element.getAttribute("value").length();
        for (int i = 0; i <= length; i++) {
            Setup.getActions().sendKeys(element, Keys.BACK_SPACE).perform();
        }
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    @SuppressWarnings("unused")
    public HashMap<String, WebElement> getMenu(By by) {
        waitForSpinningElementDisappear();
        Setup.getWait().thread(4000);
        HashMap<String, WebElement> list = new HashMap<>();

        if (!Objects.isNull(by)) {
            WebElement e = this.getWebElement(by);

            WebElement el2 = e.findElement(By.xpath("span[2]"));
            list.put(el2.getText(), e);
        }
        return list;
    }

    public WebElement getElement(String id) {
        return getWebElement(By.id(id));
    }

    public void clicks_button_done() {
        clickOn(getWebElement(By.xpath("//button[@type='submit']")));
        waitForSpinningElementDisappear();
    }

    @SuppressWarnings("unused")
    public void clicks_button_cancel() {
        clickOn(getWebElement(By.xpath("//button[@type='button']/span[text()='Cancel']")));
        waitForSpinningElementDisappear();
    }

    public boolean hoverElement(By by, WebElement element) {
        try {
            Setup.getWait().thread(500);
            if (element != null)
                Setup.getActions().moveToElement(element).build().perform();
            else
                Setup.getActions().moveToElement(getWebElement(by)).build().perform();
            Setup.getWait().thread(500);
            return true;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unused")
    public void selectDropdown(String fieldName,By dropdown,By options) {
        try {
            Setup.getWait().thread(150);
            Setup.getActions().moveToElement(getWebElement(dropdown)).build().perform();
            Setup.getActions().click(getWebElement(dropdown)).build().perform();
            Setup.getWait().thread(150);
            Assert.assertNotNull(fieldName +" not found in the page.", getWebElement(dropdown));
            WebElement dropdown_element = getWebElement(dropdown);
            List<WebElement> dropdown_options = dropdown_element.findElements(options);
            int val = dropdown_options.size();
            if (dropdown_options.size() > 3)
                val = 0;
            int number = (int) (Math.random() * val + 1);
            hoverElement(null, dropdown_options.get(number));
            clickOn(dropdown_options.get(number));
            Setup.getWait().thread(150);
        } catch(Exception ignored) { }
    }

    @SuppressWarnings("unused")
    public void interactWithDropDownElement(By dropDown, Boolean scrollRequired, By dropDownContainer) {
        int index = 0;
        waitForWebElement(dropDown);
        WebElement element = getWebElement(dropDown);
        if (scrollRequired && dropDownContainer != null)
            scroll(getWebElement(dropDownContainer), element);
        moveToWebElement(element);
        clickOnWebElement(element);
        WebElement optionElementsContainer = getWebElement(By.xpath("//div[@role='listbox' and @id='" +
                element.getAttribute("id") + "_list']"));
        waitForWebElement(By.xpath("//div[@role='option' and @aria-label]"));
        WebElement choiceElement = optionElementsContainer.findElement(By.xpath
                ("//div[@role='option' and @aria-label]"));
        moveToWebElement(choiceElement);
        moveToWebElement(getWebElement(By.xpath("//div[@class='rc-virtual-list-holder']")));
        scrollVerticalDown(10, getWebElement(By.xpath("//div[@class='rc-virtual-list-holder']")), false,
                null);

        HashMap<Integer, String> choiceElementsIndexes = new HashMap<>();

        for (Map.Entry<String, WebElement> choiceItem: getChoiceItems().entrySet())
            choiceElementsIndexes.put(index++, choiceItem.getKey());

        int randomIndexToSelect = (int) Math.floor(Math.random() * index + 1);
        String elementToSelect = choiceElementsIndexes.get(randomIndexToSelect);

        scrollVerticalDown(10, getWebElement(By.xpath("//div[@class='rc-virtual-list-holder']")), true,
                elementToSelect);
    }

    public void scroll(WebElement containerElement, WebElement targetElement) {
        if (targetElement.getLocation().getY() > 45) {
            String script = "arguments[0].scrollTo(" + targetElement.getLocation().getX() + ", " +
                    (targetElement.getLocation().getY() - 45) + ");";
            Setup.getJsExecutor().executeScript(script, containerElement);
        }
    }
    public void scrollVerticalDown(int times, WebElement element, boolean select, String selectItem) {
        int defaultSize = 200;

        for (int i = 0;i < times;i++) {
            Setup.getWait().thread(defaultSize);
            Setup.getJsExecutor().executeScript("arguments[0].scrollBy(0, " + defaultSize + ");", element);
            fillChoiceItems(getWebElements(By.xpath("//div[@class='ant-select-item-option-content']")));
            if (select) {
                try {
                    Setup.getActions().click(getWebElement(By.xpath("//div[text()='" + selectItem + "']"))).
                            build().perform();
                    return;
                } catch (Exception ignored) { }
            }
        }

        for (int i = 0;i < times;i++) {
            Setup.getWait().thread(defaultSize);
            Setup.getJsExecutor().executeScript("arguments[0].scrollBy(0, " +
                    defaultSize * -1 + ");", element);
        }
    }
    private void fillChoiceItems(List<WebElement> elements) {
        try {
            for (WebElement element: elements) {
                if (!getChoiceItems().containsKey(element.getText())) {
                    getChoiceItems().put(element.getText(), element);
                }
            }
        } catch (Exception ignored) { }
    }

    public void clickOnWebElement(WebElement element) {
        Setup.getActions().click(element).build().perform();
    }

    public void moveToWebElement(WebElement element) {
        Setup.getActions().moveToElement(element).build().perform();
    }

    public void waitForWebElement(By by) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
