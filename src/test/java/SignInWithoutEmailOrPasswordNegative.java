import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SignInWithoutEmailOrPasswordNegative {

    static WebDriver driver;


    @BeforeAll
    static void prepareTheBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        WebDriver.Timeouts timeouts= driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @BeforeEach
    void deleteCookies() {
        driver.manage().deleteAllCookies();
    }
    @AfterAll
    static void closeTheBrowser(){
        driver.close();
    }

    @Test
    void shouldVerifySignInWithoutEmail() {
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebElement passwordInput = driver.findElement(By.cssSelector("#passwd"));
        passwordInput.sendKeys("1qaz!QAZ");

        WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
        submitButton.click();

        String alertText = driver.findElement(By.cssSelector("#center_column > .alert-danger")).getText();
        Assertions.assertEquals("There is 1 error\n" +
                "An email address required.", alertText);
        Assertions.assertTrue(alertText.contains("An email address required."));

    }
    @Test
    void shouldVerifySignInWithoutPassword() {
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("123@4321.pl");

        WebElement passwordInput = driver.findElement(By.cssSelector("#passwd"));
        passwordInput.sendKeys("");

        WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
        submitButton.click();

        String alertText = driver.findElement(By.cssSelector("#center_column > .alert-danger")).getText();
        Assertions.assertEquals("There is 1 error\n" +
                "Password is required.", alertText);
        Assertions.assertTrue(alertText.contains("Password is required."));
    }
}
