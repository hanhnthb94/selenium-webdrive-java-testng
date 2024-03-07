package orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Order_01_Search {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("Pre-Condition for bellow testcases");
    }
    @Test(groups = "order")
    public void TC_01_SearchWithDay() {

    }
    @Test(groups = "order")
    public void TC_02_SearchWithBilling() {

    }
    @Test(groups = "order")
    public void TC_03_SearchWithProduct() {
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("Pre-Condition for above testcases");
    }
}