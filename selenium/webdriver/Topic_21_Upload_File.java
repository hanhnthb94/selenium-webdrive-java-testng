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

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Topic_21_Upload_File {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String oneName = "one.jpg"; String twoName = "two.jpg"; String threeName = "three.jpg";
    String oneFilePath = projectPath + File.separator + "uploadFiles" + File.separator + oneName;
    String twoFilePath = projectPath + File.separator + "uploadFiles" + File.separator + twoName;
    String threeFilePath = projectPath + File.separator + "uploadFiles" + File.separator + threeName;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        //String filePathn = 'D:\\AUTO\\img\\2023-12-24_11-19-31-000.jpg';
        // Windows: \\
        // MAC: /
        // File nam o dau
        // Trong thu muc uploadFile
        // Neu may khac dung OS khac co chay duoc k
        // Duong dan tuong doi cua file do
        By uploadBy = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadBy).sendKeys(oneFilePath);
        sleepInSeconds(2);
        driver.findElement(uploadBy).sendKeys(twoFilePath);
        sleepInSeconds(2);
        driver.findElement(uploadBy).sendKeys(threeFilePath);
        sleepInSeconds(2);
        // Verify file loader success
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + oneName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + twoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + threeName + "']")).isDisplayed());


        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        // classic for
        /*for (int i = 0; i < startButtons.size(); i++) {
            if (startButtons.get(i).isDisplayed()) {
                startButtons.get(i).click();
                sleepInSeconds(2);
            }
        }*/
        // For-each
        for (WebElement button: startButtons) {
            button.click();
            sleepInSeconds(2);
        }
        // Verify file upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + oneName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + twoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + threeName + "']")).isDisplayed());

    }
    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadBy = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadBy).sendKeys(oneFilePath + "\n" + twoFilePath + "\n" + threeFilePath);
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + oneName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + twoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + threeName + "']")).isDisplayed());
        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button: startButtons) {
            button.click();
            sleepInSeconds(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + oneName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + twoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + threeName + "']")).isDisplayed());
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