package evolutiongaming;

import evolutiongaming.pomFolder.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by Vladyslav Vlasov on 07.12.2018.
 */
public class SearchAddToMemoTest extends BaseTest {

    HomePage homePage;
    Header header;
    SearchPage searchPage;
    SearchResultPage searchResultPage;
    MemoPage memoPage;
    protected final String HOME_PAGE_URL = "https://www.ss.com/";
    protected final String ELECTRONICS_URL = "https://www.ss.com/ru/electronics/";
    protected final String DEFAULT_RU_LANGUAGE_SELECTOR = "RU";
    protected final String LV_LANGUAGE_SELECTOR = "LV";
    protected final String ELECTRONICS_SECTION_TITLE = "Объявления Электротехника";
    protected final String SEARCH_URL_SUFFIX = "/search/";
    protected final String SEARCH_RESULT_SUFFIX = "/search-result/";
    protected final String SEARCH_WORD = "Компьютер";
    protected final String REGION_TO_SEARCH = "Рига";
    protected final String SELL_DEAL_RU = "Продажа";
    protected final String ASCENDING_ORDER = "ascending";

    @Test
    public void searchAndAddToMemoTest() {
        homePage = new HomePage(driver);
        homePage.openPage();
        Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, "Expected to be on " + HOME_PAGE_URL);
        header = new Header(driver);
        Assert.assertEquals(header.getSelectedLanguage(), DEFAULT_RU_LANGUAGE_SELECTOR, "Expected language selector to be " + DEFAULT_RU_LANGUAGE_SELECTOR);
        header.clickLanguageMenu();
        Assert.assertEquals(header.getSelectedLanguage(), LV_LANGUAGE_SELECTOR, "Expected language selector to be " + LV_LANGUAGE_SELECTOR);
        homePage.goToSection(ELECTRONICS_SECTION_TITLE);
        Assert.assertEquals(driver.getCurrentUrl(), ELECTRONICS_URL, "Expected to be on " + ELECTRONICS_URL);
        searchPage = header.clickSearch();
        Assert.assertTrue(driver.getCurrentUrl().endsWith(SEARCH_URL_SUFFIX), "Expected to be redirected to search page");
        searchPage.setSearchField(SEARCH_WORD);
        searchPage.selectSearchRegion(REGION_TO_SEARCH);
        searchPage.setMinPrice("100");
        searchResultPage = searchPage.clickSubmitBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(SEARCH_RESULT_SUFFIX), "Expected to be redirected to search result page");
        searchResultPage.clickSortByPrice();
        Assert.assertTrue(searchResultPage.isSortedByPrice(ASCENDING_ORDER));
        searchResultPage.selectTypeOfDeal(SELL_DEAL_RU);
        Assert.assertTrue(searchResultPage.isTypeOfDealSelected(SELL_DEAL_RU));
        searchPage = searchResultPage.clickExtendedSearch();
        Assert.assertTrue(driver.getCurrentUrl().endsWith(SEARCH_URL_SUFFIX), "Expected to be redirected to search page");
        searchPage.setMinPrice("160");
        searchPage.setMaxPrice("300");
        searchResultPage = searchPage.clickSubmitBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains(SEARCH_RESULT_SUFFIX), "Expected to be redirected to search result page");
        ArrayList<String> randomIds = searchResultPage.select3RandomProducts();
        searchResultPage.selectProductsById(randomIds);
        searchResultPage.clickAddSelectedToMemo();
        memoPage = header.goToMemo();
        for (String id : randomIds) {
            Assert.assertTrue(memoPage.isProductDisplayedById(id), "Expected product with id " + id + " to be displayed on memo page");
        }
    }
}
