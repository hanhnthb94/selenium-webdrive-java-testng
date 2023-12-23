package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String firstName = "Automation", lastName = "FC", emailAddress = getEmailAddress(), password = "123456";
    String fullName = firstName + " " + lastName;
    String company = "Selenium WebDrive";
    String day = "15", month = "November", year = "1950";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector(".ico-register")).click();

        driver.findElement(By.cssSelector("#gender-male")).click();
        driver.findElement(By.cssSelector("#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#LastName")).sendKeys(lastName);

        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        day.selectByVisibleText("15");
        month.selectByVisibleText("November");
        year.selectByVisibleText("1950");
        // biến dùng 1 lần thì k nên khai báo. thao tác trực tiếp bằng câu:
        /*new Select(driver.findElement(By.name("DateOfBirthDay"))).deselectByVisibleText("15");
        new Select(driver.findElement(By.name("DateOfBirthMonth"))).deselectByVisibleText("November");
        new Select(driver.findElement(By.name("DateOfBirthYear"))).deselectByVisibleText("1950");*/

        // Verify drpdown này là Single (kp Multiple) (chỉ được chọn 1 giá trị hay đc chọnnhieeufu value)
        Assert.assertFalse(day.isMultiple());

        // Verify số lượng item trong dropdown này là 32 item
        //List<WebElement> dayOptions = day.getOptions();
        //Assert.assertEquals(dayOptions.size(),32); // .size sẽ trả về số lượng kiểu int
        // Rút gọn lại thành
        Assert.assertEquals(day.getOptions().size(),32);
        Assert.assertEquals(month.getOptions().size(),13);
        Assert.assertEquals(year.getOptions().size(),112);


        driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("#Company")).sendKeys(company);
        driver.findElement(By.cssSelector("#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("#register-button")).click();
        sleepInSeconds(2);

        // Verify đã vào trang home page sau khi đăng ký thành công
        Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(),"Your registration completed");

    }

    @Test
    public void TC_02_Login() {
        // Login
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector(".ico-login")).click();

        driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(2);

        // Verify
        driver.findElement(By.cssSelector(".ico-account")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), company);

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
}