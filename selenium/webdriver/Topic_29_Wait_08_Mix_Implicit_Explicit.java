package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class Topic_29_Wait_08_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Only_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");
        // Khi vao tim element thi no tim thay ngay, ko can cho het timeout
        driver.findElement(By.cssSelector("input#email"));

    }
    @Test
    public void TC_02_Only_Implicit_Not_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        // Khi vao tim element thi no khong tim thay
        // Polling moi nua giay tim lai 1 lan
        // KHi het timeout se danh fail testcase va throw exception: NoSuchElementException
        driver.findElement(By.cssSelector("input#automation"));
    }
    @Test
    public void TC_03_Only_Explicit_Found() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }
    @Test
    public void TC_04_Only_Explicit_Not_Found_Param_By() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Khi vao tim element thi no khong tim thay
        // Polling moi nua giay tim lai 1 lan
        // KHi het timeout se danh fail testcase va throw exception: TimeOutException
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
    }
    @Test
    public void TC_05_Only_Explicit_Not_Found_Param_WebElement() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Khi vao tim element thi no khong tim thay
        // Polling moi nua giay tim lai 1 lan
        // Khi het timeout se danh fail testcase va throw exception: NoSuchElementException
        // Ham ben duoi chay het 0s: Vi ham findElement khong wait nen fail thi se throw ra exception (implicit)
        // Ham wait co text Located: thi co findElement/ findElements bên trong hàm
        // Nen dung ca 2: vi neu k wait thi co the gay ra fail testcase khi k du time
        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
        } catch (Exception e) {
            System.out.println("End time: " + getDateTimeNow());
            e.printStackTrace();
        }
    }
    @Test
    public void TC_06_Mix_Implicit_Explicit_Found() {
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("input#email"));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        /* Wait explicit không thể ảnh hưởng vào các hàm findElement/ findElements (bị ảnh hưởng bởi implicit)
        Wait implicit nó sẽ ảnh hưởng vào các hàm wait explicit nếu có set */
    }
    @Test
    public void TC_07_Mix_Implicit_Explicit_Not_Found() {
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("Start time: " + getDateTimeNow());
        // code se chay tu cac ham ben trong truoc roi moi toi cac ham ben ngoai
        // implicit luon chay truoc explicit
        // explicit chay sau implicit khoang nho
        // du set implicit < = > explicit thi cong thuc luon giong nhau
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
        } catch (Exception e) {
            System.out.println("End time: " + getDateTimeNow());
            e.printStackTrace();
        }


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}