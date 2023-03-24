package testCases;

import base.SetUpConnection;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPageDetailsPage;
import pages.SearchPage;

public class TC03_AddProductToCartTest extends SetUpConnection {

    @Test
    public void addProductToCart() {
        HomePage homePage = new HomePage(driver);
        homePage.checkAdDisplayed();
        homePage.openCategoriesPage();

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.selectCategory();
        categoryPage.selectCategoryType();
        categoryPage.clickTheFirstItem();

        ProductPageDetailsPage productPageDetailsPage = new ProductPageDetailsPage(driver);
        productPageDetailsPage.addProductToBag();
        productPageDetailsPage.assertThatItemAddedToBag();

    }
}
