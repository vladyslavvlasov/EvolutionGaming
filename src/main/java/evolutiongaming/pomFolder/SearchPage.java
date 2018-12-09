package evolutiongaming.pomFolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Vladyslav Vlasov on 07.12.2018.
 */
public class SearchPage extends BasePage {
    @FindBy(name = "sort")
    private WebElement sorter;

    @FindBy(name = "sid")
    private WebElement typeOfDeal;

    @FindBy(name = "search_region")
    private WebElement searchRegion;

    @FindBy(name = "txt")
    private WebElement searchField;

    @FindBy(name = "topt[8][min]")
    private WebElement minPrice;

    @FindBy(name = "topt[8][max]")
    private WebElement maxPrice;

    @FindBy(name = "btn")
    private WebElement submitBtn;

    @FindBy(xpath = "//div[@id='preload_txt' and contains(@style,'display: block')]")
    private WebElement activeDropdown;

    private By dropdownElements = new By.ByXPath("/div");


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void selectSearchRegion(String region) {
        Select regionSelect = new Select(searchRegion);
        regionSelect.selectByVisibleText(region);
    }

    public void setSearchField(String text) {
        searchField.sendKeys(text);
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOf(activeDropdown));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> dropdownOptions = activeDropdown.findElements(dropdownElements);
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(text)) {
                option.click();
                break;
            }
        }
    }

    public void setMinPrice(String price) {
        minPrice.clear();
        minPrice.sendKeys(price);
    }

    public void setMaxPrice(String price) {
        maxPrice.clear();
        maxPrice.sendKeys(price);
    }

    public SearchResultPage clickSubmitBtn() {
        submitBtn.click();
        return new SearchResultPage(driver);
    }
}
