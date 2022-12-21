import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class AddToCartTest {


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
    void shouldVerifyAddingProductToCart(){
        WebElement womenTag= driver.findElement(By.xpath(".//a[@title='Women']"));
        womenTag.click();
        WebElement womenLabel= driver.findElement(By.className("cat-name"));
        Assertions.assertTrue(womenLabel.isDisplayed());

        WebElement listIcon= driver.findElement(By.className("icon-th-list"));
        listIcon.click();

        WebElement addToCartButton= driver.findElement(By.cssSelector("[class='button-container col-xs-7 col-md-12']>[data-id-product='1']"));
        addToCartButton.click();

        WebElement proceedToCheckoutButton= driver.findElement(By.xpath(".//a[@class='btn btn-default button button-medium']/span"));
        proceedToCheckoutButton.click();

        WebElement shoppingCart= driver.findElement(By.className("navigation_page"));
        Assertions.assertEquals("Your shopping cart",shoppingCart.getText());

        WebElement itemInCart= driver.findElement(By.id("summary_products_quantity"));
        Assertions.assertEquals("1 Product",itemInCart.getText());



    }}