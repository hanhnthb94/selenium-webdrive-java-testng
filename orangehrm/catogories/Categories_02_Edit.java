package catogories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Categories_02_Edit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_EditCategoryEmpty() {

    }
    @Test
    public void TC_02_EditCategoryNameAndDescription() {

    }
    @Test
    public void TC_03_EditCategoryWithParentCategory() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }



}