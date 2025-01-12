package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    public static void takeScreenshotOnFail(WebDriver driver) {
        try {
            //Create date format for our screenshot's name
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
            //File path and type
            String filePath = "screenshots/failure_" + timestamp + ".png";
            //Create screenshots folder if we don't have
            Files.createDirectories(Paths.get("screenshots"));
            //Takes screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // puts our screenshot to our path
            Files.copy(screenshot.toPath(), Paths.get(filePath));
            //Console output for control...
            System.out.println("Screenshot saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
            System.out.println("Waiting for "+seconds+ " seconds");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep interrupted", e);
        }
    }
}
