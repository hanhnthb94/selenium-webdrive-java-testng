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

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_15_Popup_02 {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Random_Popup_Not_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSeconds(5);
        // Delete cookie: shift ctrl delete
        By newsLetterPopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        // Neu hien thi thi nhan close popup di
        if (driver.findElements(newsLetterPopup).size() > 0 && driver.findElements(newsLetterPopup).get(0).isDisplayed()) {
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a")).click();
        }
        // Neu ko hien thi qua steps tiep theo (search du lieu)
        // Nhap vao field search du lieu
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='post-details']//a[text()='Agile Testing Explained']")).isDisplayed());

    }
    @Test
    public void TC_02_Random_Popup_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(3);
        findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");
    }
    @Test
    public void TC_03_Random_Popup_Not_In_DOM() {
        driver.get("https://dehieu.vn/");
        sleepInSeconds(3);
        By inviteGroupPopup = By.cssSelector("div.popup-content");
        // Neu hien thi thi nhan close popup di
        if (driver.findElements(inviteGroupPopup).size() > 0 && driver.findElements(inviteGroupPopup).get(0).isDisplayed()) {
            driver.findElement(By.cssSelector("button#close-popup")).click();
        }
        // Neu ko hien thi qua steps tiep theo (search du lieu)
        // Nhap vao field search du lieu
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/dang-nhap");
    }

    public WebElement findElement(By locator) {
        if (driver.findElement(By.cssSelector("div#tve-p-scroller>article")).isDisplayed()) {
            driver.findElement(By.cssSelector("svg.tcb-icon")).click();
            sleepInSeconds(3);
        } return driver.findElement(locator);
    }
    
    @AfterClass
    public void afterClass() {
        driver.quit();
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