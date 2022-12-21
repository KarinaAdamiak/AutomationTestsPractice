import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class StoresGoogleMapTest {

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

