package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllCurrency;
import utils.Driver;
import utils.ReusableMethods;

import java.io.File;
import java.io.IOException;

public class allCurrency {

    AllCurrency allCurrency=new AllCurrency();
AndroidDriver<AndroidElement> driver= Driver.getAndroidDriver();
    @Test
    public void test01() throws InterruptedException, IOException {
// all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));

// uygulamanin acildigi dogrulanir
        Assert.assertTrue(allCurrency.currencyText.isDisplayed());

// cevirmek istedigimiz para birimi zloty olarak secilir
        ReusableMethods.koordinatTiklama(396,344,100);
        ReusableMethods.scrollWithUiScrollable("PLN");

// cevirelecek olan para birimi Tl olarak secilir
        ReusableMethods.koordinatTiklama(499,487,100);
        ReusableMethods.scrollWithUiScrollable("TRY");
        allCurrency.bir.click();
        allCurrency.sifir.click();
        allCurrency.sifir.click();
        allCurrency.sifir.click();
// cevrilen tutar screenShot olarak kaydedilir
       File fileSS =driver.getScreenshotAs(OutputType.FILE); //ScreenShot i cekecek olan kisim
        // FileUtils classindan bir kopya olusturarak gormek istedigimiz
        FileUtils.copyFile(fileSS,new File("zlotyToTl.png"));
        ReusableMethods.getScreenshot("zlotyToTl");
// Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
        String finalExchange=allCurrency.sonucDegeri.getText();
        String handyNummar="56456626262";
        driver.sendSMS(handyNummar,finalExchange);
// bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir
    }
}
