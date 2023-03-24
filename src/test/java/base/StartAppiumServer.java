package base;

import Utilities.Helper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class StartAppiumServer {
    Helper helper = new Helper();
    DesiredCapabilities capabilities;
    AndroidDriver<AndroidElement> driver;
    AppiumServiceBuilder builder;
    AppiumDriverLocalService service;

    /*
     * install the apk
     * setup the desired capabilities for android emulator
     */

    public DesiredCapabilities capabilities() {
        File mainFolder = new File("App");
        File ApkPath = new File(mainFolder, helper.getValuesFromPropertiesFile("emulatorDetails", "AppName"));
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, helper.getValuesFromPropertiesFile("emulatorDetails","EmulatorName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(MobileCapabilityType.APP, ApkPath.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        capabilities.setCapability(MobileCapabilityType.EVENT_TIMINGS, true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("autoDismissAlerts", true);
        return capabilities;
    }


    /*
     *
     * setup the appium server connection
     */
    public String startServer() {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withCapabilities(capabilities());
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        String appiumServiceUrl = service.getUrl().toString();
        return appiumServiceUrl;
    }

    public boolean checkIfServerIsRunnning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public void runCommand (String command){
        try{ Runtime.getRuntime().exec(command);}
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
