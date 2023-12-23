package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_12_Checkbox_Radio {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_Telerisk_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By rearSiteCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        // driver.findElement(dualZoneCheckbox).click();
        // Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        // Case mặc định đã chọn
        /*if (!driver.findElement(rearSiteCheckbox).isSelected()) {
            driver.findElement(rearSiteCheckbox).click();
        }*/
        checkToElement(rearSiteCheckbox);
        // Case mặc định chưa chọn
        /*if (!driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
        }*/
        checkToElement(dualZoneCheckbox);


        Assert.assertTrue(driver.findElement(rearSiteCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());


        // Bỏ chọn 2 check box\
        unCheckToElement(rearSiteCheckbox);
        unCheckToElement(dualZoneCheckbox);

    }
    @Test
    public void TC_02_Default_Telerisk_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
        // Chỉ chọn chứ k bỏ chọn được
        // Click chọn 1 trong 2
        checkToElement(twoPetrolRadio);
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());
    }

    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("li[id='id_52'] input[type='checkbox']"));
        // Chọn hết tất cả các checkbox trong màn hình đó
        for (WebElement checkbox: allCheckboxs) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // Verify all
        for (WebElement checkbox: allCheckboxs) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // Chọn 1 checkbox/radio nào đó trong tất cả các checkbox/radio
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckboxs = driver.findElements(By.cssSelector("li[id='id_52'] input[type='checkbox']"));

        for (WebElement checkbox: allCheckboxs) {
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // Verify all
        for (WebElement checkbox: allCheckboxs) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }

    }
    @Test
    public void TC_04_Custom_Radio() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        // Case1: dùng thẻ input để click => thẻ input bị che bởi 1 element khác
        // isSelected: only applies to input elements

        // Case 2: Dùng thẻ div bên ngoài để click thì pass => nhưng verify thì failed

        // Case 3: dùng thẻ div bên ngoài để click => pass. Dùng thẻ div để verify => pass
        // => Nhưng 1 element mà cần define tới 2 locator thì sau này mantian mất thời gian

        // driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div[@class='mat-radio-container']")).click();
        // Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());


        // Case 4:
        // Dùng thẻ input để click => sử dụng JavascriptExecutor (JS)
        // Dùng thẻ input để verify => isSelected: only applies to input elements
        // Chỉ cần 1 locator

        By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
        // JS k cần scroll
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(registerRadio));

        Assert.assertTrue(driver.findElement(registerRadio).isSelected());


        // Khai báo biến:
        // interface webDriver
        // interface JavascriptExecutor
        // interface k khai báo bằng new được, mà chỉ có thể gán từ interface này sang enterface khác
        // JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_05_Custom_Checkbox_Button_Radio_Google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangTriCheckbox = By.xpath("//div[@aria-label='Quảng Trị']");

        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
        driver.findElement(canThoRadio).click();
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

        Assert.assertEquals(driver.findElement(quangTriCheckbox).getAttribute("aria-checked"), "false");
        driver.findElement(quangTriCheckbox).click();
        Assert.assertEquals(driver.findElement(quangTriCheckbox).getAttribute("aria-checked"), "true");
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

    public void checkToElement(By byXpath) {
        // Nếu như element chưa được chọn thì mới click
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(1);
        }
    }
    public void unCheckToElement(By byXpath) {
        // Nếu như element được chọn thì vào click lần nữa cho bỏ chọn
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(1);
        }
    }

}