package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    //Cac cau lenh de thao tac voi browser se di sau "drive."
    WebDriver driver;
    // Cac cau lenh thao tac voi element se di sau element.
    WebElement element;

    @BeforeClass // cac @test la Annotation cua testNG, BeforClass se chay truoc tata ca testcase
    public void beforeClass() {
        // Muon dung thi phai khoi tao bien len
        // Neu k khoi tao se gap loi NullPointerException
        driver = new FirefoxDriver();//**
        //driver = new ChromeDriver();//**
        //driver = new SafariDriver();//**
        //driver = new EdgeDriver();//**
        //driver = new InternetExplorerDriver();
        // driver = new OperaDriver(); // selenium 4 ngung support
        // driver = new HTMLUnit(); // Headless browser
        // Tu nam 2016 chrome va firefox co support chay dang headless
        // Handless: crawl data (DA)/ dev fontEnd
        // FirefoxDriver: firefox on windows (b2b6313a-0be4-4c05-9cf1-52bac8d2b46a)
        // Goi la GUID: Global Unique Identifier Number (so dinh danh toan cau co 1 id du lieu duy nhat k trung lap)

        // Selenium 3.xx/ 2.xx/1.xx
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Selenium 4.xx
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));//**

        driver.manage().window().maximize();//**
    }

    @Test
    public void TC_01_() throws MalformedURLException {
        /*// Set trực tiếp vào
        //Mở ra 1 page Url bất kì
        driver.get("https://www.facebook.com/");//**

        // Khai báo biến rồi gán vào sau:
        // Nếu như biến chỉ sử dụng 1 lần thì k nên taoj biến
        String homePageUrl = "https://www.facebook.com/";
        driver.get(homePageUrl);

        System.out.println("Windown/ Tab ID = " + driver.getWindowHandle());


        // Lý do chạy chậm:
        // 1 - Của ÍSP: viettel/FPT/VPNT
        // 2 -  có bnhiu Mb (có cáp quang k?)
        // 100Mb - 16Mb

        // Gói có nhiều ng dùng k?
        // Giờ truy cập là giờ nào?
        // ....
        // close đóng 1 tab:
        driver.close();//*
        // close đóng trình duyệt, k quan tâm có bnhiu tab  tab:
        driver.quit();//**

        // 2 hàm findElements, findElement này sẽ bị ảnh hưởng bởi timeout của implicitlyWait và ngược lại implicitlyWait chỉ tác động lên 2 hàm này

        // Nó sẽ đi tìm loại By nào và trả về element nếu như đc tìm thấy (WebElement)
        // Nếu k đc tìm thấy: fail tại step này - throw exception: NoSuchElementException
        // Trả về 1 element - Nếu tìm thấy nhiều hơn 1 thì chỉ lấy cái đầu tiên và thao tác vs cái đầu tiên
        // (Nếu findElement là kiểu dữ liệu webElement thì biến cũng phải khai báo kiểu dữ liệu là WebElement)
        WebElement emailAddressTextbox = driver.findElement(By.id("email")); //**

        // Nó sẽ đi tìm loại By nào và trả về 1 danh sách element nếu như đc tìm thấy (List WebElement)
        // Nếu k đc tìm thấy: k bị fail tại step này trả về 1 list rỗng (0 element)
        List<WebElement> checkboxs = driver.findElements(By.xpath("//input[@type='checkbox']")); //**
        // Dùng findElements sẽ k dùng hàm để thao tác element được, mà phải xác định 1 element mới thao tác được:
        checkboxs.get(1).click();

        // Các biến được khai báo trong 1 hàm/block code -> phạm vi cục bộ (Local)
        // Dùng trong các hàm nó ddc khai báo/ block code được dinh ra
        String homePageUrl = 'https://www.facebook.com/';

        // Trong 1 hàm nếu như có 2 biến cùng tên (Global/Local) thì sẽ ưu tiên lấy biến Local
        // 1 biến Local nếu như đc gọi tới mà chưa đckhoiwri tạo thì sẽ bị báo lỗi ngay lúc viết code
        driver.get(homePageUrl);

        // Nếu trong 1 hàm có 2 biến cùng tên (Global/Local) mà mình muốn lấy biến Global thì dùng this.
        // Biến Golbal chưa khởi tạo mà gọi ra thì k báo lỗi ngay ở lv compile code
        // Nhưng khi runtime sẽ bị lỗi
        driver.get(this.homePageUrl);

        // Tại sao cần phải lấy dữ liệu ra làm gì? (để verify)
        // 5 Hàm dùng để lấy ra Url của màn hình/page hiện tại đang đứng:
        driver.getCurrentUrl(); //**

        // Lấy ra page source HTML/ CSS/ JS của page hiện tại:
        // Verify 1 cách tương đối
        driver.getPageSource().contains("The Apple and Google Play logos are trademarks of their respactive owners.");
        Assert.assertTrue(driver.getPageSource().contains("The Apple and Google Play logos are trademarks of their respactive owners."));

        // Lấy ra title của page hiện tại:
        driver.getTitle();

        // Lấy ra ID của cửa sổ/tab hiện tại
        // Handle Window/ Tab:
        driver.getWindowHandle(); //*
        driver.getWindowHandles(); //*

        // Nếu chỉ dùng 1 lần thì k nên khai báo biến

        // Get Cookies - Framwork
        driver.manage().addCookie(); //*

        // Get ra những log ở devtool
        driver.manage().logs().get(LogType.DRIVER); //*

        // Apply cho việc tìm element (findElement/ findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**

        // Chờ cho page load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trước khi dùng với thư viện JavascriptExcutor
        // Inject 1 đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Selemium ver 4.xx
        // Lấy ra thời gian tìm element, k có dùng
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        // Chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize(); //**
        driver.manage().window().maximize();

        // test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().setSize(new Dimension(3440, 1440));

        // Get size giao diện
        driver.manage().window().getSize();

        // Set cho browser ở vị trí nào so với độ phân giải màn hình (run trên màn hình c kích thước bnhiu?)
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();


        // Điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();
        driver.navigate().to("https://www.facebook.com/");
        driver.navigate().to(new URL("https://www.facebook.com/"));
        // hay dùng cái này hơn: driver.get("https://www.facebook.com/");

        // Alert/ Window (tab)/ Frame (ỉfame)  sau này sẽ học chi tiết //*
        driver.switchTo().alert();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys();

        // Lấy ra ID của cửa sổ/tab hiện tại sau này sẽ học chi tiết  //*
        // Handle Window/ Tab:
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        Set<String> allWindowIDs = driver.getWindowHandles();

        // Switch/ handle frame (ỉame)
        // Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("365987432");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();

        // Từ cái frame trong đi ra cái frame ngoài chứa nó
        driver.switchTo().parentFrame();
*/
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}