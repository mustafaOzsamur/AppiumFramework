package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ReusableMethods {
    static TouchAction action = new TouchAction(Driver.getAndroidDriver());

    public static void koordinatTiklama(int x, int y, int bekleme) throws InterruptedException {
        action.press(PointOption.point(x, y)).release().perform();
        Thread.sleep(bekleme);
    }


    public static void ekranAltKaydirma(int x1, int y1, int wait, int x2, int y2, int bekleme) throws InterruptedException {
        action.press(PointOption.point(x1, y1))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(wait)))
                .moveTo(PointOption.point(x2, y2))
                .release()
                .perform();

        Thread.sleep(bekleme);

    }

    public static void scrollWithUiScrollable(String elementText) {
        AndroidDriver driver = (AndroidDriver) Driver.getAndroidDriver();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))");
        driver.findElementByXPath("//*[@text='" + elementText + "']").click();
    }
    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhssmm").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot)Driver.getAndroidDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

}
