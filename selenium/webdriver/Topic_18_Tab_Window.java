package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class Topic_18_Tab_Window {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Basic_Form() {
        /* Lay ra ID tab hien tai
        String parendID = driver.getWindowHandle();
        System.out.println("Parend tab ID = " + parendID);
        driver.findElement(By.cssSelector("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);
        // Lay ra tat ca ID khac nhau tren window
        Set<String> allIDs = driver.getWindowHandles();
        for (String id: allIDs) {
                // kieu du lieu tham hcieu k su dung cac ky tu: == < > (chi kieu du lieu nguyen thuy moi dung)
                if (!id.equals(parendID)) {
                        driver.switchTo().window(id);
                        // break de thoat khoi vong lap ko can kiem tra cac gia tri con lai (neu co)
                        break;
                }
        }
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        sleepInSeconds(2);
        String googleTabID = driver.getWindowHandle();
        allIDs = driver.getWindowHandles();
        for (String id: allIDs) {
                // kieu du lieu tham hcieu k su dung cac ky tu: == < > (chi kieu du lieu nguyen thuy moi dung)
                if (!id.equals(googleTabID)) {
                        driver.switchTo().window(id);
                        // break de thoat khoi vong lap ko can kiem tra cac gia tri con lai (neu co)
                        break;
                }

        }
        driver.findElement(By.cssSelector("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);
        // Neu nhu nhieu hon 2 tab thi ham switchToWindowByID k dung
        // Can dung ham khac */
    }
    @Test
    public void TC_02_Form_Site() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(1);
        switchToWindowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        sleepInSeconds(1);

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(1);
        switchToWindowByTitle("Facebook");
        driver.findElement(By.cssSelector("input#email")).sendKeys("dam@gmail.com");
        sleepInSeconds(1);
        switchToWindowByTitle("Selenium WebDriver");
    }
    @Test
    public void TC_03_KynaEng() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("div.hotline img[alt='facebook']")).click();
        switchToWindowByTitle("Kyna.vn | Ho Chi Minh City  | Facebook");
        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("dam@gmail.com");
        sleepInSeconds(2);
        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        driver.findElement(By.cssSelector("div.hotline img[alt='youtube']")).click();
        sleepInSeconds(2);
        switchToWindowByTitle("Kyna.vn - YouTube");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#inner-header-container yt-formatted-string#text")).getText(), "Kyna.vn");
    }


    @Test
    public void TC_04_TechPanda() {
        driver.get("http://live.techpanda.org/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepInSeconds(2);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");
        switchToWindowByTitle("Mobile");
        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        sleepInSeconds(2);

        closeAllWindowWithoutParent(parentID);
        sleepInSeconds(2);
    }

    @Test
    public void TC_05_Selenium_Version_4() {
        // Không tối ưu hơn mấy, dễ gây hiểu nhầm
        // Driver đang ở trang Home
        // Tổ hợp phím: Ctrl N: New Window; Ctrl T: New Tab
        driver.get("https://skills.kynaenglish.vn/");
        System.out.println("Driver ID Kyna = " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        // Tự mở ra 1 tab/Window mới và nhập cái url vào
        // Window mới - driver nhảy qua Windows mới này nhưng ko có tạo ra driver mới
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://skills.kynaenglish.vn/phan-tich-ky-thuat-nang-cao-trong-chung-khoan");
        System.out.println("Driver ID Kyna = " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.cssSelector("a.crs-btn-buy")).click();
        sleepInSeconds(2);

        // Tại Window này - new Tab mới - driver nhảy quá Tab mới đó từ Window trước đó
        driver.switchTo().newWindow(WindowType.TAB).get("https://web.facebook.com/kyna.vn");
        System.out.println("Driver ID = " + driver.toString());

        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("dam@gmail.com");
        sleepInSeconds(2);

        // Muốn quay lại Tab/Window cũ thì phải switch
        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Java");
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("button.search-button")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.content>h4")).getText(), "Lập trình Java trong 4 tuần");

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