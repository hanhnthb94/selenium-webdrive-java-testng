package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
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

public class Topic_16_Shadow_DOM {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom");
        // Bắt element shadownDom thì phải Đi theo đúng cấu trúc của HTML
        // Bắt từ thẻ chứa shadownDom trước
        // shadow root: chua support su dung xpath, tagName
        // driver: dai dien cho real DOM 1
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        // shadowRootContext: dai dien cho shadow DOM ben trong (shadow host) 2 trong 1
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();
        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText, "some text");
        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());
        // nestedShadowHostElement: dai dien cho cai nested shadow DOM 3 trong 2
        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();
        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        Assert.assertEquals(nestedText, "nested text");
    }
    @Test
    public void TC_02_Shadow_DOM_Shopee() {
        driver.get("https://shopee.vn/");
        System.out.println(driver.getCurrentUrl());
            WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
            SearchContext shadowHostContext = shadowHostElement.getShadowRoot();
            // Verify hien thi
            if (shadowHostContext.findElements(By.cssSelector("div.home-popup__content")).size() > 0 &&
                    shadowHostContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()) {
                shadowHostContext.findElement(By.cssSelector("div.shopee-popup__close-btn>svg")).click();
            }
            driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iPhone 15 Pro Max");
            driver.findElement(By.cssSelector("button.shopee-searchbar__search-button>svg")).click();
    }
    @Test
    public void TC_03_Random_Popup_Not_In_DOM() {
        driver.get("https://dehieu.vn/");
    }

    public WebElement findElement(By locator) {
        if (driver.findElement(By.cssSelector("div#tve-p-scroller>article")).isDisplayed()) {
            driver.findElement(By.cssSelector("svg.tcb-icon")).click();
            sleepInSeconds(3);
        } return driver.findElement(locator);
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