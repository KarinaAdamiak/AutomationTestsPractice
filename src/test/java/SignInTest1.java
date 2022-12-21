import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Random;

public class SignInTest1 {

    static WebDriver driver;


 @BeforeAll
 static void prepareTheBrowser() {
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
    static void closeBrowser() {
     driver.close();
 }
 @Test
    void shouldVerifySignInProcedure(){
     WebElement signInTag= driver.findElement(By.xpath(".//a[@class='login']"));
     signInTag.click();

     Random random= new Random();
     String randomEmail= "kara"+ random.nextInt(150)+"@gmail.com";
     WebElement emailInput= driver.findElement(By.id("email_create"));
     emailInput.sendKeys(randomEmail);

     WebElement createAccountButton= driver.findElement(By.id("SubmitCreate"));
     createAccountButton.click();
     Assertions.assertEquals("CREATE AN ACCOUNT",driver.findElement(By.xpath(".//h1[text()='Create an account']")).getText());

     WebElement firstNameInput= driver.findElement(By.id("customer_firstname"));
     firstNameInput.sendKeys("Ann");

     WebElement lastNameInput= driver.findElement(By.id("customer_lastname"));
     lastNameInput.sendKeys("Keys");

     WebElement password= driver.findElement(By.id("passwd"));
     Random random1= new Random();
     String randomPassword= "Keys"+ random1.nextInt(100);
     password.sendKeys(randomPassword);

     WebElement address1= driver.findElement(By.id("address1"));
     address1.sendKeys("6 Brooklyn Avenue");

     WebElement city= driver.findElement(By.id("city"));
     city.sendKeys("New York");

     Select stateDropDown= new Select(driver.findElement(By.id("id_state")));
     stateDropDown.selectByValue("32");

     WebElement zipCodeInput= driver.findElement(By.id("postcode"));
     zipCodeInput.sendKeys("10015");

     Select countryDropDown= new Select(driver.findElement(By.id("id_country")));
     countryDropDown.selectByVisibleText("United States");

     WebElement phoneInput= driver.findElement(By.id("phone_mobile"));
     Random random2= new Random();
     String phoneNumber= "78564"+ random2.nextInt(150);
     phoneInput.sendKeys(phoneNumber);

     WebElement aliasInput= driver.findElement(By.id("alias"));
     aliasInput.sendKeys(randomEmail);

     WebElement registerButton= driver.findElement(By.id("submitAccount"));
     registerButton.click();

     Assertions.assertEquals("MY ACCOUNT",driver.findElement(By.className("page-heading")).getText());

  WebElement signOutButton=driver.findElement(By.className("logout"));
  signOutButton.click();
 }

    }

