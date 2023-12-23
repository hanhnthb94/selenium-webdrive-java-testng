package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_11_Button_Radio_Checkbox {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        // Verify button bij disable khi chua click vafo checkbox dieuf khoan
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        // Verify button bij enable khi da click vao checkbox dieu khoan
        Assert.assertTrue(registerButton.isEnabled());

        // Lấy ra mã màu nền của button
        String registerBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color RGB = " + registerBackgroundRGB);

        // Convert từ kiểm string qua mã color
        Color registerBackgroundColor = Color.fromString(registerBackgroundRGB);

        //Convert qua kiểu hexa
        String registerBackgroundHexa = registerBackgroundColor.asHex();
        System.out.println("Background color Hexa = " + registerBackgroundHexa);
        // in ra color kiểu hexa với cách viết hoa/viết thường
        System.out.println("Background color Hexa = " + registerBackgroundHexa.toUpperCase());
        System.out.println("Background color Hexa = " + registerBackgroundHexa.toLowerCase());

        Assert.assertEquals(registerBackgroundHexa.toLowerCase(), "#ef5a00");
        // Viết gọn thành 1 câu:
        Assert.assertEquals(Color.fromString(registerButton.getCssValue("background-color")).asHex().toLowerCase(), "#ef5a00");

    }

    @Test
    public void TC_02_faha_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        // Chuyển qua tab Đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();
        sleepInSeconds(2);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        // Verify butotn login is disable
        Assert.assertFalse(loginButton.isEnabled());


        // Verify butotn login  = backgoundcolor
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        // Nhập email, password
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("daominhdam@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(2);

        // Verify butotn login is enable
        Assert.assertTrue(loginButton.isEnabled());
        // Verify butotn login  = backgoundcolor
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");

    }

    public void TC_03_Checkbox_RadioButton() {

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