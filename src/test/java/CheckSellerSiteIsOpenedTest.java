import org.junit.Test;
import pages.HomePage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Created by Igor Odokienko.
 */
@Features("Search")
public class CheckSellerSiteIsOpenedTest extends BaseTest {

    @Test
    @Stories("Make sure that user redirected to the seller site when click on offer in search results")
    @Description("Select min price between offers that have more then 10 comments and warranty not less than 6 month and go to the seller site")
    public void checkSiteIsOpened() {
        HomePage homePage = new HomePage(driver);
        homePage.open()
                .closeRegionPopup()
                .enterTextInSearchBox("iPhone")
                .selectFromDropDownList("Apple iPhone 7")
                .goToProductAllOffersPage()
                .defineOfferWithMinPrice()
                .pressGoToShopButton()
                .checkSiteUrl();
    }

}