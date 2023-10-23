package mst.qa.page;

import mst.qa.drivermaanager.AndroidDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TodoPage {
    private static final String nameEditText = "//android.widget.TextView[@text='Name']/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='Input Name']";
    private static final String dateChoseButton = "//android.widget.TextView[@text='Date']/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup";
    private static final String typeChooserButton = "//android.widget.Button[@content-desc='Choose Type']";
    private static final String descEditText = "//android.widget.TextView[@text='Description']/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[@text='Input Description']";
    private static final String fileChooserButton = "//android.widget.TextView[@text='File']/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup";
    private static final String addTodoButton = "//android.widget.TextView[@text='Add Todo']/parent::android.widget.Button";

    //bottom sheet text type chooser
    private static final String primaryText = "//android.widget.TextView[@text='Primary']/parent::android.view.ViewGroup";
    private static final String secondaryText = "//android.widget.TextView[@text='Secondary']/parent::android.view.ViewGroup";
    private static final String otherText = "//android.widget.TextView[@text='Secondary']/parent::android.view.ViewGroup";

    //date navigator
    private static final String backMonth = "//android.view.ViewGroup[@resource-id='native.calendar.CHANGE_MONTH_LEFT_ARROW']";
    private static final String nextMonth = "//android.view.ViewGroup[@resource-id='native.calendar.CHANGE_MONTH_RIGHT_ARROW']";
    private static final String okButton = "//android.widget.TextView[@text='OK']/parent::android.widget.Button";


    private AndroidDriverManager androidDriverManager;
    private WebDriverWait waitElement;

    public TodoPage(AndroidDriverManager androidDriverManager){
        this.androidDriverManager = androidDriverManager;
        waitElement = new WebDriverWait(androidDriverManager.getDriver(), 10);
    }

    private WebElement getNameEditText(){
        return androidDriverManager.getDriver().findElement(By.xpath(nameEditText));
    }

    private WebElement getDateChooser(){
        return androidDriverManager.getDriver().findElement(By.xpath(dateChoseButton));
    }

    private WebElement getTypeChooser(){
        return androidDriverManager.getDriver().findElement(By.xpath(typeChooserButton));
    }

    private WebElement getDescriptionEditText(){
        return androidDriverManager.getDriver().findElement(By.xpath(descEditText));
    }

    private WebElement getFileChooserButton(){
        return androidDriverManager.getDriver().findElement(By.xpath(fileChooserButton));
    }

    private WebElement getAddToDoButton(){
        return androidDriverManager.getDriver().findElement(By.xpath(addTodoButton));
    }

    //secondary element
    private WebElement getPrimaryText(){
        return androidDriverManager.getDriver().findElement(By.xpath(primaryText));
    }

    private WebElement getSecondaryText(){
        return androidDriverManager.getDriver().findElement(By.xpath(secondaryText));
    }

    private WebElement getOtherText(){
        return androidDriverManager.getDriver().findElement(By.xpath(otherText));
    }

    private WebElement getBackMonth(){
        return androidDriverManager.getDriver().findElement(By.xpath(backMonth));
    }

    private WebElement getNextMonth(){
        return androidDriverManager.getDriver().findElement(By.xpath(nextMonth));
    }

    private WebElement getOKCalendar(){
        return androidDriverManager.getDriver().findElement(By.xpath(okButton));
    }

    public void performFillTodoInput(String name, Calendar calendar, String type, String desc, boolean isSeletImage){
        WebElement textName = androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Name']"));
        waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Name']")));
        getNameEditText().sendKeys(name);
        selectedDate(calendar);
        selectedType(type);
        getDescriptionEditText().sendKeys(desc);
        selectedImage(isSeletImage);
        getAddToDoButton().click();

    }

    private void selectedDate(Calendar calendar){
        if(calendar!=null){
            getDateChooser().click();
            Calendar currentDate = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            if(currentDate.get(Calendar.MONTH)>month){
                String findMonth ="";
                do{
                    getBackMonth().click();
                    findMonth = androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='HEADER_MONTH_NAME']")).getText();
                }while(!findMonth.contains(new SimpleDateFormat("MMMM").format(calendar.getTime())));
                androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+day+"']")).click();
                getOKCalendar().click();
            }else if (currentDate.get(Calendar.MONTH)<month){
                String findMonth ="";
                do{
                    getNextMonth().click();
                    findMonth = androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='HEADER_MONTH_NAME']")).getText();
                }while(!findMonth.contains(new SimpleDateFormat("MMMM").format(calendar.getTime())));
                androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+day+"']")).click();
                getOKCalendar().click();
            }else{
                androidDriverManager.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+day+"']")).click();
                getOKCalendar().click();
            }
        }
    }

    private void selectedType(String type){
        getTypeChooser().click();
        if(type.equals("Primary")) {
            getPrimaryText().click();
        }else if (type.equals("Secondary")){
            getSecondaryText().click();
        }else{
            getOtherText().click();
        }
    }

    private void selectedImage(boolean isSelectImage){
        if(isSelectImage) {
            getFileChooserButton().click();
            List<WebElement> listWebElements =  androidDriverManager.getDriver().findElements(By.xpath("//android.widget.ImageView[@resource-id='com.google.android.providers.media.module:id/icon_thumbnail']"));
            listWebElements.get(0).click();
        }
    }



}
