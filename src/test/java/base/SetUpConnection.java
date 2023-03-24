package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetUpConnection extends StartAppiumServer {

    public static AndroidDriver<AndroidElement> driver;

    /*
     * check if there is any process run on port 4723 if exist stop and run appium if not run appium directly
     *  */
    @BeforeSuite
    public void startConnection() {
        int port = 4723;
        if (!checkIfServerIsRunnning(port)) {
            startServer();
        } else {
            stopServer();
            startServer();
        }
    }


    /*
     * lunch the emulator
     * you can change the emulator name in propertiesFile/emulatorDetails
     */
    @BeforeClass
    public void startDriver() {
        runCommand("emulator -avd " + helper.getValuesFromPropertiesFile("emulatorDetails", "EmulatorName"));
        try {
            driver = new AndroidDriver(new URL(startServer()), capabilities());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * close the app
     */
    @AfterClass
    public void closeApp() {
        driver.closeApp();
        //driver.removeApp("");
    }

    /*
     * stop appium server
     */
    @AfterSuite
    public void stopServer() {

        service.stop();
    }
}
