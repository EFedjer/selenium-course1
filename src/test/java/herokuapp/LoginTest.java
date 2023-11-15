package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private static WebDriver driver;

    @BeforeAll
    static void globalSetup() {
        WebDriverManager.edgedriver().setup(); // Konfiguriert den WebDriver (Edge) für den Test
    }

    @BeforeEach
    void setup() {
        driver = new EdgeDriver(); // Initialisiert den WebDriver vor jedem Testfall

    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit(); // Beendet den WebDriver nach jedem Testfall
        }
    }

    @Test
    @DisplayName("Gültige Anmeldung")
    void validUserLogin() {
        driver.get("https://the-internet.herokuapp.com/login"); // Öffnet die Login-Seite.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Eingabe gültiger Anmeldedaten
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.id("login")).click(); // Klickt auf die Anmelden-Schaltfläche

    }

    @Test
    @DisplayName("Fehlermeldung für ungültigen Benutzernamen")
    void invalidUsernameErrorMessage() {
        driver.get("https://the-internet.herokuapp.com/login"); // Öffnet die Login-Seite
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        // Eingabe eines ungültigen Benutzernamens
        driver.findElement(By.id("username")).sendKeys("ungültigerBenutzername");
        driver.findElement(By.id("password")).sendKeys("irgendEinPasswort");
        driver.findElement(By.id("login")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        assertTrue(driver.findElement(By.id("flash")).isDisplayed(), "Fehlermeldung für ungültigen Benutzernamen nicht angezeigt");

    }

    @Test
    @DisplayName("Fehlermeldung für ungültiges Passwort")
    void invalidPasswordErrorMessage() {
        driver.get("https://the-internet.herokuapp.com/login"); // Öffnet die Login-Seite

        // Eingabe eines gültigen Benutzernamens und eines ungültigen Passworts

    }
}
