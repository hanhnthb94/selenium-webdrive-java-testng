package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_08_DataProvider {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    By emailTextbox = By.xpath("//*[@id='email']");
    By passwordTextbox = By.xpath("//*[@id='pass']");
    By loginButton = By.xpath("//*[@id='send2']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test(dataProvider = "loginData")
    public void TC_01_LoginToSystem(String username, String password, int age)  {
        // Chuc nang Login
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        System.out.println("Age = " + age);
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));

        // Action nay cho tat ca user giong nhau
        // Mua san pham - thanh toan - verify - Logout

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @DataProvider(name = "loginData")
    public Object[][] UserAndPasswordData() {
        return new Object[][]{
                {"selenium_11_01@gmail.com", "111111", 15},
                {"selenium_11_02@gmail.com", "111111", 20},
                {"selenium_11_03@gmail.com", "111111", 30}};
    }
    @DataProvider(name = "register")
    public Object[][] Register() {
        return new Object[][]{
                {"selenium_11_01@gmail.com"},
                {"selenium_11_02@gmail.com"},
                {"selenium_11_03@gmail.com"}};
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}