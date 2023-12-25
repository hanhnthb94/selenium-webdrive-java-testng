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

public class Topic_15_Popup_01 {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_.icon-before")).click();
        sleepInSeconds(2);
        By loginPopup = By.cssSelector("div#modal-login-v1>div.modal-dialog>div");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog>div input#account-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog>div input#password-input")).sendKeys("automationfc1");
        driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog>div button.btn-v1.btn-login-v1.buttonLoading")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog>div div.row.error-login-panel")).getText(), "Tài khoản không tồn tại!");
        // Close popup
        driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog>div button.close")).click();
        sleepInSeconds(2);
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }
    @Test
    public void TC_02_Fixed_Popup_Not_In_DOM_01() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.styles__Root-sc-2hr4xa-0")).isDisplayed());
        // driver.findElement(By.cssSelector("div.styles__Root-sc-2hr4xa-0 input[type='tel']")).sendKeys("");
        driver.findElement(By.cssSelector("div.styles__Root-sc-2hr4xa-0 p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span[1]")).getText(), "Mật khẩu không được để trống");
        driver.findElement(By.cssSelector("img.close-img")).click();
        sleepInSeconds(2);

        // Khi popup dong lai thi HTML ko con trong DOM nua

        // findElement should not be used to look for non-present elements
        // Assert.assertFalse(driver.findElement(By.cssSelector("div.styles__Root-sc-2hr4xa-0")).isDisplayed());

        // use findElements(By) and assert zero length response instead
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.styles__Root-sc-2hr4xa-0")).size(), 0);
    }
    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM_02() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div._8ien")).isDisplayed());
        sleepInSeconds(2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("div._8ien>img")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("div._8ien")).size(), 0);
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