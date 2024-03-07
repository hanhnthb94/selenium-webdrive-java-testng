package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_03_Assert {
    WebDriver driver;
    // Unit test cho component
    @Test
    public void Test_01() {
        // Equals = kiem tra 2 du lieu bằng nhau (Actual - Expected): String, boolean, Int, Float,...
        String fullName = "Automation FC";
        Assert.assertEquals(fullName, "Automation FCC", "Actual fullname is not the same!");
        // True - False
        // Dieu kien la boolean (isDisplay/ isEnable/ isSelecte/ User-Defined/...
        // Mong doi ket qua tra ve dung/Sai thi dung AssertTrue/AssertFalse
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")), "Elemenet is not display!");
    }


    // Component
    private int getRandomNumber() {
        return new Random().nextInt(1000000);
    }
    public boolean isElementDisplayed(By loacator) {
        return driver.findElement(loacator).isDisplayed();
    }


}
