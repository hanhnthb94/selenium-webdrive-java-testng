package webdriver;

import org.openqa.selenium.Alert;
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
import java.util.regex.Pattern;

public class Topic_26_Wait_05_Explicit_01_Knowledge {
    WebDriver driver;
    WebDriverWait explicitWait; // buoc nay la buoc khai bao, chua khoi tao

    @BeforeClass // Precondition - khoi tao du lieu/ data test/ page class/ varible
    public void beforeClass() {
        // 1
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        // Khoi tao 1 explicit wait co tong thoi gia la 10s - polling la 0.5s mac dinh
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Khoi tao 1 explicit wait co tong thoi gia la 10s - polling la 0.3s tu set
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
    }

    @Test
    public void TC_01_() {
        // Cho cho 1 alert presence trong HTML trc khi thao tac len
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        // Cho cho element k o trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));
        // Chowf cho element co trong DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#search-form input#live-search-bar")));
        // Cho cho 1 list element co trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("form#search-form"), By.cssSelector("input#live-search-bar")));
        // CHo cho 1 or nhieu element duoc hien thi tren UI
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector("1"))));
        // CHo cho element duoc thao tac: clcik/ link/ checkbox/...
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.className("")));
        // CHo cho page hien tai co title nhu mong doi
        explicitWait.until(ExpectedConditions.titleIs("")); // dung truoc ham ben duoi:
        driver.getTitle();
        // Ket hop and nhieu dieu kien (it dung), ca 2 dieu kien deu dung
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(""))));
        // Ket hop or nhieu dieu kien (it dung), 1 trong 2 dieu kien dung
        explicitWait.until(ExpectedConditions.or(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(""))));
        // CHo cho 1 element co attribute chua gia tri mong doi (tuong doi)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "palceholder", "Search entire"));
        // CHo cho 1 element co attribute chua gia tri mong doi (tuyet doi)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "palceholder", "Search entire"));
        // CHo cho 1 element co attribute khac null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("input#search")), "palceholder"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("imput#search")), "namespaceURI", "//http://www.w3.org/1999/xhtml"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("imput#search")), "namespaceURI", "//http://www.w3.org/1999/xhtml"));
        // Cho cho 1 element duoc selected thanhh cong, co the dung cho Checkbox/Dropdown Item (default)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        // Cho cho 1 element duoc selected roi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));
        // Cho cho 1 element chua duoc selected
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

        // Cho cho 1 frame/iframe duoc avaiable va switch qua
        // Name or ID
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));
        // Index
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
        // By or element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
        // Cho cho 1 element bien mat ko hien thi tren UI
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        // Cho cho 1 doan code JS can tra ve kieu gia tri
        explicitWait.until(ExpectedConditions.jsReturnsValue("document.documentElement.innerText;"));
        // Cho cho 1 doan code JS duoc thuc thi ma ko nem ra ngoai le nao het
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;"));

        // Cho cho so luong element bang 1 con so co dinh
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 10));
        // Cho cho window/Tab al bao nhieu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "Mobile"));
        Pattern pattern = Pattern.compile("Moblie", Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), pattern));

        // Bat buoc text nay trong DOM/HTML
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""), "Mobile"));

        explicitWait.until(ExpectedConditions.urlToBe("//http://www.w3.org/1999/xhtml"));
        explicitWait.until(ExpectedConditions.urlContains("www.w3.org/1999/xhtml"));
        explicitWait.until(ExpectedConditions.urlMatches("[^abc]"));

        // Cho cho 1 dieu kien ma element nay bo bi update trangj thai - load lai HTML
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(""))));







    }
    @Test
    public void TC_02_Less_Than_5_Second() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSeconds(3);
        Assert.assertEquals( driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_03_Greater_Than_5_Second() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        // Neu nhu dung Implicit/ Explicit/ Fluent Wait thi khi dieu kien dc thoa man thi k can cho het timeout
        // Neu dung sleep cung thi no k quan tam
        sleepInSeconds(10);
        Assert.assertEquals( driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    @Test
    public void TC_04_() {

    }
    @Test
    public void TC_05_() {

    }
    @Test
    public void TC_06_() {

    }
    @Test
    public void TC_07_() {

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