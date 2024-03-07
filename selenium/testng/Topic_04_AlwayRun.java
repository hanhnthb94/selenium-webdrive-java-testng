package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_04_AlwayRun {
    WebDriver driver;
    // Unit test cho component
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("BeforeClass");
        // No bi fail o BeforeClass thi cac testcase/AfterClass cung se bi skip
        Assert.assertTrue(false);
    }
    @Test
    public void Test_01() {
        System.out.println("Test_01");
    }
    @Test
    public void Test_03() {
        System.out.println("Test_03");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("AfterClass");
    }
}
