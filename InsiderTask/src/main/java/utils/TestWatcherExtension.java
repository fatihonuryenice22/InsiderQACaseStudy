package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.util.function.Supplier;

public class TestWatcherExtension implements TestWatcher {
    //Monitors the run and if the test fails it takes screenshot and closes driver
    private final Supplier<WebDriver> driverSupplier;
    public TestWatcherExtension(Supplier<WebDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = driverSupplier.get();
        if (driver != null) {
            Helper.takeScreenshotOnFail(driver);
            DriverFactory.quitDriver(driver);
        }
    }
    @Override
    public void testSuccessful(ExtensionContext context) {
        WebDriver driver = driverSupplier.get();
        DriverFactory.quitDriver(driver);
    }
}
