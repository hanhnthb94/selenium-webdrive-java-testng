package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_12_Parallel_Method {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
        Thread thread = null;
        thread.sleep(5000);
    }
    @Test
    public void TC_01() {
        System.out.println("Run testcase 01");
    }
    @Test
    public void TC_02() {
        System.out.println("Run testcase 02");
    }
    @Test
    public void TC_03() {
        System.out.println("Run testcase 03");
    }
    @Test
    public void TC_04() {
        System.out.println("Run testcase 04");
    }
    @Test
    public void TC_05() {
        System.out.println("Run testcase 05");
    }
    @Test
    public void TC_06() {
        System.out.println("Run testcase 06");
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (driver!=null) {
            driver.quit();
        }
    }

}
