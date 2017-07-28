package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Igor Odokienko.
 */
public class WebDriverUtils {

    private long explicitWait;
    private WebDriverWait wait;

    public WebDriverUtils(WebDriver driver) {
        explicitWait = Long.parseLong(PropertiesCache.getProperty("wait.explicit"));
        wait = new WebDriverWait(driver, explicitWait);
    }

    public WebDriverWait getWait() {
        return wait;
    }

}
