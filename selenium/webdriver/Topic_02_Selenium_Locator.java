package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.SocketOption;
import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");
        driver.manage().window().maximize();
    }

    // Firstname textbox - HTML code
    // HTML Element: <tagname attribute_name_1='attribute_value' attribute_name_2='attribute_value' ...>
    /*
     * <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
     */

    @Test
    public void TC_01_ID() {
        // Seleium versionL 1.x/ 2.x/ 3.x/ 4.x
        // 8 loai Locator
        // Selenium Locator = HTML Attribute
        // Id/ Class/ Name = Trùng với 3 Attribute của HTML
        // LinkText/ Partial LinkText: HTML Link (thẻ <a>)
        // Tagname: HTML Tagname
        // Css/ XPath

        // Seleium versionL 4.x - GUI (Graphic User Interface)
        // Class - Relative Locator
        // above/ bellow/ near/ leftOf/ rightOf

        // UI testing
        // FUI: Functional UI

        // GUI: Graphic UI
        // Font/ Size/ Color/ Position/ Locator/ Resolution/ Responsite/...

        //TestNG: Oder testcase theo Alphabet (0-9, A-Z)
        /*
        Selenium Locator By
        By: Class
        C: Class
        m: Method
        I: Interface
        E: Enum
        R: Record
        A: Annotation
        f: biến final
         */
        // tìm element có id là FirstName
        // element phải có class mới dùng được, k có class thì k dùng được, hoặc k duy nhất thì ta k dùng
        driver.findElement(By.id("FirstName")).sendKeys("Keane");
        System.out.println(driver.findElement(By.id("FirstName")));

    }


    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }



    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
    }

    @Test
    public void TC_04_Tagame() {
        driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
        driver.findElement(By.linkText("Shipping & returns"));
        // linkText bắt buộc tìm bằng all Text, chứ k tìm 1 phần đc
        // do chinh xac cao = tuyet doi
    }


    @Test
    public void TC_06_PartialLink() {
        driver.findElement(By.partialLinkText("New products"));
        //do chinh xac k cao_tuong doi_dau/cuoi/giua
    }

    @Test
    public void TC_07_Css() {
        // Css va XPath lay het duoc tat ca, 6 case tren rat it dung
        // Css voi ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        // or driver.findElement(By.cssSelector("#FirstName"));

        // Css voi class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        // Css voi name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        // Css voi tagname
        driver.findElement(By.cssSelector("input"));

        // Css voi  partial link: link phai di cung voi the a href
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));

        // Css voi link: link phai di cung voi the a href
        //driver.findElement(By.cssSelector("a[href*='addresses']"));
        //driver.findElement(By.cssSelector("a[href^='addresses']"));
        //driver.findElement(By.cssSelector("a[href$='addresses']"));
    }

    @Test
    public void TC_08_XPath() {
        // XPath k cho viet tat
        // XPath voi ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        // Css voi class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        // Css voi name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        // Css voi tagname
        driver.findElement(By.xpath("//input"));

        // Css voi  partial link: link phai di cung voi the a href
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));

        // Css voi  partial link:
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}