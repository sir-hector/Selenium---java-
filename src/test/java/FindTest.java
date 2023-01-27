import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FindTest {

    //Przykłady znajdowania elementów na stronie www bez elementów xpath

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://www.google.pl");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }


    @Test
    public void findById() {
        WebElement element = driver.findElement(By.id("gbwa"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findByName() {
        WebElement element = driver.findElement(By.name("q"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findByClass() {
        WebElement element = driver.findElement(By.className("SDkEP"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findBylinkText() {
        WebElement element = driver.findElement(By.linkText("Gmail"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findByPartiallinkText() {
        WebElement element = driver.findElement(By.partialLinkText("ma"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findByTagName() {
        WebElement element = driver.findElement(By.tagName("div"));
        Assertions.assertNotNull(element);
    }

    @Test
    public void findByCssSelector() {
        WebElement element = driver.findElement(By.cssSelector("input.gLFyf"));
        Assertions.assertNotNull(element);
    }


}