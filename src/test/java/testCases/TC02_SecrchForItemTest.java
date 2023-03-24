package testCases;

import base.SetUpConnection;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;

public class TC02_SecrchForItemTest extends SetUpConnection {

    @Test
    public void addProductToWishlist() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.checkAdDisplayed();
        homePage.clickSearchIcon();

        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchAnItem();
        searchPage.clickFilterButton();
        searchPage.filterWithSize();
        searchPage.clickApplyFilter();
        searchPage.navigateItems();
    }
}
