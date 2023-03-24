package base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.TestException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static base.BasePage.Direction.*;


public class BasePage {
    protected AndroidDriver<AndroidElement> driver;
    Actions action;

    protected BasePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
    }

    protected void addText(AndroidElement element, String word) {
        waitVisibilityOfElement(element);
        waitUntilElementIsClickable(element);
        try {
            element.clear();
            element.sendKeys(word);
        } catch (Exception e) {
            throw new TestException(String.format("ERROR: Cannot add text in the following element: [%s]", element));
        }
    }

    protected void clearField(AndroidElement field) {
        waitVisibilityOfElement(field);
        waitUntilElementIsClickable(field);
        try {
            field.clear();
        } catch (Exception e) {
            throw new TestException(String.format("ERROR: Cannot clear the text in the following field: [%s]", field));
        }
        field.clear();
    }


    protected void waitVisibilityOfElement(AndroidElement element) {
        try {
            Wait<AndroidDriver<AndroidElement>> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("ERROR: The following element is not visible: [%s]!",
                    element));
        }
    }

    protected void waitUntilElementIsClickable(AndroidElement element) {
        try {
            Wait<AndroidDriver<AndroidElement>> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("ERROR: The following element is not visible: [%s]!",
                    element));
        }
    }


    protected void waitToLoad(int secs) {
        driver.manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);
    }


    protected void click(AndroidElement element) {
        waitVisibilityOfElement(element);
        waitUntilElementIsClickable(element);
        try {
            action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
        } catch (Exception e) {
            throw new TestException(String.format("ERROR: The following element is not clickable: [%s]", element));
        }
    }

    protected void clickEnterBtn() {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void swipe(Direction direction) {
        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;
        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (direction) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width /2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + direction + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }


    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public static String generateRandomText(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String randomString = sb.toString();
        return randomString;
    }

    protected static String generateRandomPassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];
        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));
        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return String.valueOf(password);
    }


    protected String splitText(String valueToSplit, String Regex){
        String[] parts = valueToSplit.split(Regex);
        return parts[1];
    }

}
