import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginTest {
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
        driver.get("http://automationpractice.pl/index.php?controller=authentication&back=my-account");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void LoginTestRedirected() {
        WebElement username=driver.findElement(By.id("email"));
        WebElement password=driver.findElement(By.id("passwd"));
        username.sendKeys("s20687@pjwstk.edu.pl");
        password.sendKeys("1234567890");
        WebElement login=driver.findElement(By.id("SubmitLogin"));
        login.click();
        String expectedUrl = "http://automationpractice.pl/index.php?controller=my-account";
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void LoginTestShowMyAccount() {
        WebElement username=driver.findElement(By.id("email"));
        WebElement password=driver.findElement(By.id("passwd"));
        username.sendKeys("s20687@pjwstk.edu.pl");
        password.sendKeys("1234567890");
        WebElement login=driver.findElement(By.id("SubmitLogin"));
        login.click();
        String expectedHeading = "MY ACCOUNT";
        WebElement myAccount =driver.findElement(By.className("page-heading"));
        Assertions.assertEquals(expectedHeading, myAccount.getText());
    }

    @Test
    public void LoginTestFailedNotRedirected() {
        WebElement username=driver.findElement(By.id("email"));
        WebElement password=driver.findElement(By.id("passwd"));
        username.sendKeys("s20687@pjwstk.edu.pl");
        password.sendKeys("123456");
        WebElement login=driver.findElement(By.id("SubmitLogin"));
        login.click();
        String expectedUrl = "http://automationpractice.pl/index.php?controller=my-account";
        Assertions.assertNotEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void LoginTestFailedErrorMessageInvalidEmail() {
        WebElement username=driver.findElement(By.id("email"));
        WebElement password=driver.findElement(By.id("passwd"));
        username.sendKeys("s20687");
        password.sendKeys("123456");
        WebElement login=driver.findElement(By.id("SubmitLogin"));
        login.click();
        WebElement myAccount =driver.findElement(By.cssSelector("div.alert.alert-danger > ol > li"));
        String expectedMessage = "Invalid email address.";
        Assertions.assertEquals(expectedMessage, myAccount.getText());
    }

    @Test
    public void LoginTestFailedErrorMessageInvalidPassword() {
        WebElement username=driver.findElement(By.id("email"));
        WebElement password=driver.findElement(By.id("passwd"));
        username.sendKeys("s20687@pjwstk.edu.pl");
        password.sendKeys("123456");
        WebElement login=driver.findElement(By.id("SubmitLogin"));
        login.click();
        WebElement myAccount =driver.findElement(By.cssSelector("div.alert.alert-danger > ol > li"));
        String expectedMessage = "Invalid password.";
        Assertions.assertEquals(expectedMessage, myAccount.getText());
    }
}
