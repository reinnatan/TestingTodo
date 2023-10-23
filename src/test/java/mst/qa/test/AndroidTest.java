package mst.qa.test;

import mst.qa.page.HomePage;
import mst.qa.page.LoginPage;
import mst.qa.page.TodoPage;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

public class AndroidTest extends BaseTest{
    private static final String username = "mstqa";
    private static final String password = "P@ssw0rd";

    @Test(priority = 0)
    public void LoginLogout(){
        LoginPage loginPage = new LoginPage(getAndroidDriverManager());
        HomePage homePage = new HomePage(getAndroidDriverManager());
        loginPage.performLogin(username, password);
        homePage.performLogout();
    }

    @Test(priority = 1)
    public void AddToDo(){
        LoginPage loginPage = new LoginPage(getAndroidDriverManager());
        loginPage.performLogin(username, password);
        HomePage homePage = new HomePage(getAndroidDriverManager());
        TodoPage todoPage = new TodoPage(getAndroidDriverManager());
        homePage.performAddTodo();
        todoPage.performFillTodoInput("MST QA Test", null, "Primary", "Testing", false);
    }

    @Test(priority = 2)
    public void AddToDoSecondary(){
        HomePage homePage = new HomePage(getAndroidDriverManager());
        TodoPage todoPage = new TodoPage(getAndroidDriverManager());
        homePage.checkVisibilityTodo();
        homePage.performAddTodo();
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(2023, 8, 3);
        todoPage.performFillTodoInput("MST QA Test", selectedDate , "Secondary", "Testing", false);
    }

    @Test(priority = 3)
    public void AddToDoOther(){
        HomePage homePage = new HomePage(getAndroidDriverManager());
        TodoPage todoPage = new TodoPage(getAndroidDriverManager());
        homePage.checkVisibilityTodo();
        homePage.performAddTodo();
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(2023, 11, 2);
        todoPage.performFillTodoInput("MST QA Test", selectedDate , "Other", "Testing 4", true);
    }

    @Test(priority = 5)
    public void AddToDoOtherDiffUpload(){
        HomePage homePage = new HomePage(getAndroidDriverManager());
        TodoPage todoPage = new TodoPage(getAndroidDriverManager());
        homePage.checkVisibilityTodo();
        homePage.performAddTodo();
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(2023, 10, 1);
        todoPage.performFillTodoInput("MST QA Test", selectedDate , "Other", "Testing", true);
    }

    @Test(priority = 6)
    public void AddToDoOtherTapDetailBefore() throws InterruptedException {
        HomePage homePage = new HomePage(getAndroidDriverManager());
        TodoPage todoPage = new TodoPage(getAndroidDriverManager());
        homePage.checkVisibilityTodo();
        homePage.performAddTodo();
        todoPage.performFillTodoInput("MST QA Test", null, "Primary", "Testing", false);

        //add second data
        homePage.checkVisibilityTodo();
        homePage.performAddTodo();
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(2023, 10, 1);
        todoPage.performFillTodoInput("MST QA Test", selectedDate , "Other", "Testing", true);

        //prepare for click view detail
        homePage.checkVisibilityTodo();
        homePage.clickDetailBeforeElement();
        homePage.clickBackButton();
    }

    @Test(priority = 7)
    public void ValidateAddToDoOtherTapDetailBefore() {
        HomePage homePage = new HomePage(getAndroidDriverManager());
        TodoPage todoPage = new TodoPage(getAndroidDriverManager());
        homePage.checkVisibilityTodo();
        homePage.performAddTodo();
        todoPage.performFillTodoInput("MST QA Test", null, "Primary", "Testing", false);

        //add second data
        homePage.checkVisibilityTodo();
        homePage.performAddTodo();
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(2023, 10, 1);
        todoPage.performFillTodoInput("MST QA Test", selectedDate , "Other", "Testing", true);

        //prepare for click view detail
        homePage.checkVisibilityTodo();
        homePage.clickDetailBeforeElement();
        homePage.validateListDetailTodo();
    }


}
