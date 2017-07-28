package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;
import utils.PropertiesCache;

import java.util.List;

/**
 * Created by Igor Odokienko.
 */

public class HomePage extends BasePage {

    public static final String BASE_URL = PropertiesCache.getProperty("site.url");

    @FindBy(id = "searchbox")
    WebElement searchbox;
    @FindBy(xpath = "//header/div[2]")
    WebElement regionPopup;
    @FindBy(xpath = "//header/div[2]/span")
    WebElement closeRegionPopup;
    @FindBy(xpath = "//div[@id='live-search']//a")
    List<WebElement> linksInDropDown;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public HomePage open() {
        driver.get(BASE_URL);
        LOG.info(BASE_URL + " is opened");
        return this;
    }

    @Step
    public HomePage enterTextInSearchBox(String searchItem) {
        webDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(searchbox))
                .sendKeys(searchItem);
        LOG.info("'" + searchItem + "' is entered in searchbox");
        return this;
    }

    @Step
    public ProductMainPage selectFromDropDownList(String searchItem) {
        webDriverUtils.getWait()
                .until(ExpectedConditions.visibilityOfAllElements(linksInDropDown));
        linksInDropDown.stream()
                .filter(e -> e.getText().contains(searchItem))
                .findFirst()
                .get()
                .click();
        LOG.info("Clicked on first item in dropdown list containing '" + searchItem + "'");
        return new ProductMainPage(driver);
    }

    @Step
    public HomePage closeRegionPopup() {
        if (regionPopup.isDisplayed()) {
            webDriverUtils.getWait()
                    .until(ExpectedConditions.elementToBeClickable(closeRegionPopup))
                    .click();
            LOG.info("Closed 'Region' popup");
        }
        return this;
    }

}

