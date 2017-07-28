import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import utils.DriverFactory;

/**
 * Created by Igor Odokienko.
 */
public class BaseTest {

    protected WebDriver driver;

    @Rule
    public TestWatcher screenshotOnFailure = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenshotOnFailure("Screenshot on failure");
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }

    };

    @Before
    public void setUp() {
        System.out.println("Before");
        driver = new DriverFactory().getDriver();
    }

    @Attachment(value = "{0}", type = "image/png")
    private byte[] makeScreenshotOnFailure(String attachName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}