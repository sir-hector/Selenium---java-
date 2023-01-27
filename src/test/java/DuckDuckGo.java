import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class DuckDuckGo {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        Proxy p = new Proxy();
        p.setProxyType(Proxy.ProxyType.MANUAL);
        p.setAutodetect(false);
        options.setProxy(p);
        driver = new ChromeDriver(options);
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void clickOnThirdElement() {

        WebElement searchInput = driver.findElement(By.cssSelector("#searchbox_input"));
        searchInput.sendKeys("Selenium java");
        WebElement searchButton = driver.findElement(By.className("searchbox_searchButton__F5Bwq"));
        searchButton.sendKeys(Keys.ENTER);
        WebElement thirdElement = driver.findElement(By.cssSelector("#links .nrn-react-div:nth-child(3) h2 a"));
        driver.navigate().to(thirdElement.getAttribute("href"));
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void clickOnFirstElement() {

        WebElement searchInput = driver.findElement(By.cssSelector("#searchbox_input"));
        searchInput.sendKeys("Selenium java");
        WebElement searchButton = driver.findElement(By.className("searchbox_searchButton__F5Bwq"));
        searchButton.sendKeys(Keys.ENTER);
        WebElement thirdElement = driver.findElement(By.cssSelector("#links .nrn-react-div:nth-child(1) h2 a"));
        driver.navigate().to(thirdElement.getAttribute("href"));
        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void otherClickMethod() {
        WebElement element = driver.findElement(By.id("searchbox_input"));
        element.sendKeys(Keys.ENTER);
    }

    @Test
    public void testElementNotFound() {
        boolean isExists = true;
        try{
            WebElement elements = driver.findElement(By.linkText("test"));
        } catch(NoSuchElementException e){
            isExists = false;
        }
        Assertions.assertFalse(isExists);
    }
}
