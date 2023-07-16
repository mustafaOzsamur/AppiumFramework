package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ArabamComTest {

    AndroidDriver<AndroidElement> driver;


// yakit tipini secelim
// vites tipini secelim
// Versiyon secimi yapalim
// aracin km bilgilerini girelim
// aracin rengini secelim
// opsiyel donanim (varsa) seecelim
// degisen bilgisi ekleyerek tramer kaydi belirtelim
// aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
// uygulamayi kapatalim

    @BeforeTest
    public void arabamTestSetUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        // Eger ki kullandigimiz android sürümü 6 veya üstü ise "UiAutomator2" kullanilir
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //app capabilityType bir uygulamayi yüklemek istedigimizde indirdigimiz apk dosyasinin path uzantisini vererek
        // o uygulamayi yüklemek icin kullandigimiz DesierdCapability
        // capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\musta\\IdeaProjects\\AppiumProject\\Apps\\Calculator_8.4.1 (520193683)_Apkpure.apk");
        capabilities.setCapability("appPackage", "com.dogan.arabam");
        capabilities.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void arabamTest() throws InterruptedException {
        // Basarili bir sekilde yüklendi mi?
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        //Uygulama basarili bir sekilde acildi mi?
        AndroidElement arabamLogo=driver.findElementById("com.dogan.arabam:id/ivArabamLogo");
        Assert.assertTrue(arabamLogo.isDisplayed());

        // Arabam kac para bolumune tiklayalim
        AndroidElement arabamKacPara=driver.findElementByXPath("//*[@text='Arabam kaç para?']");
        arabamKacPara.click();

        // Aracimin fiyatini merak ediyorum bolumunetiklayalim
        driver.findElementByXPath("//*[@text='Aracımın fiyatını merak ediyorum']").click();
        driver.findElementById("com.dogan.arabam:id/et_email").sendKeys("ihsanyakin5@gmail.com");
        driver.findElementById("com.dogan.arabam:id/et_password").sendKeys("ihsanyakin5");
        driver.findElement(By.xpath("(//*[@text='Giriş Yap'])[2]")).click();
        driver.findElement(By.xpath("//*[@text='Aracımın fiyatını merak ediyorum']")).click();


        // Wolkswagen markasini secelim
        TouchAction action=new TouchAction(driver);
        action.press(PointOption.point(543,1900))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(521,1)).release().perform();
       /*
       Eger ki daha önceden kaydirma islemi gerceklestirmissek tam tersi hareketini gerceklestirmek icin
       yazdigimiz koordinat degerlerini tam tersi olacak sekilde yazmak o islemin zittini gerceklestirir.

       Thread.sleep(2000);

       action.press(PointOption.point(537,100))
              .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
               .moveTo(PointOption.point(543,1733)).release().perform();

        */

        driver.findElement(By.xpath("//*[@text='Volkswagen']")).click();

        // Yil secimi
        driver.findElement(By.xpath("//*[@text='2018']")).click();
        // Model Secimi
        driver.findElement(By.xpath("//*[@text='Passat']")).click();
        //Gövde Tip Secimi
        driver.findElement(By.xpath("//*[@text='Sedan']")).click();

        //Yakit Secimi
        driver.findElement(By.xpath("//*[@text='Benzin']")).click();

        //Vites Tipi
        driver.findElement(By.xpath("//*[@text='Yarı Otomatik']")).click();

        //Version Secimi

        // 906,1743
        // X,Y koordinatlari kullanarak element tiklanabilir
        Thread.sleep(1000);
        action.press(PointOption.point(906,1743)).release().perform();

        //Km gir
        // Eger klavye aktifse (GORUNUYORSA) Bu sekilde locate almadan veri girebiliriz
        //   driver.getKeyboard().pressKey("190000");

        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("50000");
        }
        else {
            driver.findElementById("com.dogan.arabam:id/et_km").sendKeys("50000");
        }

        driver.findElementById("com.dogan.arabam:id/btn_price_prediction_submit").click();

        //renk sec
        driver.findElement(By.xpath("//*[@text='Beyaz']")).click();
        driver.findElement(By.xpath("//*[@text='Devam']")).click();

        //tremer kaydi
        AndroidElement kaput=driver.findElementById("com.dogan.arabam:id/iv_B01001");
        kaput.click();

        driver.findElement(By.xpath("(//*[@text='Boyalı'])[2]")).click();
        Thread.sleep(1000);

        driver.findElementById("com.dogan.arabam:id/btn_next").click();

        // Tramer kaydi yok
        driver.findElementById("com.dogan.arabam:id/rbHasNoTramerEntry").click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@text='Devam']")).click();



        // aracinizin degerini 500.000 den fazla oldugunu test edin

        String avaragePrice=driver.findElementById("com.dogan.arabam:id/tvAveragePrice").getText();
        String lastPrice=avaragePrice.replaceAll("\\D","");

        int lastPriceInt=Integer.parseInt(lastPrice);

        Assert.assertTrue(lastPriceInt>500000);

        //uygulamayai kapatin

        driver.closeApp();

    }

}
