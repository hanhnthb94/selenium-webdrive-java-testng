package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Topic_19_New_Driver {
    WebDriver userDriver;
    WebDriver adminDriver;
    WebDriverWait explicitWait;
    String firstName = "Kevin", lastName = "Lamping", emailAddress = getEmailAddress();
    String companyName = "Selenium WebDriver", password = "123456";
    String day = "15", month = "November", year = "1950";

    @BeforeClass
    public void beforeClass() {
        // Khi 1 driver/ browser được new lên thì ko nhận setting nào từ user
        // Profile empty (ko extension/ plugin/ setting)
        // Có thể new 2 driver firefox khác nhau, chúng sẽ hoạt động riêng k lq đến nhau
        userDriver = new FirefoxDriver();
        userDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        adminDriver = new EdgeDriver();
        adminDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void TC_01_Basic_Form() {
        userDriver.get("https://demo.nopcommerce.com/");
        userDriver.findElement(By.cssSelector("a.ico-register")).click();
        userDriver.findElement(By.id("FirstName")).sendKeys(firstName);
        userDriver.findElement(By.id("LastName")).sendKeys(lastName);
        // Day dropdown
        Select dayDropdown = new Select(userDriver.findElement(By.name("DateOfBirthDay")));
        // Chon ngay
        dayDropdown.selectByVisibleText(day);
        // Verify dropdown nay la Single chu ko phai la Multiple
        Assert.assertFalse(dayDropdown.isMultiple());
        // Verify so luong itom trong dropdown nay la 32 item
        Assert.assertEquals(dayDropdown.getOptions().size(), 32);

        new Select(userDriver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(userDriver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        userDriver.findElement(By.id("Email")).sendKeys(emailAddress);
        userDriver.findElement(By.id("Company")).sendKeys(companyName);
        userDriver.findElement(By.id("Password")).sendKeys(password);
        userDriver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        userDriver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(2);
        Assert.assertEquals(userDriver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

        // Admin
        adminDriver.get("https://admin-demo.nopcommerce.com/");
        adminDriver.findElement(By.cssSelector("input#Email")).clear();
        adminDriver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");

        adminDriver.findElement(By.cssSelector("input#Password")).clear();
        adminDriver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        adminDriver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(3);

        // Check
        // ...

        // User
        userDriver.get("https://demo.nopcommerce.com/");

        // Login
        userDriver.findElement(By.cssSelector("a.ico-login")).click();
        userDriver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        userDriver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        userDriver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(3);
    }

    @AfterClass
    public void afterClass() {
        userDriver.quit();
        adminDriver.quit();
    }

    public void sleepInSeconds ( long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress () {
        // Ham lay ra email random
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.com";

    }

}