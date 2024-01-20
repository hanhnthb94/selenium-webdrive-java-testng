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

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element_Visible() {
        //Điều kiện 1 - Element có xuất hiện ở trên UI và có trong cây HTML
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dam@gmail.com");
        sleepInSeconds(2);
        // Tai thoi diem/step nay thi confirm email textbox dang visible/display
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));
        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }
    @Test
    public void TC_02_Element_InVisible_In_DOM() {
        //Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(2);
        // Tai thoi diem/step nay thi confirm email textbox dang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
        // Kiem tra element k hien thi
        // CHay nhanh - ket qua step nay Passed
        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());


    }
    @Test
    public void TC_02_Element_InVisible_Not_In_DOM() {
        // Close dong popup singin
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        //Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML
        // Tai thoi diem/step nay thi confirm email textbox dang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
        // Kiem tra element k hien thi
        // CHay lau - Ket qua steps nay bi failed
        // k dung cach nay de verify element not in dom: Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }
    @Test
    public void TC_03_Element_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("dam@gmail.com");
        sleepInSeconds(2);
        // Điều kiện 1 - Element có xuất hiện ở trên UI và có trong cây HTML
        // Tai thoi diem/step nay thi confirm email textbox dang o trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(2);
        // Điều kiện 2 - Element ko xuất hiện trên UI và vẫn có trong cây HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

    }
    @Test
    public void TC_04_Element_Staleness() {
        //Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML
        // mo popup
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        // tai thoi diem nay element co xuat hien va se findElement
        // attached to the DOM
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);
        // Close dong popup singin
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);
        //Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML
        //Wait until an element is no longer attached to the DOM.
        // ham nay bat phai truyen 1 Element
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void switchToWindowByID(String expectedID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id: allIDs) {
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String expectedTitle) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id: allIDs) {
            // switch truoc
            driver.switchTo().window(id);
            sleepInSeconds(2);
            // Lay ra title cua
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id: allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
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

    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        sleepInSeconds(1);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item: allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
        sleepInSeconds(1);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item: allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }

}