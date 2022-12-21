import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LogInTest {

    static WebDriver driver;

    @BeforeAll
    static void browserPreparation () {
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @BeforeEach
    void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }
    @AfterAll
    static void closeBrowser(){
        driver.close();
    }
    @Test
    void shouldVerifyLogInProcess() {
        WebElement signInTag= driver.findElement(By.className("login"));
        signInTag.click();

        WebElement emailAddressInput= driver.findElement(By.id("email"));
        emailAddressInput.sendKeys("test@softie.pl");

        WebElement passwordInput= driver.findElement(By.id("passwd"));
        passwordInput.sendKeys("1qaz!QAZ");

        WebElement signInButton =driver.findElement(By.id("SubmitLogin"));
        signInButton.click();

        Assertions.assertEquals("MY ACCOUNT",driver.findElement(By.className("page-heading")).getText());




    }
}