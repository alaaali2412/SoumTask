package testCases;

import base.SetUpConnection;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class TC01_RegisterNewUserTest extends SetUpConnection {

    @Test
    public void registerNewUser(){
        HomePage homePage = new HomePage(driver);
        homePage.checkAdDisplayed();
        homePage.openProfilePage();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.assertProfileScreenOpened();
        profilePage.clickRegister();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.addCredentials();
        loginPage.clickRegisterBtn();
        loginPage.assertRegistrationIsSuccessful();

    }
}
