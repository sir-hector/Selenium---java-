import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutomationPracticeTest {
    private static WebDriver driver;
    private static WebDriver driverSafari;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driverSafari = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverSafari.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("http://automationpractice.pl/");
        driverSafari.get("http://automationpractice.pl/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
        driverSafari.quit();
    }

    @Test
    public void clickShopNow() throws InterruptedException {
        WebElement shopNow=driver.findElement(By.cssSelector(".shopping_cart a"));
        shopNow.click();

        String expected = driver.getCurrentUrl();
        String actual = "http://automationpractice.pl/index.php?controller=order";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void clickShopNowSafari() throws InterruptedException {
        WebElement shopNow=driverSafari.findElement(By.cssSelector(".shopping_cart a"));
        shopNow.click();

        String expected = driverSafari.getCurrentUrl();
        String actual = "http://automationpractice.pl/index.php?controller=order";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testForm() {
        WebElement form = driver.findElement(By.xpath("//form"));
        List<WebElement> textInputs = driver.findElements(By.xpath("//input[@type='text']"));
        Assertions.assertEquals(2, textInputs.size());
    }

    @Test
    public void testFormSafari() {
        WebElement form = driver.findElement(By.xpath("//form"));
        List<WebElement> textInputs = driver.findElements(By.xpath("//input[@type='text']"));
        Assertions.assertEquals(2, textInputs.size());
    }

    @Test
    public void testSearching() {
        WebElement password=driver.findElement(By.id("search_query_top"));
        password.sendKeys("1231224124");
        WebElement searchButton=driver.findElement(By.cssSelector(".button-search"));
        searchButton.click();

        WebElement resultAllerts=driver.findElement(By.cssSelector(".alert"));
        String expected = "No results were found for your search \"1231224124\"";
        Assertions.assertEquals(expected, resultAllerts.getText());
    }


    @Test
    public void testSearchingSafari() throws InterruptedException {
        WebElement password=driverSafari.findElement(By.id("search_query_top"));
        password.sendKeys("1231224124");
        WebElement searchButton=driverSafari.findElement(By.cssSelector(".button-search"));
        searchButton.click();
        WebElement resultAllerts=driverSafari.findElement(By.cssSelector(".alert"));
        String expected = "No results were found for your search \"1231224124\"";
        Assertions.assertEquals(expected, resultAllerts.getText().trim());
    }
}
