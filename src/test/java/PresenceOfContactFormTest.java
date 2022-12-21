import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PresenceOfContactFormTest {

    static WebDriver driver;

    @BeforeAll
    static void prepareBrowser() {

        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeEach
    void deleteAllCookies() {
        driver.manage().deleteAllCookies();

    }

    @AfterAll
    static void closeBrowser() {
        driver.close();
    }
    @Test
    void shouldVerifyPresenceOfContactForm(){
    WebElement contactUsButton = driver.findElement(By.xpath(".//div[@id='contact-link']/a"));
    contactUsButton.click();

    WebElement contactForm= driver.findElement(By.xpath(".//h1[@class='page-heading bottom-indent']"));
        Assertions.assertTrue(contactForm.isDisplayed());
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US",contactForm.getText());

}}

