package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
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
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Relative() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");

        // Login button
        By loginButtonBy = By.cssSelector("button.login-button");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));
        // Remember Me checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");

        // Forgot password link

        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        // Password textbox
        By passwordTextboxBy = By.cssSelector("input#Password");
        // GUI (location/ position) ben tren, ben phai, ben trai, ben duoi
        // findElement: neu co nhieu kq, thi se lay cai dau tien de su dung
        // findElements: se tra ve dc nhieu ket qua
        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                        .above(loginButtonBy)
                        .toRightOf(rememberMeCheckboxBy)
                        .toLeftOf(forgotPasswordElement)
                        .below(passwordTextboxBy)
                        .near(forgotPasswordElement));
        System.out.println(rememberMeTextElement.getText());

        List< WebElement> allLinks = driver.findElements(RelativeLocator.with(By.tagName("a")));
        System.out.println(allLinks.size());

    }
/*
Bài tập: demo lai phần XPath/ Priority
Typo = sai chính tả/vị trí:
//a[@href='huong-dan-trien-khai'] -> case k có attribute là id/class/name
    //div[@class='input-container'] -> có class và là duy nhất: nên dùng
    //input[@type='file'] -> có thẻ id/class rồi: không ưu tiên dùng
    //input[@id='real-input'] -> k có ý nghĩa: k nên dùng
    //span[@class='file-info'] -> k có ý nghĩa: k nên dùng
//button[@class='browse-btn'] -> chỉ có attribute class và là duy nhất: nên dùng
...
 */

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}