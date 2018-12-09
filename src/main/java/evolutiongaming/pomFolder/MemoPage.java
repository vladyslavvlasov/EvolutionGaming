package evolutiongaming.pomFolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Vladyslav Vlasov on 08.12.2018.
 */
public class MemoPage extends BasePage {

    public MemoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDisplayedById(String id){
        return driver.findElement(By.id(id)).isDisplayed();
    }
}
