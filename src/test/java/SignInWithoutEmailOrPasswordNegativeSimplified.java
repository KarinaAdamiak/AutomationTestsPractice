import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class SignInWithoutEmailOrPasswordNegativeSimplified {


    static WebDriver driver;

    @FindBy(className = "login")
    WebElement signInButton;

    @FindBy(css = "#passwd")
    WebElement passwordInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "SubmitLogin")
    WebElement submitButton;

    public SignInWithoutEmailOrPasswordNegativeSimplified() {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

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
        signInButton.click();

        emailInput.sendKeys("");

        passwordInput.sendKeys("1qaz!QAZ");

        submitButton.click();

        String alertText = driver.findElement(By.cssSelector("#center_column > .alert-danger")).getText();
        Assertions.assertEquals("There is 1 error\n" +
                "An email address required.", alertText);
        Assertions.assertTrue(alertText.contains("An email address required."));

    }
    @Test
    void shouldVerifySignInWithoutPassword() {
        signInButton.click();

        emailInput.sendKeys("123@4321.pl");

        passwordInput.sendKeys("");

        submitButton.click();

        String alertText = driver.findElement(By.cssSelector("#center_column > .alert-danger")).getText();
        Assertions.assertEquals("There is 1 error\n" +
                "Password is required.", alertText);
        Assertions.assertTrue(alertText.contains("Password is required."));
    }
}

