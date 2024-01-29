package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class Topic_27_Wait_07_Explicit_03 {
    WebDriver driver;
    WebDriverWait explicitWait; // buoc nay la buoc khai bao, chua khoi tao
    String projectPath = System.getProperty("user.dir");
    String oneName = "one.jpg"; String twoName = "two.jpg"; String threeName = "three.jpg";
    String oneFilePath = projectPath + File.separator + "uploadFiles" + File.separator + oneName;
    String twoFilePath = projectPath + File.separator + "uploadFiles" + File.separator + twoName;
    String threeFilePath = projectPath + File.separator + "uploadFiles" + File.separator + threeName;

    @BeforeClass // Precondition - khoi tao du lieu/ data test/ page class/ varible
    public void beforeClass() {
        // 1
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        // Khoi tao 1 explicit wait co tong thoi gia la 10s - polling la 0.5s mac dinh
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Khoi tao 1 explicit wait co tong thoi gia la 10s - polling la 0.3s tu set
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
    }

    @Test
    public void TC_01_AjaxLoading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        // Khi loading xong, element co the thay doi trang thai, neu trong truong hop nay thi k nen chir find 1 lan
        Assert.assertEquals( driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
        driver.findElement(By.xpath("//a[text()='29']")).click();
        // Wait cho icon loding bien mat trong vong x giay
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
        Assert.assertEquals( driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Monday, January 29, 2024");
    }
    @Test
    public void TC_02_UploadFile() {
        driver.get("https://gofile.io/?t=uploadFiles");
        // Wait + verify
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
        // Wait + click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();
        // Wait + verify
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border"))));
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(oneFilePath + "\n" + twoFilePath + "\n" + threeFilePath);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border"))));
        // Wait cho progress Bar bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();
        // Verify button Play co tai tung hinh dc upload
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='" + oneName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='" + twoName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='" + threeName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        // Verify button Download co tai tung hinh dc upload
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='" + oneName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='" + twoName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='" + threeName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}