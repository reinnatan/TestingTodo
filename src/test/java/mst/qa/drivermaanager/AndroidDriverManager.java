package mst.qa.drivermaanager;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class AndroidDriverManager {

    private AndroidDriver driver;

    public AndroidDriverManager createAndroidManager(){
        try{
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities());
            setupDriverTimeouts();
        }catch (MalformedURLException ux){
            throw new RuntimeException(ux);
        }
        return this;
    }

    public void quitDriver(){
        driver.quit();
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    private void setupDriverTimeouts(){
        getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    private DesiredCapabilities capabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "Android");
        capabilities.setCapability("appium:appPackage", "com.mstqa");
        capabilities.setCapability("appium:appActivity", "com.mstqa.MainActivity");
        capabilities.setCapability( "platformName", "Android");
        capabilities.setCapability("appium:udid", "RR8R801GPNZ");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:noReset", "false");
        capabilities.setCapability("appium:automationName","uiautomator2");

        return capabilities;
    }
}
