package de.qcademy.selenium.course.introduction.emine;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
public class BasicSelectors {
    private static WebDriver driver;
    @BeforeAll
   static void globalSetup() { //methode
        WebDriverManager.edgedriver().setup(); //Web driver manager sorgt dafür das die richtige version für den testfall von opera isntalliert. manager schaut in den install pfad ob es schon vorhanden ist, wenn nein installiet er es ansonsten nicht.
        }
        @BeforeEach
        void setup() {
            driver= new EdgeDriver();
        }
        @AfterEach
        void teardown () {
            if (driver != null) {
                driver.quit();
            }
        }
        @Test
        @DisplayName("Should register a new user")
        void registrationTest() throws InterruptedException {
        //Arrange Block
        String email = "emine.fed@gmail.com";
        String vorname = "John";
        String nachname = "Wing";
        String password = " superPassword";
        String expectedUsername = "John Wing";
        // Act Block
        driver.get("http://www.automationpractice.pl");
        Thread.sleep(5000);

        driver.findElement(By.className("login")).click();

        driver.findElement(By.id("email_create")).sendKeys("email");
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(5000);

       driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firsname")).sendKeys(vorname);
        driver.findElement(By.id("cutomer_lastname")).sendKeys(nachname);
        driver.findElement(By.id("passwd")).sendKeys(password);
        Select daySelect = new Select( driver.findElement(By.id("days")));
        daySelect.selectByIndex(10);
            Select monthSelect = new Select( driver.findElement(By.id("months")));
            monthSelect.selectByIndex(3);
            Select yearSelect = new Select( driver.findElement(By.id("years")));
            yearSelect.selectByIndex(5);
            driver.findElement(By.id("submitAccount")).click();
            Thread.sleep(5000);

            String actualUsername = driver.findElement(By.className("header_user_info")).getText().trim();
        // Assert Block
            Assertions.assertEquals(expectedUsername, actualUsername);

        }
    }


