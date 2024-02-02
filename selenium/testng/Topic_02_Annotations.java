package testng;

import org.testng.annotations.*;

public class Topic_02_Annotations {
    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AfterClass");
    }

    @BeforeGroups
    public void beforeGroups() {
        System.out.println("BeforeGroups");
    }

    @Test
    public void Test_01() {
        System.out.println("Test_01");
    }
    @Test
    public void Test_03() {
        System.out.println("Test_03");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest");
    }
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest");
    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }
    @Test
    public void Test_02() {
        System.out.println("Test_02");
    }
    @Test
    public void Test_05() {
        System.out.println("Test_05");
    }


}
