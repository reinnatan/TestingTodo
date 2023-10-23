package mst.qa.page;

import mst.qa.drivermaanager.AndroidDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    private static final String addToDoButton = "//android.widget.TextView[@text='Add Todo']/parent::android.widget.Button";
    private static final String listChildTodo = "//android.widget.TextView[@text='Add Todo']/parent::android.widget.Button/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup";
    private static final String logoutButton = "//android.widget.TextView[@text='Home']/preceding-sibling::android.view.ViewGroup/android.widget.TextView";
    private AndroidDriverManager androidDriverManager;
    private WebDriverWait waitElement;

    public HomePage(AndroidDriverManager androidDriverManager){
        this.androidDriverManager = androidDriverManager;
        waitElement = new WebDriverWait(androidDriverManager.getDriver(), 5);
    }

    private WebElement getToDoButton(){
        return androidDriverManager.getDriver().findElement(By.xpath(addToDoButton));
    }

    private WebElement getLogoutButton(){
        return androidDriverManager.getDriver().findElement(By.xpath(logoutButton));
    }

    private List<WebElement> getLisToDo(){
        return androidDriverManager.getDriver().findElements(By.xpath(listChildTodo));
    }

    public void performLogout(){
        getLogoutButton().click();
    }

    public void performAddTodo(){
        getToDoButton().click();
    }

    public void checkVisibilityTodo(){
        WebElement textName = androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Add Todo']"));
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Add Todo']")));
    }

    public void clickBackButton(){
        androidDriverManager.getDriver().navigate().back();
    }

    public void clickDetailBeforeElement(){
        List<WebElement> listTodo = getLisToDo();
        if(listTodo.size()-2>=0) {
            listTodo.get(listTodo.size()-2).click();
        }
    }

    public void validateListDetailTodo(){
        String detailHeaderTest = androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Detail Todo']")).getText();
        boolean nameDisplay =  androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Name']")).isDisplayed();
        boolean dateDisplay =  androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Date']")).isDisplayed();
        boolean typeDisplay =  androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Type']")).isDisplayed();
        boolean descDisplay =  androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Description']")).isDisplayed();
        Assert.assertEquals("Detail Todo", detailHeaderTest);
        Assert.assertTrue(nameDisplay);
        Assert.assertTrue(dateDisplay);
        Assert.assertTrue(typeDisplay);
        Assert.assertTrue(descDisplay);
    }
}
