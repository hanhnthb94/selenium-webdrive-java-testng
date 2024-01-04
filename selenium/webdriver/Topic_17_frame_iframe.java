package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
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

public class Topic_17_frame_iframe {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        // Trang A, domain: formsite.com
        driver.get("");
        // Chua iframe trang B
        // Tu A vao B
        driver.switchTo().frame("");
        driver.findElement(By.cssSelector("")).click();

        // Tu B vao C
        driver.switchTo().frame("");
        driver.findElement(By.cssSelector("")).click();

        // Tu C quay lai B
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("")).click();

        // Tu B quay lai A
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("")).click();
    }
    @Test
    public void TC_01_Form_Site() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(3);
        // Iframe Element
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());
        // Neu k swich to iframe ma thao tac binh thuong thi run testcase se bi loi k tim thay element

        // switch bang 3 cach
        // driver.switchTo().frame(0); // su dung index
        // driver.switchTo().frame("frame-one85593366"); // su dung name hoac id
        driver.switchTo().frame(formIframe); // dung element -- Nen dung
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(2);
        // Thao tac voi 1 element ben ngoai iframe (trang A)
        driver.switchTo().defaultContent();

        Assert.assertEquals(driver.findElement(By.cssSelector("h2.reasons__title")).getText(), "Why Formsite");

        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
        // Trang nao switch rieng trang do
    }
    @Test
    public void TC_03_Kynaeng() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "172K followers");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cs_chat_iframe");
        driver.findElement(By.cssSelector("div.button_bar")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0909444555");
        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys("Java Bootcamp");
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Java");
        driver.findElement(By.cssSelector("button.search-button")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.content>h4")).getText(), "Lập trình Java trong 4 tuần");
    }
    @Test
    public void TC_04_Frame_HDFCbank() {
        // Chay bang firefox fail, chrome thi pass, co the do web cua ngan hang, chan k cho chay auto
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("div.inputfield>input")).sendKeys("luis_suarez");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(2);
        // tu frame nay sang trang khac
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
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