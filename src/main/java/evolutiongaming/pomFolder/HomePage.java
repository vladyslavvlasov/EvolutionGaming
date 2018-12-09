package evolutiongaming.pomFolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Vladyslav Vlasov on 07.12.2018.
 */
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final String HOME_URL = "https://www.ss.com/";

    public void openPage() {
        driver.get(HOME_URL);
    }

    public void goToSection(String sectionTitle) {
        WebElement sectionLink = driver.findElement(By.cssSelector(".main_head .a1[title='" + sectionTitle + "']"));
        sectionLink.click();
    }
}
