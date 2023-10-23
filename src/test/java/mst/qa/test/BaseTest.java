package mst.qa.test;

import mst.qa.drivermaanager.AndroidDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private AndroidDriverManager androidDriverManager;

    @BeforeClass(alwaysRun = true)
    public void testSetup(){
        androidDriverManager = new AndroidDriverManager();
        androidDriverManager.createAndroidManager();
    }

    @AfterClass
    public void tearDown(){
        androidDriverManager.quitDriver();
    }

    public AndroidDriverManager getAndroidDriverManager() {
        return androidDriverManager;
    }
}
