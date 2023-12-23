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

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();


        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("span#speed-button","ul#speed-menu div","Faster");
        sleepInSeconds(1);

        selectItemInDropdown("span#files-button","ul#files-menu div","ui.jQuery.js");
        sleepInSeconds(1);

        selectItemInDropdown("span#number-button","ul#number-menu div","10");
        sleepInSeconds(1);

        selectItemInDropdown("span#salutation-button","ul#salutation-menu div","Dr.");
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Dr.");

    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i[class='dropdown icon']", "div.item>span.text", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='divider text']")).getText(),"Christian");
        selectItemInDropdown("i[class='dropdown icon']", "div.item>span.text", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='divider text']")).getText(),"Stevie Feliciano");
    }
    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("span.caret", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
        selectItemInDropdown("span.caret", "ul.dropdown-menu a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");
    }


    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("input.search", "div.item span", "Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Algeria");
        sleepInSeconds(1);
        selectItemInEditableDropdown("input.search", "div.item span", "Australia");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Australia");
        sleepInSeconds(1);
        selectItemInEditableDropdown("input.search", "div.item span", "Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Belgium");
        sleepInSeconds(1);
    }

    @Test
    public void TC_05_Nopcommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay'] option", "26");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay'] option[value='26']")).isSelected());
        selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth'] option", "July");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth'] option[value='7']")).isSelected());
        selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear'] option", "1994");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear'] option[value='1994']")).isSelected());
        // dropdown default sẽ k thay đổi element trc và sau khi chọn, nên cần dùng .isSelected()
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

    // Nhung gia tri truyen vao, goi la tham so
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

    // Du an khac nhau thi can sua ham cho dung yeu cau
    // Cypress/ Playwright/ WebDriveIO/...
    // Tu viet ham: Python/ JS/ Ruby
    // Break/ Vong lap/...
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