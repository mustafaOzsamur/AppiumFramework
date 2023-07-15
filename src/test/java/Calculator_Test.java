import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Calculator_Test {
    // Android cihazlar icin kullanilmasi gereken driver
    AndroidDriver<AndroidElement> driver;

    // Hem android hemde ios cihazlar icin kullanilir
// AppiumDriver<AndroidElement> driver2;

    // ios icin kullanilir (Android cihazlar icinde kullanilabilir)
//AppiumDriver<MobileElement> driver3;


    @Test
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        // Eger ki kullandigimiz android sürümü 6 veya üstü ise "UiAutomator2" kullanilir
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //app capabilityType bir uygulamayi yüklemek istedigimizde indirdigimiz apk dosyasinin path uzantisini vererek
        // o uygulamayi yüklemek icin kullandigimiz DesierdCapability
       // capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\musta\\IdeaProjects\\AppiumProject\\Apps\\Calculator_8.4.1 (520193683)_Apkpure.apk");
        capabilities.setCapability("appPackage","com.google.android.calculator");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


      //  driver.activateApp("com.google.android.calculator");

        // uygulamanin yuklendigini dogrular(isInstalled)
        Assert.assertTrue(driver.isAppInstalled("com.google.android.calculator"));

        // uygulamanin acildigini dogrular
        AndroidElement acButonu=driver.findElementByAccessibilityId("clear");
        Assert.assertTrue(acButonu.isDisplayed());

        // carpma,bolme,toplama,cikarma islemleri yaparak sonuclari dogrular
        driver.findElementByAccessibilityId("8").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("2").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("equals").click();
        String sonuc=driver.findElementById("com.google.android.calculator:id/result_final").getText();
        Assert.assertEquals(Integer.parseInt(sonuc),1600);



        // AC butonuna tiklayarak ana ekrani temizler




    }
}
