import com.github.javafaker.Faker;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import java.util.List;
import java.util.Random;

public class SignInTest2Faker {


    static WebDriver driver;


    @BeforeAll
    static void browserPreparation() {
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
    void shouldVerifySignInProcedureWithFaker() {
        WebElement signInTag = driver.findElement(By.xpath(".//a[@class='login']"));
        signInTag.click();

        Random random = new Random();
        String randomEmail = "kari" + random.nextInt(150) + "@gmail.com";
        WebElement emailInput = driver.findElement(By.id("email_create"));
        emailInput.sendKeys(randomEmail);

        WebElement createAccountButton = driver.findElement(By.id("SubmitCreate"));
        createAccountButton.click();
        Assertions.assertEquals("CREATE AN ACCOUNT",driver.findElement(By.xpath(".//h1[text()='Create an account']")).getText());

        WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
        String lastName = faker.name().lastName();
        lastNameInput.sendKeys(lastName);

        WebElement passwordInput = driver.findElement(By.id("passwd"));
        String password = faker.random().nextInt(6, 10) + "kari";
        passwordInput.sendKeys(password);

        WebElement address1Input = driver.findElement(By.id("address1"));
        String address = faker.address().fullAddress();
        address1Input.sendKeys(address);

        WebElement cityInput = driver.findElement(By.id("city"));
        String city = faker.address().cityName();
        cityInput.sendKeys(city);

        Select stateDropDown = new Select(driver.findElement(By.id("id_state")));
        List<WebElement> states = stateDropDown.getOptions();
        Random random1=new Random();
        int list= random1.nextInt(states.size());
        states.get(list).click();

        WebElement zipCodeInput = driver.findElement(By.id("postcode"));
        String zipCode = String.valueOf(faker.address().zipCode().substring(0, 5));
        zipCodeInput.sendKeys(zipCode);

        Select countryDropDown= new Select(driver.findElement(By.id("id_country")));
        countryDropDown.selectByVisibleText("United States");

        WebElement phoneInput = driver.findElement(By.id("phone_mobile"));
        String phoneNumber = String.valueOf(faker.phoneNumber().cellPhone());
        phoneInput.sendKeys(phoneNumber);

        WebElement aliasInput = driver.findElement(By.id("alias"));
        aliasInput.sendKeys(randomEmail);

        WebElement registerButton = driver.findElement(By.id("submitAccount"));
        registerButton.click();

        Assertions.assertEquals("MY ACCOUNT", driver.findElement(By.className("page-heading")).getText());

        WebElement signOutButton = driver.findElement(By.className("logout"));
        signOutButton.click();
    }

    }

