package javaTest;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.File;

public class Topic_03_System_Info {
    WebDriver driver;
    WebDriverWait explicitWait;
    String osName = System.getProperty("os.name");
    Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;
    String projectPath = System.getProperty("user.dir");
    String oneName = "one.jpg";
    String twoName = "two.jpg";
    String threeName = "three.jpg";

    String oneFilePath = projectPath + File.separator + "uploadFiles" + File.separator + oneName;
    String twoFilePath = projectPath + File.separator + "uploadFiles" + File.separator + twoName;
    String threeFilePath = projectPath + File.separator + "uploadFiles" + File.separator + threeName;
@BeforeClass
    public void beforeClass(){
        System.out.println(oneFilePath);
        System.out.println(twoFilePath);
        System.out.println(threeFilePath);
}
}
