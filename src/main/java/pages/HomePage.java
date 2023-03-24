package pages;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {
    public HomePage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }


    @AndroidFindBy(accessibility = "Me")
    private AndroidElement profileBtn;

    @AndroidFindBy(accessibility = "Search")
    private AndroidElement searchBtn;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Wishlist']/android.widget.ImageView")
    private AndroidElement wishlistBtn;

    @AndroidFindBy(className = "android.widget.ImageView")
    private AndroidElement ad;

    @AndroidFindBy(accessibility = "Close")
    private AndroidElement closeAdBtn;

    @AndroidFindBy(accessibility = "Category")
    private AndroidElement categoryBtn;

    public void openProfilePage() {
        click(profileBtn);
    }

    public void clickSearchIcon() {
        click(searchBtn);
    }

    public void openCategoriesPage() {
        click(categoryBtn);
    }

    public void checkAdDisplayed(){
        if (ad.isDisplayed()){
            click(closeAdBtn);
        }
    }
}
