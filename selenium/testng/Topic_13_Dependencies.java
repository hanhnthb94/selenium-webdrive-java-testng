package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(listeners.ExtenReport.class)
public class Topic_13_Dependencies {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

    }
    @Test
    public void TC_01_CreateNewUser() {
        //System.out.println("Run testcase 01");
    }
    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_02_ViewAndSearchUser() {
        //System.out.println("Run testcase 02");

    }
    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_03_UpdateExistingUser() {
        //System.out.println("Run testcase 03");
        Assert.assertTrue(false);
    }
    @Test(dependsOnMethods = "TC_03_UpdateExistingUser")
    public void TC_04_MoveExistingUserToOtherRole() {
        //System.out.println("Run testcase 04");

    }
    @Test(dependsOnMethods = "TC_04_MoveExistingUserToOtherRole")
    public void TC_05_DeleteExistingUser() {
        //System.out.println("Run testcase 05");
    }

    @AfterClass
    public void afterClass() {
            driver.quit();
    }

}
