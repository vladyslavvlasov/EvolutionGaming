package evolutiongaming;

import evolutiongaming.pomFolder.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Vladyslav Vlasov on 07.12.2018.
 */
public class BaseTest {
    protected WebDriver driver;
    BasePage basePage;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/utils/chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
