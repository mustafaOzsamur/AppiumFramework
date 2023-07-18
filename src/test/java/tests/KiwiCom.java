package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KiwiPage;
import utils.Driver;

public class KiwiCom {

    AndroidDriver<AndroidElement> driver= Driver.getAndroidDriver();
    KiwiPage kiwiPage=new KiwiPage();
    TouchAction action=new TouchAction(driver);
    @Test
    public void kiwiTest() throws InterruptedException {
// uygulamanin yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.skypicker.main"));
// uygulamanin basariyla acildigi dogrulanir
       Assert.assertTrue(kiwiPage.quest.isDisplayed());
// misafir olarak devam et e tiklanir
        kiwiPage.quest.click();

// ardinda gelecek olan 3 adimada yesil butona basilarak devam edilir
        //538 1697

        kiwiPage.ucAdimGecme(0,3,538,1686,1000);
// Trip type,one way olarak secilir

        action.press(PointOption.point(290,627)).release().perform();
        Thread.sleep(1000);
        action.press(PointOption.point(552,1448)).release().perform();
        Thread.sleep(1000);
        action.press(PointOption.point(502,780)).release().perform();
        Thread.sleep(1000);
        action.press(PointOption.point(1016,135)).release().perform();

// kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
        if (driver.isKeyboardShown()){
        driver.getKeyboard().pressKey("Kayseri");
        }else {
            kiwiPage.departureBox.sendKeys("Atina");
        }
        Thread.sleep(1000);
        action.press(PointOption.point(493,294)).release().perform();
        Thread.sleep(1000);
        kiwiPage.chooseButton.click();

        Thread.sleep(1000);
        action.press(PointOption.point(431,936)).release().perform();

// kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
// varis ulkesi secenegine tiklanir ve gidilecek ulke girilir

        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("Singapur");
        }else {
            kiwiPage.departureBox.sendKeys("Papua Yeni Gine");
        }


        action.press(PointOption.point(493,294)).release().perform();
        Thread.sleep(1000);

        kiwiPage.chooseButton.click();

       kiwiPage.ekranAltKaydirma(487,1332,500,527,230,1000);


// gidis tarihi mayis ayinin 21 i olarak secilir ve set date e tiklanir
// search butonuna tiklanir
// en  ucuz ve aktarmasiz filtrelemeleri yapilir
// gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
    }

}
