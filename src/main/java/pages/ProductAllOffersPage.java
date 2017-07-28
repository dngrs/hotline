package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.entities.Offer;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Igor Odokienko.
 */
public class ProductAllOffersPage extends BasePage {

    @FindBy(css = ".cell.shop-title.text-ellipsis")
    List<WebElement> shopNameList;
    @FindBy(css = ".sum.g_statistic,.cell.text-11.p_l-10.no-reviews")
    List<WebElement> commentsList;
    @FindBy(css = ".delivery-th.cell4.cell-1024-b.p-10-0-1024-b")
    List<WebElement> warrantyList;
    @FindBy(id = "gotoshop-price")
    List<WebElement> priceList;
    @FindBy(id = "gotoshop-price-button")
    List<WebElement> goToShopButtonList;
    List<Offer> allOffers;

    public ProductAllOffersPage(WebDriver driver) {
        super(driver);
    }

    private void initializeAllOffersList() {

        if (allOffers == null) {

            allOffers = new ArrayList<>();

            Iterator<WebElement> shopNameIterator = webDriverUtils.getWait()
                    .until(ExpectedConditions.visibilityOfAllElements(shopNameList))
                    .iterator();
            Iterator<WebElement> commentsIterator = webDriverUtils.getWait()
                    .until(ExpectedConditions.visibilityOfAllElements(commentsList))
                    .iterator();
            Iterator<WebElement> warrantyIterator = webDriverUtils.getWait()
                    .until(ExpectedConditions.visibilityOfAllElements(warrantyList))
                    .iterator();
            Iterator<WebElement> priceIterator = webDriverUtils.getWait()
                    .until(ExpectedConditions.visibilityOfAllElements(priceList))
                    .iterator();
            Iterator<WebElement> goToShopButtonIterator = webDriverUtils.getWait()
                    .until(ExpectedConditions.visibilityOfAllElements(goToShopButtonList))
                    .iterator();

            while (shopNameIterator.hasNext()) {

                String shopName = shopNameIterator.next().getText();
                int comments = Integer.parseInt(commentsIterator.next().getText().replaceAll("[^0-9]", ""));
                String warranty = warrantyIterator.next().getText();
                double price = Double.parseDouble(priceIterator.next().getText().replaceAll("\\s", "").replace(",", "."));
                WebElement goToShopButton = goToShopButtonIterator.next();

                allOffers.add(new Offer(shopName, comments, warranty, price, goToShopButton, driver));

            }
        }
    }

    @Step
    public Offer defineOfferWithMinPrice() {

        initializeAllOffersList();

        Offer minOffer = allOffers.stream()
                .filter(offer -> offer.getCommentsNumber() > 10)
                .filter(offer -> {
                    int warrantyInMonth = 0;
                    try {
                        warrantyInMonth = Integer.parseInt(offer.getWarranty().replaceAll("[^0-9]", ""));
                    } catch (NumberFormatException e) {
                    }
                    return warrantyInMonth >= 6;
                })
                .min(Comparator.comparing(offer -> Double.valueOf(offer.getPrice())))
                .get();
        LOG.info("Offer with min price is defined: " + minOffer.toString());
        return minOffer;

    }

}
