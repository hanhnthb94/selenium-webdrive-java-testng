package webdriver;

import org.bouncycastle.util.encoders.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectLocation = System.getProperty("user.dir"); // Câu lệnh lấy ra đường dẫn đến tên dự án
    By resultText = By.cssSelector("p#result");
    String username = "admin";
    String password = "admin";
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void TC_06_Authentication_Alert_Selenium_4xx() {
        // Cách 3
        // Thư viện Alert không sử dụng cho Authentication Alert được
        // Chrome DevTool Protocol (CDP) - Chrome/Edge (Chromium) => chạy được work around
        // Cốc cốc/ Opera (Chromium) => chạy được work around

        /*// Câu lệnh run trên cốc cốc nhưng tưởng là chrome
        ChromeOptions options = new ChromeOptions();
        options.setBinary("Địa chỉ chứa file run trình duyệt cốc cốc");
        driver = new ChromeDriver(options);*/

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(Base64.encode(String.format("%s:%s", username, password).getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

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

    public String getAuthenAlertByUrl(String url, String username, String password) {
        String[] authenArray = url.split("//");
        return authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];
    }
}