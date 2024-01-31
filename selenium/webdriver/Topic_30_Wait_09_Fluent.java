package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

public class Topic_30_Wait_09_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    FluentWait<String> fluentString;
    long fullTimeoutInSecond = 30;
    long pollingTimeoutInMillisecond = 300;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // Time - Default Polling time: 0.5s
        fluentDriver = new FluentWait<WebDriver>(driver);
    }

    @Test
    public void TC_01_() {
        // KHOI TAO
        fluentDriver = new FluentWait<WebDriver>(driver);
        WebElement element = driver.findElement(By.cssSelector(""));
        fluentElement = new FluentWait<WebElement>(element);
        fluentString = new FluentWait<String>("Hello world");

        // SETTING
        // Set tong thoi gian
        fluentDriver.withTimeout(Duration.ofSeconds(10));
        // Set polling time
        fluentDriver.pollingEvery(Duration.ofMillis(300));
        // Ignoring NoSuchElementException exception
        fluentDriver.ignoring(NoSuchElementException.class);
        // Ignoring TimeoutException exception
        fluentDriver.ignoring(TimeoutException.class);
        // FluentWait neu k set ignoring thi polling 1 lan no se tra ve loi ngay, con impicitWait thi polling cho den het tong thoi gian moi tra ra loi

        // CONDITION
        fluentDriver.until(new Function<WebDriver, Object>() {

            @Override
            public Object apply(WebDriver webDriver) {
                return  null;
            }
        });

        fluentDriver.until(new Function<WebDriver, String>() {


            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("")).getText();
            }
        });

        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(TimeoutException.class, NoSuchElementException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector("")).isDisplayed();
                    }
                });
    }
    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        // Cho cho text hello world xuat hien trong 10s
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
            }
        });
        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                String text = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
                System.out.println("Get text = " + text);
                return text;
            }
        });
        Assert.assertEquals(helloText, "Hello World!");

        fluentDriver.withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(100))
                        .ignoring(NoAlertPresentException.class);
        fluentDriver.until(new Function<WebDriver, Alert>() {
            @Override
            public Alert apply(WebDriver webDriver) {
                return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
            }
        });
    }
    @Test
    public void TC_03_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        waitAndFindElement(By.cssSelector("div#start>button")).click();
        String helloText = waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText();
        Assert.assertEquals(helloText, "Hello World!");
    }
    @Test
    public void TC_03_() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(countDownTime);
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });
        // Cac loi thuong gap khi dung fluentWait:
        // 1 - Timeout khong du
        // 2 - Ko set ignore Exception
        // 3 - Truyen sai Exception de ignore
        // Polling tim phai luon luon nho hon timouts
    }

    public WebElement waitAndFindElement(By locator) {
        FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(fullTimeoutInSecond))
                .pollingEvery(Duration.ofMillis(pollingTimeoutInMillisecond))
                .ignoring(NoSuchElementException.class);
        return  fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}