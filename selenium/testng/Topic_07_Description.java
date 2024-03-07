package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Pre-Condition for bellow testcases");
    }
    @Test
    public void TC_01_SearchWithDay() {

    }
    @Test(description = "JIRA#44559 - Add new Users and Search")
    // Hien thi description trong log/Report HTML
    public void TC_02_SearchWithBilling() {


    }
    @Test
    public void TC_03_SearchWithProduct() {
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("Pre-Condition for above testcases");
    }
}