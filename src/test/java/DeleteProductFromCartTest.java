import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DeleteProductFromCartTest {

    static WebDriver driver;

    @BeforeAll
        static void prepareBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        WebDriver.Timeouts timeouts= driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @BeforeEach
        void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }
    @AfterAll
    static void cloeBrowser(){
        driver.close();
    }
    @Test
    void shouldVerifyDeletingItemsFromCart(){
        WebElement womenTag= driver.findElement(By.xpath(".//a[@title='Women']"));
        womenTag.click();

        WebElement listIcon= driver.findElement((By.className("icon-th-list")));
        listIcon.click();

        WebElement addToCartButton= driver.findElement(By.cssSelector("[class='button-container col-xs-7 col-md-12']>[data-id-product='1']"));
        addToCartButton.click();


        WebElement proceedToCheckoutButton= driver.findElement(By.xpath(".//a[@class='btn btn-default button button-medium']/span"));

        Wait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
        proceedToCheckoutButton.click();

        WebElement shoppingCart= driver.findElement(By.className("navigation_page"));
        Assertions.assertEquals("Your shopping cart",shoppingCart.getText());

        WebElement itemInCart= driver.findElement(By.id("summary_products_quantity"));
        Assertions.assertEquals("1 Product",itemInCart.getText());

        WebElement trashIcon= driver.findElement(By.className("icon-trash"));
        trashIcon.click();

        WebElement emptyCart= driver.findElement(By.xpath(".//span[@class='ajax_cart_no_product']"));
        Assertions.assertEquals("(empty)",emptyCart.getText());



    }
}
