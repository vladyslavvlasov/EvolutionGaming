package evolutiongaming.pomFolder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Vladyslav Vlasov on 07.12.2018.
 */
public class Header extends BasePage {
    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".menu_lang a")
    private WebElement languageMenu;

    @FindBy(css = "a[href$='/search/']")
    private WebElement search;

    @FindBy(css = "a[href$='/favorites/']")
    private WebElement memo;

    public void clickLanguageMenu() {
        languageMenu.click();
    }

    public String getSelectedLanguage() {
        return languageMenu.getText();
    }

    public SearchPage clickSearch() {
        search.click();
        return new SearchPage(driver);
    }

    public MemoPage goToMemo() {
        memo.click();
        return new MemoPage(driver);
    }
}
