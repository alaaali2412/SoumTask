package pages;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

public class ProfilePage extends BasePage {
    public ProfilePage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zzkko:id/me_login_btn")
    private AndroidElement loginBtn;

    @AndroidFindBy(id = "com.zzkko:id/tv_register_coupon")
    private AndroidElement registerCouponBtn;

    @AndroidFindBy(accessibility = "Setting")
    private AndroidElement settingBtn;

    @AndroidFindBy(id = "com.zzkko:id/setting_log_out_layout")
    private AndroidElement signOutBtn;

    public void assertProfileScreenOpened() {
        Assert.assertTrue(loginBtn.isDisplayed());
    }

    public void clickLoginBtn() {
        waitToLoad(30);
        click(loginBtn);
    }

    public void clickRegister() {
        click(registerCouponBtn);
    }

    public void clickSettingBtn() {
        click(settingBtn);
    }

    public void clickSignOutBtn() {
        click(signOutBtn);
    }


}
