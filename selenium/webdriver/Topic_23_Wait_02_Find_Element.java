package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_23_Wait_02_Find_Element {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        // implicitlyWait
        // set implicit = selenium ver 4.xx trowr len
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // set implicit = selenium ver 3.xx trowr xuong
        ///driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // explicitWait = new WebDriverWait(driver, 5);

        //ver 4.xx
        ///explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        ///fluentWait = new FluentWait(driver);
        //fluentWait.withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(500));
        // Ver 3.xx
        //fluentWait.withTimeout(5000, TimeUnit.MILLISECONDS).pollingEvery(250, TimeUnit.MILLISECONDS);
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_FindElement() {
        // Case 1 - Element duoc tim thay chi co 1
        // neu element dc tim thay thi k can cho het timeout
        // Tim thay se tra ve 1 element va qua step tiep theo
        /*System.out.println("Start step: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#email"));
        System.out.println("End step: " + getDateTimeNow());*/

        // Case 2 - Element duoc tim thay chi co nhieu hon 1
        // neu element dc tim thay thi k can cho het timeout
        // Lay cai element dau tin du co ca n node
        // qua step tiep theo
        driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("dam@gmail.com");

        // Case 3 - Element khong duoc tim thay
        // Cho het timeout la 10s
        // trong thoi gian nay, cu nua giay se tim lai 1 lan
        // Neu tim lai maf thay thi cung tra ve element roi qua step tiep theo
        // Neu tim lai ma k thay thi danh fail testcase va throw exception: NoSuchElementException
        // Cac step con lai se k dc chay
        System.out.println("Start step: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#reg_email__"));
        System.out.println("End step: " + getDateTimeNow());

    }
    @Test
    public void TC_02_FindElements() {
        List<WebElement> elementList;
        // Case 1 - chi 1 Element duoc tim thay trong khoang thoi gian duoc set
        // ko can cho het timeout
        // tra ve 1 list element chua dung 1 element
        System.out.println("Start step: " + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#email"));
        System.out.println("List have: " + elementList.size());
        System.out.println("End step: " + getDateTimeNow());

        // Case 2 - Element duoc tim thay chi co nhieu hon 1
        // ko can cho het timeout
        // tra ve 1 list element chua nhieu element
        System.out.println("Start step: " + getDateTimeNow());
        driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
        System.out.println("End step: " + getDateTimeNow());

        // Case 3 - Element khong duoc tim thay
        // Cho het timeout la 10s
        // trong thoi gian nay, cu nua giay se tim lai 1 lan
        // Neu tim lai maf thay thi cung tra ve list element roi qua step tiep theo
        // Neu tim lai ma k thay thi tra ve 1 list rong (empty) k danh fail testcase va qua steps tiep theo
        System.out.println("Start step: " + getDateTimeNow());
        driver.findElements(By.cssSelector("input#reg_email__"));
        System.out.println("End step: " + getDateTimeNow());

    }
    @Test
    public void TC_03_() {

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



    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }

}