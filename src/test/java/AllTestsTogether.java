import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AllTestsTogether {

    static WebDriver driver;


    @BeforeAll
    static void prepareTheBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        WebDriver.Timeouts timeouts= driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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
    void shouldVerifyAddingProductToCart() {
        WebElement womenTag = driver.findElement(By.xpath(".//a[@title='Women']"));
        womenTag.click();

        WebElement womenLabel = driver.findElement(By.className("cat-name"));
        Assertions.assertTrue(womenLabel.isDisplayed());

        WebElement listIcon = driver.findElement(By.className("icon-th-list"));
        listIcon.click();

        WebElement addToCartButton = driver.findElement(By.cssSelector("[class='button-container col-xs-7 col-md-12']>[data-id-product='1']"));
        addToCartButton.click();

        WebElement proceedToCheckoutButton = driver.findElement(By.xpath(".//a[@class='btn btn-default button button-medium']/span"));
        proceedToCheckoutButton.click();

        WebElement shoppingCart = driver.findElement(By.className("navigation_page"));
        Assertions.assertEquals("Your shopping cart", shoppingCart.getText());

        WebElement itemInCart = driver.findElement(By.id("summary_products_quantity"));
        Assertions.assertEquals("1 Product", itemInCart.getText());
    }
    @Test
    void shouldVerifyDeletingItemsFromCart() {
        WebElement womenTag = driver.findElement(By.xpath(".//a[@title='Women']"));
        womenTag.click();

        WebElement listIcon = driver.findElement((By.className("icon-th-list")));
        listIcon.click();

        WebElement addToCartButton = driver.findElement(By.cssSelector("[class='button-container col-xs-7 col-md-12']>[data-id-product='1']"));
        addToCartButton.click();


        WebElement proceedToCheckoutButton = driver.findElement(By.xpath(".//a[@class='btn btn-default button button-medium']/span"));

        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
        proceedToCheckoutButton.click();

        WebElement shoppingCart = driver.findElement(By.className("navigation_page"));
        Assertions.assertEquals("Your shopping cart", shoppingCart.getText());

        WebElement itemInCart = driver.findElement(By.id("summary_products_quantity"));
        Assertions.assertEquals("1 Product", itemInCart.getText());

        WebElement trashIcon = driver.findElement(By.className("icon-trash"));
        trashIcon.click();

        WebElement emptyCart = driver.findElement(By.xpath(".//span[@class='ajax_cart_no_product']"));
        Assertions.assertEquals("(empty)", emptyCart.getText());
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
    @Test
    void shouldVerifyPresenceOfContactForm(){
        WebElement contactUsButton = driver.findElement(By.xpath(".//div[@id='contact-link']/a"));
        contactUsButton.click();

        WebElement contactForm= driver.findElement(By.xpath(".//h1[@class='page-heading bottom-indent']"));
        Assertions.assertTrue(contactForm.isDisplayed());
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US",contactForm.getText());

    }
    @Test
    void shouldVerifyGoogleMapsLocationOfStoreItsAddressAndOpeningHours() {

        WebElement womenTag= driver.findElement(By.xpath(".//a[@title='Women']"));
        womenTag.click();

        WebElement discoverOurStoresButton= driver.findElement(By.xpath(".//div/a[@title='Our stores']"));
        discoverOurStoresButton.click();

        WebElement googleMapsOkButton= driver.findElement(By.xpath(".//td/button[@class='dismissButton']"));
        googleMapsOkButton.click();

        WebElement locationIcon= driver.findElement(By.xpath("//*[@id='map']/div/div/div[2]/div[2]/div/div[3]/div[3]"));
        locationIcon.click();

        WebElement storeInfoBoard= driver.findElement(By.className("gm-style-iw-d"));
        Assertions.assertTrue(storeInfoBoard.isDisplayed());
    }
}


