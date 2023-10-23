package mst.qa.page;

import io.appium.java_client.android.AndroidElement;
import mst.qa.drivermaanager.AndroidDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private static final String textUserName = "//android.widget.TextView[@text='Username']/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@password='false']";
    private static final String textPassword = "//android.widget.TextView[@text='Password']/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@password='true']";
    private static final String loginButton = "//android.widget.EditText/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.Button/android.widget.TextView[@text='Login']";
    private AndroidDriverManager androidDriverManager;

    public LoginPage(AndroidDriverManager androidDriverManager){
        this.androidDriverManager = androidDriverManager;
    }

    private WebElement geTextUserName(){
        return androidDriverManager.getDriver().findElement(By.xpath(textUserName));
    }

    private WebElement getTextPassword(){
        return androidDriverManager.getDriver().findElement(By.xpath(textPassword));
    }

    private WebElement getLoginButton(){
        return androidDriverManager.getDriver().findElement(By.xpath(loginButton));
    }

    public void performLogin(String username, String password){
        geTextUserName().sendKeys(username);
        getTextPassword().sendKeys(password);
        getLoginButton().click();
    }
}
