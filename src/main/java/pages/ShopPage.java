package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.entities.ShopDB;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Igor Odokienko.
 */
public class ShopPage extends BasePage {

    private String shopName;

    public ShopPage(WebDriver driver, String shopName) {
        super(driver);
        this.shopName = shopName;
    }

    @Step
    public void checkSiteUrl() {
        Assert.assertTrue(driver.getCurrentUrl().contains(ShopDB.getShopUrlByName(shopName)));
        LOG.info("Site URL matches to expected: " + driver.getCurrentUrl().contains(ShopDB.getShopUrlByName(shopName)));
    }

}
