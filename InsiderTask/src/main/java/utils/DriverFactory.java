package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class DriverFactory {
    public static WebDriver initializeDriver() {
    String baseUrl = ConfigReader.getProperty("baseUrl");
    String browser = ConfigReader.getProperty("browser");
    WebDriver driver;
    if (browser.equalsIgnoreCase("chrome")) {
        //Starts the chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    } else if (browser.equalsIgnoreCase("firefox")) {
        //Starts the firefox browser
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    } else {
        //Exits from the automation if browser var is empty
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
    //Maximizes window and sets the url
    driver.manage().window().maximize();
    driver.get(baseUrl);
    return driver;
}
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
