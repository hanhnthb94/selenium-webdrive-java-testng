package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Topic_14_Actions {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    WebElement buttonX;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // Nó đang giả lập lại hành vi của Mouse/keyboard/Pen nên khi đang chạy thì k đc sử dụng các thiết bị này
        // Conflict
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(ageTextbox).perform();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Menu() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Bách Hóa Online - Lưu Niệm']"))).perform();
        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.category6506")).getText(), "THIẾT BỊ SỐ - PHỤ KIỆN SỐ");
        Assert.assertTrue(driver.findElement(By.xpath("//li/strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());
    }

    @Test
    public void TC_03_ClickAndHover() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);
        // Chọn theo Block ngang/dọc
        // Chọn 1 đến 15
        actions.clickAndHold(allNumbers.get(0)) // Click lên số 1 và giữ chuột
                .pause(1000)
                .moveToElement(allNumbers.get(14)) // Di chuột trái đến số 15
                .pause(1000)
                .release() // nhả chuột ra
                .perform(); // action tất cả các action trên
        sleepInSeconds(2);
        String[] allNumberTextExpectedList = {"1", "2", "3", "5", "6", "7", "9", "10", "11", "13", "14", "15"};
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 12);

        List<String> allNumberTextActual = new ArrayList<String>();
        for (WebElement element: allNumbersSelected) {
            allNumberTextActual.add(element.getText());
            Assert.assertEquals(element.getCssValue("background-color"), "#F39814");
        }
        // convert từ Array qua ArrayList (List)
        List<String> allNumberTextExpected = Arrays.asList(allNumberTextExpectedList);
        Assert.assertEquals(allNumberTextActual,allNumberTextExpected);
    }

    @Test
    public void TC_04_ClickAndHover() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        String osName = System.getProperty("os.name");
        Keys keys;
        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }
        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        // Chon tu 1 den 15 theo du hang va cot
        // Chon tu 1 den 12
        actions.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(11)).release().perform();
        actions.clickAndHold(allNumbers.get(12)).moveToElement(allNumbers.get(14)).release().perform();
        // Chon 13,14,15
        /*actions.keyDown(Keys.CONTROL).perform();
        actions.click(allNumbers.get(12))
                .click(allNumbers.get(13))
                .click(allNumbers.get(14))
                .keyUp(Keys.CONTROL)
                .perform();*/
        sleepInSeconds(1);
        String[] allNumberTextExpectedList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",  "13", "14", "15"};
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));

        List<String> allNumberTextActual = new ArrayList<String>();
        for (WebElement element: allNumbersSelected) {
            allNumberTextActual.add(element.getText());
        }
        List<String> allNumberTextExpected = Arrays.asList(allNumberTextExpectedList);
        Assert.assertEquals(allNumberTextActual,allNumberTextExpected);
    }

    @Test
    public void TC_05_ClickAndHover() {
        // Chon index nho hon 15:
        driver.get("https://automationfc.github.io/jquery-selectable/");
        String osName = System.getProperty("os.name");
        Keys keys;
        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }
        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        for (int i = 0; i < allNumbers.size(); i++) {
            actions.keyDown(cmdCtrl).perform();
            if (i < 15) {
                actions.click(allNumbers.get(i));
            } actions.perform();
            actions.keyUp(cmdCtrl).perform();
        }
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