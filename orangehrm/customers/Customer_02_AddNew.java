package customers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Customer_02_AddNew {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Delete() {

    }
    @Test
    public void TC_02_SearchWithBilling() {

    }
    @Test
    public void TC_03_SearchWithProduct() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }



}