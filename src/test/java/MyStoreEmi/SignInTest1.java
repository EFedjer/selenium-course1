package MyStoreEmi;

import com.github.javafaker.Faker; //wird in der Pom ergänzt, ist für die automatische Generierung von Daten
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class SignInTest1 {
    private static WebDriver driver; //instanziire WebDriver
    private static Faker faker = new Faker(); //instanziire JavaFaker

    @BeforeAll
    static void globalSetup() {
        WebDriverManager.edgedriver().setup(); //WebDriver wird asl MS Edge ausgeführt
    }

    @BeforeEach
    void setup() {
        driver = new EdgeDriver();  // wird vor jedem Test neu Initialisiert
    }

    @AfterEach
    void teardown() {
        if (driver != null) {  // WebDriver nach jedem Test beenden, um Platz freizugeben
            driver.quit();
        }
    }

    @Test
    @DisplayName("Should register a new user")
    void registrationTest() throws InterruptedException {
        // Arrange Block
        String email = faker.internet().emailAddress(); //durch JavaFaker werden zufällige Daten hier generiert
        String vorname = faker.name().firstName();
        String nachname = faker.name().lastName();
        String password = faker.internet().password();
        String expectedUsername = vorname + " " + nachname; //ein Benutzername wird erstellt

        // Act Block
        driver.get("http://www.automationpractice.pl"); // die Webadresse der zu öffnenden Seite wird angegeben
        Thread.sleep(5000); // eine Wartezeit zum Laden der Seite wird eingegeben

        driver.findElement(By.className("login")).click();

        driver.findElement(By.id("email_create")).sendKeys(email);
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(5000);

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(vorname);
        driver.findElement(By.id("customer_lastname")).sendKeys(nachname);
        driver.findElement(By.id("passwd")).sendKeys(password);

        Select daySelect = new Select(driver.findElement(By.id("days")));
        daySelect.selectByIndex(10);
        Select monthSelect = new Select(driver.findElement(By.id("months")));
        monthSelect.selectByIndex(3);
        Select yearSelect = new Select(driver.findElement(By.id("years")));
        yearSelect.selectByIndex(5);
        driver.findElement(By.id("submitAccount")).click();
        Thread.sleep(5000);

       //der Benutzername wird aus der Seite geholt
        String actualUsername = driver.findElement(By.className("account")).getText().trim();

        // Assert Block
        Assertions.assertEquals(expectedUsername, actualUsername); //es wird überprüft ob der erwartete Benutzername //
        // mit dem tatsächlichen übereinstimmt
    }
}