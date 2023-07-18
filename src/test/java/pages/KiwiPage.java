package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.time.Duration;

public class KiwiPage {
   TouchAction action=new TouchAction<>(Driver.getAndroidDriver());
    public KiwiPage(){
        PageFactory.initElements((WebDriver) Driver.getAndroidDriver(),this);
    }

    @FindBy(xpath = "//*[@text='Continue as a guest']")
    public WebElement quest;

    @FindBy(xpath = "//*[@class='android.widget.EditText']")
    public WebElement departureBox;

    @FindBy(xpath = "//*[@text='Choose']")
    public WebElement chooseButton;

    public void ucAdimGecme(int iBaslangic, int iBitis, int xKoordinat, int yKootdinat, int bekleme) throws InterruptedException {
        for (int i = iBaslangic; i < iBitis ; i++) {
            action.press(PointOption.point(xKoordinat,yKootdinat)).release().perform();
            Thread.sleep(bekleme);
        }
    }


    public void koordinatTiklama(int x, int y, int bekleme ) throws InterruptedException {
        action.press(PointOption.point(x,y)).release().perform();
        Thread.sleep(bekleme);
    }

    public void ekranAltKaydirma(int x1,int y1,int wait,int x2,int y2,int bekleme) throws InterruptedException {
        action.press(PointOption.point(x1,y1))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(wait)))
                .moveTo(PointOption.point(x2,y2))
                .release()
                .perform();

        Thread.sleep(bekleme);

    }
}
