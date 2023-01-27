import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class XPathTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("http://automationpractice.pl/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLinks() {
        List<WebElement> elements = driver.findElements(By.xpath("//a"));
        System.out.println(driver.getCurrentUrl());
        Assertions.assertEquals(95, elements.size());
    }

    @Test
    public void testImages() {
        List<WebElement> elements = driver.findElements(By.xpath("//img"));
        Assertions.assertEquals(22, elements.size());
    }

    @Test
    public void testEnterLinks() {
        List<WebElement> elements = driver.findElements(By.xpath("//a"));
        for (WebElement element: elements){
            String href = element.getDomAttribute("href");
            if(href.matches("^[^\\s#]+$")){
                element.click();
                driver.navigate().to(href);
                driver.navigate().back();
            }

        }
    }

    @Test
    public void testForm() {
        WebElement form = driver.findElement(By.xpath("//form"));
        List<WebElement> textInputs = driver.findElements(By.xpath("//input[@type='text']"));
        Assertions.assertEquals(2, textInputs.size());
    }
}
