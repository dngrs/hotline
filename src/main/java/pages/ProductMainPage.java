package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Igor Odokienko.
 */
public class ProductMainPage extends BasePage {

    @FindBy(xpath = "//b[text()='Все предложения']/parent::a")
    WebElement allOffersLink;

    public ProductMainPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public ProductAllOffersPage goToProductAllOffersPage() {
        webDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(allOffersLink))
                .click();
        LOG.info("Clicked on 'All offers' link");
        return new ProductAllOffersPage(driver);
    }

}
