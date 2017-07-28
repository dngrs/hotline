package pages.entities;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ShopPage;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Igor Odokienko.
 */

@Getter
@Setter
@ToString(exclude = {"goToShopButton", "driver"})
@AllArgsConstructor
public class Offer {

    private String shopName;
    private int commentsNumber;
    private String warranty;
    private double price;
    private WebElement goToShopButton;
    private WebDriver driver;

    @Step
    public ShopPage pressGoToShopButton(){
        goToShopButton.click();
        LogManager.getLogger().info("Clicked on 'Go to shop' button");
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        return new ShopPage(driver, shopName);
    }

}
