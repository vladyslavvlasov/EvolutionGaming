package evolutiongaming.pomFolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Vladyslav Vlasov on 08.12.2018.
 */
public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "sid")
    private WebElement typeOfDeal;

    @FindBy(xpath = "//a[contains(text(),'Цена')]")
    private WebElement priceSortLinkRu;

    @FindBy(css = "a.a9a[href$='/search/']")
    private WebElement extendedSearch;

    @FindBy(className = "amopt")
    private List<WebElement> prices;

    @FindBy(id = "a_fav_sel")
    private WebElement addToMemo;

    @FindBy(xpath = "//tr[contains(@id,'tr_') and not(contains(@style,'display:none'))]")
    private List<WebElement> searchResultsList;

    public void selectTypeOfDeal(String optionValue) {
        Select typeOfDealSelect = new Select(typeOfDeal);
        typeOfDealSelect.selectByVisibleText(optionValue);
    }

    public void clickSortByPrice() {
        priceSortLinkRu.click();
    }

    public ArrayList<Integer> getListOfPrices() {
        ArrayList<Integer> listOfPrices = new ArrayList<>();
        for (WebElement price : prices) {
            listOfPrices.add(Integer.parseInt(price.getText().replace("€", "").trim()));
        }
        return listOfPrices;
    }

    public boolean isSortedByPrice(String order) {
        boolean flag = false;
        ArrayList<Integer> listOfPrices = getListOfPrices();
        if (order.equalsIgnoreCase("descending")) {
            for (int i = 0; i < listOfPrices.size() - 2; i++) {
                if (listOfPrices.get(i) < listOfPrices.get(i + 1)) {
                    return flag;
                }
            }
            flag = true;
        } else {
            for (int i = 0; i < listOfPrices.size() - 2; i++) {
                if (listOfPrices.get(i) > listOfPrices.get(i + 1)) {
                    return flag;
                }
            }
            flag = true;
        }
        return flag;
    }

    public boolean isTypeOfDealSelected(String dealName) {
        Select typeOfDealSelect = new Select(typeOfDeal);
        return dealName.equalsIgnoreCase(typeOfDealSelect.getFirstSelectedOption().getText());
    }

    public SearchPage clickExtendedSearch() {
        extendedSearch.click();
        return new SearchPage(driver);
    }

    public ArrayList<String> select3RandomProducts() {
        int randomNum1 = ThreadLocalRandom.current().nextInt(0, 11);
        int randomNum2 = ThreadLocalRandom.current().nextInt(11, 21);
        int randomNum3 = ThreadLocalRandom.current().nextInt(21, 30);
        ArrayList<String> idsList = new ArrayList<>();
        idsList.add(searchResultsList.get(randomNum1).getAttribute("id"));
        idsList.add(searchResultsList.get(randomNum2).getAttribute("id"));
        idsList.add(searchResultsList.get(randomNum3).getAttribute("id"));
        return idsList;
    }

    public void selectProductById(String id) {
        WebElement product = driver.findElement(By.id(id));
        product.findElement(By.cssSelector("input[type='checkbox']")).click();
    }

    public void selectProductsById(ArrayList<String> listOfIds) {
        for (String id : listOfIds) {
            selectProductById(id);
        }
    }

    public void clickAddSelectedToMemo() {
        addToMemo.click();
    }
}
