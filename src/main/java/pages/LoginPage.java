package pages;

import Utilities.Helper;
import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public LoginPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }
    Helper helper = new Helper();

    @AndroidFindBy(id = "com.zzkko:id/email_edt")
    private AndroidElement emailField;

    @AndroidFindBy(id = "com.zzkko:id/pwdEdtTxt")
    private AndroidElement passwordField;

    @AndroidFindBy(id = "com.zzkko:id/login_or_register_button")
    private AndroidElement registerBtn;

    @AndroidFindBy(id = "com.zzkko:id/title_success")
    private AndroidElement registrationTitle;

    @AndroidFindBy(id = "com.zzkko:id/btn_go_shopping")
    private AndroidElement confirmBtn;


    public void addCredentials() {
        String email = generateRandomText(7) + "@mail.com";
        String password = generateRandomPassword(9);
        clearField(emailField);
        clearField(passwordField);
        addText(emailField, email);
        addText(passwordField, password);
        helper.updateValueInPropertiesFile("RegistrationData","Email", email);
        helper.updateValueInPropertiesFile("RegistrationData","Password", password);
    }

    public void clickRegisterBtn() {
        click(registerBtn);
    }

    public void assertRegistrationIsSuccessful(){
        waitVisibilityOfElement(registrationTitle);
        Assert.assertTrue(registrationTitle.getText().equals("Registration success!"));
        click(confirmBtn);
    }
}
